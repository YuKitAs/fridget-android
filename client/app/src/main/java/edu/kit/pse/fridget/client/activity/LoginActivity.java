package edu.kit.pse.fridget.client.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.datamodel.Device;
import edu.kit.pse.fridget.client.datamodel.User;
import edu.kit.pse.fridget.client.datamodel.representation.UserWithJwtRepresentation;
import edu.kit.pse.fridget.client.service.DeviceService;
import edu.kit.pse.fridget.client.service.RetrofitClientInstance;
import edu.kit.pse.fridget.client.service.UserService;
import edu.kit.pse.fridget.client.utility.SafeIntentExtrasExtractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String DEFAULT = "N/A";

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private Context context = this;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");

        String ownUserId = restoreUserId();
        if (!DEFAULT.equals(ownUserId)) {
            redirectToStartActivity(ownUserId);
            return;
        }

        setContentView(R.layout.login_activity);

        // Button listener
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Configure Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(LoginActivity.this, "Authentification failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        sendIdToken(acct.getIdToken());
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentification failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void sendIdToken(String googleIdToken) {
        RetrofitClientInstance.getRetrofitInstance().create(UserService.class).sendIdToken(googleIdToken).enqueue(new Callback<UserWithJwtRepresentation>() {
            @Override
            public void onResponse(@NonNull Call<UserWithJwtRepresentation> call, @NonNull Response<UserWithJwtRepresentation> response) {
                UserWithJwtRepresentation body = response.body();
                if (body != null) {
                    User user = body.getUser();

                    Log.i(TAG, String.format("Generated JWT %s for user %s.", new Gson().toJson(body.getJwt()), new Gson().toJson(user)));

                    sendDevice(user.getId());

                    String ownUserId = user.getId();
                    String ownUserName = user.getGoogleName();

                    //Daten des Response speichern
                    PreferenceManager.getDefaultSharedPreferences(context).edit()
                            .putString("OwnUserIDnumber", ownUserId)
                            .putString("OwnUserName", ownUserName)
                            .apply();

                    redirectToStartActivity(ownUserId);
                } else Log.e(TAG, "Post an Server failed");
            }

            @Override
            public void onFailure(@NonNull Call<UserWithJwtRepresentation> call, @NonNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "Connection to Database failed!!!");
            }
        });
    }

    private String restoreUserId() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("OwnUserIDnumber", DEFAULT);
    }

    private void redirectToStartActivity(String ownUserId) {
        Intent intent = new Intent(LoginActivity.this, StartActivity.class);

        // If user clicks on the push notification, coolNoteId will be set in extras
        String coolNoteId = SafeIntentExtrasExtractor.getString(getIntent(), "coolNoteId");

        if (coolNoteId != null) {
            intent.putExtra("coolNoteId", coolNoteId);
        }

        startActivity(intent);
    }

    private void sendDevice(String userId) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w(TAG, "getInstanceId failed", task.getException());
                return;
            }

            String token = task.getResult().getToken();

            RetrofitClientInstance.getRetrofitInstance().create(DeviceService.class).sendDevice(new Device(null, userId, token)).enqueue(new Callback<Device>() {
                @Override
                public void onResponse(@NonNull Call<Device> call, @NonNull Response<Device> response) {
                    Device body = response.body();
                    if (body != null) {
                        Log.i(TAG, String.format("Saved InstanceID token for user %s: %s", body.getUserId(), body.getInstanceIdToken()));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Device> call, @NonNull Throwable t) {
                    t.printStackTrace();
                    Log.e(TAG, "Sending device failed");
                }
            });
        });
    }
}

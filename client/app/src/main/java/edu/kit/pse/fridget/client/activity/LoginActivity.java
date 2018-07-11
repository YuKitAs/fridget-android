package edu.kit.pse.fridget.client.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.LoginActivityBinding;
import edu.kit.pse.fridget.client.viewmodel.LoginViewModel;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN =1;
    private GoogleApiClient mGoogleAPiClient;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    private SignInButton mGoogleBtn;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.i(TAG, "Calling onCreate");
            LoginActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
            LoginViewModel login = new LoginViewModel();
            mGoogleBtn = (SignInButton) findViewById(R.id.sign_in_button);
            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser() != null){
                        startActivity(new Intent (LoginActivity.this,StartActivity.class));
                    }
                }
            };

            // Configure Google Sign In
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            mGoogleAPiClient = new GoogleApiClient.Builder(getApplicationContext())
                    .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Toast.makeText(LoginActivity.this, "You got an Error", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();


            mGoogleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signIn();
                }
            });
        }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleAPiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                           Toast.makeText(LoginActivity.this, "Authentification failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
       mAuth.addAuthStateListener(mAuthListener);

    }

            @Override
            protected void onResume() {
                super.onResume();
                Log.i(TAG, "Calling onResume");
            }

            @Override
            protected void onPause() {
                Log.i(TAG, "Calling onPause");
                super.onPause();
            }

            @Override
            protected void onStop() {
                Log.i(TAG, "Calling onStop");
                super.onStop();
            }

            @Override
            protected void onDestroy() {
                Log.i(TAG, "Calling onDestroy");
                super.onDestroy();
            }


        }














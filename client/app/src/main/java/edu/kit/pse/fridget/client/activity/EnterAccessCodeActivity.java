package edu.kit.pse.fridget.client.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.kit.pse.fridget.client.R;
import edu.kit.pse.fridget.client.databinding.EnterAccessCodeActivityBinding;
import edu.kit.pse.fridget.client.datamodel.command.EnterFlatshareCommand;
import edu.kit.pse.fridget.client.viewmodel.CreateFlatshareViewModel;
import edu.kit.pse.fridget.client.viewmodel.EnterAccessCodeViewModel;

public class EnterAccessCodeActivity extends AppCompatActivity {

    private static final String TAG = EnterAccessCodeActivity.class.getSimpleName();
    private Context context = this;
    public static final String DEFAULT="N/A";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        setContentView(R.layout.enter_access_code_activity);

        //OwnUserId  von shared Preferences abgerufen
        SharedPreferences sharedPreferences =getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String ownUserIDnumber =sharedPreferences.getString("OwnUserIDnumber", DEFAULT);


        final EditText accesscode = (EditText) findViewById(R.id.accesscode_input);
        final EnterAccessCodeViewModel enterAccessCode = new EnterAccessCodeViewModel();
        Button gotoFridgeButton = findViewById(R.id.gotofridge);

        gotoFridgeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EnterFlatshareCommand enterFlatshareCommand =new EnterFlatshareCommand(accesscode.getText().toString(),ownUserIDnumber);
                enterAccessCode.createMembership(enterFlatshareCommand,v,context);


            }


        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Calling onStart");

        //Daten flatshareiD von shared preferences wird abgefragt
        SharedPreferences sharedPreferences = getSharedPreferences("edu.kit.pse.fridget.client_preferences", MODE_PRIVATE);
        String flatshareId = sharedPreferences.getString("flatshareId", DEFAULT);
        updateUI(flatshareId);

    }


    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "Calling onStop");
        //Nach erfolgreicher Eingabe des Accesscodes wird der Faltsharename vom Server abgerufen und gespeichert
        CreateFlatshareViewModel createFlatshareViewModel =new CreateFlatshareViewModel();
        SharedPreferences sharedPreferences =getSharedPreferences("edu.kit.pse.fridget.client_preferences",MODE_PRIVATE);
        String flatshareId =sharedPreferences.getString("flatshareId", DEFAULT);
        if(flatshareId != DEFAULT){
        createFlatshareViewModel.getFlatshare(flatshareId,context);}
    }




    //Es wird geprüft ob schon eine flatshareid vorhanden ist, wenn ja wird automatisch in HomeActivity gewechselt
    private void updateUI(String flatshareId) {
        if (flatshareId !=DEFAULT) {
            startActivity(new Intent(EnterAccessCodeActivity.this, HomeActivity.class));
        }
    }






}





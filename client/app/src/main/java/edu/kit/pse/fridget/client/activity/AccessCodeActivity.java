package edu.kit.pse.fridget.client.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.kit.pse.fridget.client.R;

import edu.kit.pse.fridget.client.datamodel.command.EnterFlatshareCommand;
import edu.kit.pse.fridget.client.viewmodel.EnterAccessCodeViewModel;

public class AccessCodeActivity extends AppCompatActivity {

    private static final String TAG = AccessCodeActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        setContentView(R.layout.enter_access_code_activity);

        final EditText accesscode = (EditText) findViewById(R.id.accesscode_input);
        final String user = "blabla";
        final EnterAccessCodeViewModel enterAccessCode = new EnterAccessCodeViewModel();
        Button gotoFridgeButton = (Button) findViewById(R.id.gotofridge);

        gotoFridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EnterFlatshareCommand enterFlatshareCommand =new EnterFlatshareCommand(accesscode.getText().toString(),user);
                enterAccessCode.createMembership(enterFlatshareCommand,v);


                }


        });


}




}

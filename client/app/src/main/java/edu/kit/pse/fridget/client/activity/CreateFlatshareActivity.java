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
import edu.kit.pse.fridget.client.databinding.CreateFlatshareActivityBinding;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.User;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import edu.kit.pse.fridget.client.viewmodel.CreateFlatshareViewModel;

public class CreateFlatshareActivity extends AppCompatActivity {

    private static final String TAG = CreateFlatshareActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate");
        setContentView(R.layout.create_flatshare_activity);

        // CreateFlatshareActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.create_flatshare_activity);
        //CreateFlatshareViewModel createFlatshare = new CreateFlatshareViewModel();
        // binding.setCreateFlatshare(createFlatshare);
        final EditText flatsharename = (EditText) findViewById(R.id.flatsharename_input);
        final String user = "blabla";
        final CreateFlatshareViewModel createFlatshareViewModel = new CreateFlatshareViewModel();
        Button createFlatshareButton = (Button) findViewById(R.id.create);

        createFlatshareButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                CreateFlatshareCommand createFlatshareCommand = new CreateFlatshareCommand(flatsharename.getText().toString(), user);
                createFlatshareViewModel.sendNetworkRequest(createFlatshareCommand);

            }

        }) ;

    }
}


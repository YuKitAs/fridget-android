package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;

public class FullCoolNoteViewModel extends ViewModel {

    String tempMagnetColor = "#00FF00";
    String title = getCoolNote().getTitle();

    public int getMagnetColor() {
        return Color.parseColor(tempMagnetColor);
    }


    //eigentlich vom server
    public CoolNote getCoolNote(){
        CoolNote tempCN = new CoolNote("a6373664-7b90-11e8-adc0-fa7ae01bbeb", "Hello World!", "Hello!",
                "3879a0a8-546d-41cb-b26e-eb1324d0e72c", 4, 0, null, null);
        return tempCN;
    }

    public void deleteCoolNote(String userId, String flatshareId) {

    }

    public void goBack(View v) {

        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);

    }

}

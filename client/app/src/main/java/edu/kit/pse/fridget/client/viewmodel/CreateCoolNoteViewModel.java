package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.kit.pse.fridget.client.activity.FullTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;

public class CreateCoolNoteViewModel extends ViewModel {

    int i = 0;
    private String tempId = "a6373664-7b90-11e8-adc0-fa7ae01bbeb";
    private String tempFlatshareId = "a6373664-7b90-11e8-adc0-fa7ae01bbebc";
    private String tempCreatorUserId = "3879a0a8-546d-41cb-b26e-eb1324d0e72c";
    private String tempCreatedAt = "01.01.2018";
    private String title = "";
    private String content = "";
    private int tempPosition = 4;

    //Noch keine Daten gesendet
    public CoolNote createCoolNote(String title, String content){
        CoolNote coolNote = new CoolNote(tempId, title, content ,tempFlatshareId, tempCreatorUserId, tempCreatedAt, tempPosition);
        return coolNote;
    }

    public void postCoolNote(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, FullTextCoolNoteActivity.class);
        i.putExtra("coolNoteTitle", title);
        i.putExtra("coolNoteContent", content);
        context.startActivity(i);
    }

    public void goBack(View v) {
        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }

}

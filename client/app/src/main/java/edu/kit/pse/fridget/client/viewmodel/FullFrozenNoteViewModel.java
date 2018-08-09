package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;

public class FullFrozenNoteViewModel extends ViewModel {
    String title = getFrozenNote().getTitle();

    public FrozenNote getFrozenNote(){
        FrozenNote tempFN = new FrozenNote("1a607522-e697-4d54-9094-81e10a6828bd", "", "","dddd9873-57e7-43e0-a6d0-8bac4c5cba84",
                 1);
        return tempFN;
    }

    public void goBack(View v) {

        Context context = v.getContext();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);

    }

}

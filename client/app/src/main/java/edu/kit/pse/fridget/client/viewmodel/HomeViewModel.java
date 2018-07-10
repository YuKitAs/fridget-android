package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.activity.CreateTextCoolNoteActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<List<CoolNote>> liveDatacNList = new MutableLiveData<List<CoolNote>>();
    private MutableLiveData<List<FrozenNote>> liveDatafNList = new MutableLiveData<List<FrozenNote>>();

    private ArrayList<CoolNote> cNList = new ArrayList<>(3);
    private ArrayList<FrozenNote> fNList = new ArrayList<>(9);



    // nur zum Testen
    public String cNmagnetColor1 ="#4B088A";
    public String t1 = "^-^";
    public CoolNote cN1 = new CoolNote();


    // Konstruktor
    public HomeViewModel(){
        cN1.setTitle("yaaay"); // zum testen
        this.getcNList();
        this.getfNList();
        this.liveDatacNList.setValue(this.cNList);
        this.liveDatafNList.setValue(this.fNList);
    }


    // muss noch bearbeitet werden.. nur zum testen für visibility
    public boolean isEmptyCN(int i) {
           return false;
    }


    public String getCNTitle(int coolNoteId){
        return this.liveDatacNList.getValue().get(coolNoteId).getTitle();
    }

    // get methode.... hier service aufrufen
    public ArrayList<FrozenNote> getfNList(){
        return fNList;
    }


    // get methode.... hier service aufrufen
    public ArrayList<CoolNote> getcNList(){
        this.cNList.add(cN1); // nur zum testen
        return cNList;
    }

    public void onPlusButtonClicked(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context, CreateTextCoolNoteActivity.class);
        context.startActivity(intent);

    }

}

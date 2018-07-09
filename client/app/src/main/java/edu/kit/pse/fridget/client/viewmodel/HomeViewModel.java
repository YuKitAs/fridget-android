package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Bindable;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;
import java.util.List;

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
        this.cNList.add(cN1);
        this.liveDatacNList.setValue(this.cNList);
        this.liveDatafNList.setValue(this.fNList);
    }


    // muss noch bearbeitet werden.. nur zum testen für visibility
    public boolean isEmptyCN(int i) {
           return false;
    }

    public MutableLiveData<List<CoolNote>> getLiveDatacNList() {
        return liveDatacNList;
    }


    private ArrayList<FrozenNote> getfNList(){
        return fNList;
    }

}

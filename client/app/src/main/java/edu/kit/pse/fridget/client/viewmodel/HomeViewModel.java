package edu.kit.pse.fridget.client.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import edu.kit.pse.fridget.client.datamodel.CoolNote;
import edu.kit.pse.fridget.client.datamodel.FrozenNote;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<CoolNote>> liveDatacNList = new MutableLiveData<List<CoolNote>>();
    private MutableLiveData<List<FrozenNote>> liveDatafNList = new MutableLiveData<List<FrozenNote>>();

    private ArrayList<CoolNote> cNList = new ArrayList<>(3);
    private ArrayList<FrozenNote> fNList = new ArrayList<>(9);


    public MutableLiveData<List<CoolNote>> getLiveDatacNList() {
        return liveDatacNList;
    }


    private ArrayList<FrozenNote> getfNList(){
        return fNList;
    }

}

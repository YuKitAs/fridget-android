package edu.kit.pse.fridget.client.datamodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Flatshare extends BaseObservable {


    //Name der WG
    public String name;


    //ID der WG
    public String id;

    @Bindable
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name){
        this.name =name;

    }
}

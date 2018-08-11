package edu.kit.pse.fridget.client.datamodel;

import com.google.gson.annotations.SerializedName;

public class Flatshare {
    @SerializedName("id")
    private final String id;

    @SerializedName("name")
    private final String name;

    public Flatshare(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

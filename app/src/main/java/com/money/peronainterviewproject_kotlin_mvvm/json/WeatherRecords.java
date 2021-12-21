package com.money.peronainterviewproject_kotlin_mvvm.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherRecords implements Serializable {
    @SerializedName("datasetDescription")
    private String datasetDescription;

    @SerializedName("location")
    private ArrayList<WeatherLocation> locationArrayList;

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public void setDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
    }

    public ArrayList<WeatherLocation> getLocationArrayList() {
        return locationArrayList;
    }

    public void setLocationArrayList(ArrayList<WeatherLocation> locationArrayList) {
        this.locationArrayList = locationArrayList;
    }
}

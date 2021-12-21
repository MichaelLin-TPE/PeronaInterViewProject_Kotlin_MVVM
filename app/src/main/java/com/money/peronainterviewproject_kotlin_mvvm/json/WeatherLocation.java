package com.money.peronainterviewproject_kotlin_mvvm.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherLocation implements Serializable {
    @SerializedName("locationName")
    private String locationName;

    @SerializedName("weatherElement")
    private ArrayList<WeatherElement> elementArrayList;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<WeatherElement> getElementArrayList() {
        return elementArrayList;
    }

    public void setElementArrayList(ArrayList<WeatherElement> elementArrayList) {
        this.elementArrayList = elementArrayList;
    }
}

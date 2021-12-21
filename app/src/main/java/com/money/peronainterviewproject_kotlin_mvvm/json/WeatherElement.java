package com.money.peronainterviewproject_kotlin_mvvm.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherElement implements Serializable {
    @SerializedName("elementName")
    private String elementName;

    @SerializedName("time")
    private ArrayList<WeatherTime> timeArrayList;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public ArrayList<WeatherTime> getTimeArrayList() {
        return timeArrayList;
    }

    public void setTimeArrayList(ArrayList<WeatherTime> timeArrayList) {
        this.timeArrayList = timeArrayList;
    }
}

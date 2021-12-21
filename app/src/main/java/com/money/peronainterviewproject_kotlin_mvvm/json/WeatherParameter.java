package com.money.peronainterviewproject_kotlin_mvvm.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherParameter implements Serializable {
    @SerializedName("parameterName")
    private String parameterName;
    @SerializedName("parameterUnit")
    private String parameterUnit;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterUnit() {
        return parameterUnit;
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }
}

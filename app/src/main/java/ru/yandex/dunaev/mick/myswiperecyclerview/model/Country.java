package ru.yandex.dunaev.mick.myswiperecyclerview.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("name")
    private String countryName;

    @SerializedName("alpha3Code")
    private String code;

    @SerializedName("flag")
    private String countryFlagURL;

    public String getCountryName() {
        return countryName;
    }

    public String getCode() {
        return code;
    }

    public String getCountryFlagURL() {
        return countryFlagURL;
    }
}

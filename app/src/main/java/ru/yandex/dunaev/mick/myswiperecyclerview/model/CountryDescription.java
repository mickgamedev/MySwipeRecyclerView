package ru.yandex.dunaev.mick.myswiperecyclerview.model;

import java.util.List;

public class CountryDescription {
    private List<String> callingCodes;
    private String capital;
    private List<String> altSpellings;
    private String subregion;
    private long population;
    private float area;
    private List<String> timezones;
    private List<String> otherNames;
    private List<Float> latlng;

    public String getLatlng() {
        if(latlng == null) return "";
        return String.format("lat: %.2f   long: %.2f", latlng.get(0), latlng.get(1));
    }

    public float getLat(){
        if(latlng == null) return 0.0f;
        return latlng.get(0);
    }
    public float getLng(){
        if(latlng == null) return 0.0f;
        return latlng.get(1);
    }

    public String getCallingCodes() {
        if(callingCodes == null) return "";
        String ret = "";
        for(String st:callingCodes) ret += "+" + st + " ";
        return ret;
    }

    public String getCapital() {
        return capital;
    }

    public String getAltSpellings() {
        if(altSpellings == null) return "";
        String ret = "";
        for(String st:altSpellings) ret += st + "\n";
        return ret;
    }

    public String getSubregion() {
        return subregion;
    }

    public long getPopulation() {
        return population;
    }

    public float getArea() {
        return area;
    }

    public String getTimezones() {
        if(timezones == null) return "";
        String ret = "";
        for(String st:timezones) ret += st + "\n";
        return ret;
    }

    public String getOtherNames() {
        if(otherNames == null) return "";
        String ret = "";
        for(String st:otherNames) ret += st + "\n";
        return ret;
    }
}

package ru.yandex.dunaev.mick.myswiperecyclerview.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yandex.dunaev.mick.myswiperecyclerview.model.ICountry;
import ru.yandex.dunaev.mick.myswiperecyclerview.model.ICountryBounds;

public class CountiesHelper {
    private static ICountry sICountry;
    private static ICountryBounds sICountryBounds;
    private CountiesHelper(){}

    public static ICountry getInstance(){
        if(sICountry == null){
            sICountry = new Retrofit.Builder()
                    .baseUrl("https://restcountries.eu")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ICountry.class);
        }
        return sICountry;
    }

    public static  ICountryBounds getInstanceBounds(){
        if(sICountryBounds == null){
            sICountryBounds = new Retrofit.Builder()
                    .baseUrl("https://nominatim.openstreetmap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ICountryBounds.class);
        }
        return sICountryBounds;

    }


}

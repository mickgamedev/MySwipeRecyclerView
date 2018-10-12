package ru.yandex.dunaev.mick.myswiperecyclerview.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yandex.dunaev.mick.myswiperecyclerview.model.ICountry;

public class CountiesHelper {
    private static ICountry sICountry;
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


}

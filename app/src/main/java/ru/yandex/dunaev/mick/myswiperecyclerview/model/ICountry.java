package ru.yandex.dunaev.mick.myswiperecyclerview.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICountry {

    @GET("/rest/v2/all")
    Call<List<Country>> getCountriesList();

}

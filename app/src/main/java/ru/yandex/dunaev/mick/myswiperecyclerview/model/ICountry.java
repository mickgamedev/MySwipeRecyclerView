package ru.yandex.dunaev.mick.myswiperecyclerview.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICountry {

    @GET("/rest/v2/all")
    Call<List<Country>> getCountriesList();

    @GET("/rest/v2/alpha/{code}")
    Call<CountryDescription> getCountryDiscription(@Path("code") String code);

}

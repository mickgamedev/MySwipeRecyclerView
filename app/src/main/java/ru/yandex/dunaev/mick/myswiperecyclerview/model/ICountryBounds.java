package ru.yandex.dunaev.mick.myswiperecyclerview.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICountryBounds {
    @GET("/search")
    Call<List<CountryBounds>> getCountryBounds(@Query("q") String countryName, @Query("format") String format);

}

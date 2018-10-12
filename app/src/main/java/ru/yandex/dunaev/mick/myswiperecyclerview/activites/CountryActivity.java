package ru.yandex.dunaev.mick.myswiperecyclerview.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.yandex.dunaev.mick.myswiperecyclerview.R;
import ru.yandex.dunaev.mick.myswiperecyclerview.map.MyMapClickListener;
import ru.yandex.dunaev.mick.myswiperecyclerview.model.CountryDescription;
import ru.yandex.dunaev.mick.myswiperecyclerview.utils.CountiesHelper;

public class CountryActivity extends AppCompatActivity {
    public static String EXTRA_COUNTRY_NAME = "ExtraCountryName";
    public static String EXTRA_COUNTRY_CODE = "ExtraCountryCode";
    public static String EXTRA_COUNTRY_FLAG_URL = "ExtraCountryFlagUrl";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.flag) ImageView mFlag;

    @BindView(R.id.subregion) TextView subRegion;
    @BindView(R.id.capital) TextView capital;
    @BindView(R.id.callingCodes) TextView callingCodes;
    @BindView(R.id.altSpellings) TextView altSpellings;
    @BindView(R.id.population) TextView population;
    @BindView(R.id.area) TextView area;
    @BindView(R.id.timezones) TextView timezones;
    @BindView(R.id.otherNames) TextView otherNames;
    @BindView(R.id.latlng) TextView latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        String countryName = getIntent().getStringExtra(EXTRA_COUNTRY_NAME);
        String countryCode = getIntent().getStringExtra(EXTRA_COUNTRY_CODE);
        String countryFlagUrl = getIntent().getStringExtra(EXTRA_COUNTRY_FLAG_URL);

        ButterKnife.bind(this);

        mToolbar.setTitle(countryName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(countryFlagUrl, mFlag);

        CountiesHelper.getInstance().getCountryDiscription(countryCode.toLowerCase()).enqueue(new Callback<CountryDescription>() {
            @Override
            public void onResponse(Call<CountryDescription> call, Response<CountryDescription> response) {
                if(response.body() == null) return;
                CountryDescription description = response.body();

                subRegion.setText(description.getSubregion());
                capital.setText(description.getCapital());
                callingCodes.setText(description.getCallingCodes());
                altSpellings.setText(description.getAltSpellings());
                population.setText(String.format("%d",description.getPopulation()));
                area.setText(String.format("%.0f",description.getArea()));
                timezones.setText(description.getTimezones());
                otherNames.setText(description.getOtherNames());
                latLng.setText(description.getLatlng());
                latLng.setOnClickListener(new MyMapClickListener(description.getLat(),description.getLng()));
            }

            @Override
            public void onFailure(Call<CountryDescription> call, Throwable t) {

            }
        });
    }

}

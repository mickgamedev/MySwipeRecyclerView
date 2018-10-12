package ru.yandex.dunaev.mick.myswiperecyclerview.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import ru.yandex.dunaev.mick.myswiperecyclerview.map.MyMap;
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
    @BindView(R.id.latlng) TextView latLng;

    private float mLat = 0.0f;
    private float mLng = 0.0f;

    private String countryName;
    private String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        countryName = getIntent().getStringExtra(EXTRA_COUNTRY_NAME);
        countryCode = getIntent().getStringExtra(EXTRA_COUNTRY_CODE);
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
                latLng.setText(description.getLatlng());
                mLat = description.getLat();
                mLng = description.getLng();
                latLng.setOnClickListener(new MyMapClickListener(CountryActivity.this,mLat,mLng,countryName,countryCode));
            }

            @Override
            public void onFailure(Call<CountryDescription> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.flag)
    public void OnClickFlag(View view){
        Intent intent = new Intent(this,MyMapView.class);
        intent.putExtra(MyMapView.EXTRA_LAT,mLat);
        intent.putExtra(MyMapView.EXTRA_LNG,mLng);
        intent.putExtra(MyMapView.EXTRA_COUNTRY_NAME,countryName);
        intent.putExtra(MyMapView.EXTRA_COUNTRY_CODE,countryCode);
        startActivity(intent);
    }

}

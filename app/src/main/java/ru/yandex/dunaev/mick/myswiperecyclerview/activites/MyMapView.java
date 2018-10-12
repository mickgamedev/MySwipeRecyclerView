package ru.yandex.dunaev.mick.myswiperecyclerview.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.dunaev.mick.myswiperecyclerview.R;

public class MyMapView extends AppCompatActivity {
    public static String EXTRA_LAT = "ExtraLat";
    public static String EXTRA_LNG = "ExtraLng";
    public static String EXTRA_COUNTRY_NAME = "ExtraCountryName";
    public static String EXTRA_COUNTRY_CODE = "ExtraCountryCode";

    @BindView(R.id.textViewLng) TextView textLng;
    @BindView(R.id.textViewLat) TextView textLat;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        ButterKnife.bind(this);
        String countryName = getIntent().getStringExtra(EXTRA_COUNTRY_NAME);
        String countryCode = getIntent().getStringExtra(EXTRA_COUNTRY_CODE);

        mToolbar.setTitle(countryName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        float lat = getIntent().getFloatExtra(EXTRA_LAT,0.0f);
        float lng = getIntent().getFloatExtra(EXTRA_LNG, 0.0f);


        textLat.setText(String.format("%.2f",lat));
        textLng.setText(String.format("%.2f",lng));
    }
}

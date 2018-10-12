package ru.yandex.dunaev.mick.myswiperecyclerview.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.yandex.dunaev.mick.myswiperecyclerview.R;
import ru.yandex.dunaev.mick.myswiperecyclerview.model.CountryBounds;
import ru.yandex.dunaev.mick.myswiperecyclerview.utils.CountiesHelper;

public class MyMapView extends AppCompatActivity implements OnMapReadyCallback {
    public static String EXTRA_LAT = "ExtraLat";
    public static String EXTRA_LNG = "ExtraLng";
    public static String EXTRA_COUNTRY_NAME = "ExtraCountryName";
    public static String EXTRA_COUNTRY_CODE = "ExtraCountryCode";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.mapView) MapView mMapView;

    private GoogleMap map;
    private float lat;
    private float lng;

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

        lat = getIntent().getFloatExtra(EXTRA_LAT,0.0f);
        lng = getIntent().getFloatExtra(EXTRA_LNG, 0.0f);

        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
        //map.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(
         //       new LatLng(-44, 113), new LatLng(-10, 154)),0));

    }
    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}

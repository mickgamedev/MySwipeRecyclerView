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

import java.util.ArrayList;
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
    private CountryBounds mCountryBounds;

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

        CountiesHelper.getInstanceBounds().getCountryBounds(countryName,"json").enqueue(new Callback<List<CountryBounds>>() {
            @Override
            public void onResponse(Call<List<CountryBounds>> call, Response<List<CountryBounds>> response) {
                List<CountryBounds> countryBounds = response.body();
                if(countryBounds == null) return;
                List<CountryBounds> filterCountries = new ArrayList<>();
                for(CountryBounds country : countryBounds){
                    float minLat, minLng, maxLat, maxLng;
                    CountryBounds.Bounds bounds = country.parseBounds();
                    if(bounds == null) continue;
                    minLat = bounds.getMinLat();
                    minLng = bounds.getMinLng();
                    maxLat = bounds.getMaxLat();
                    maxLng = bounds.getMaxLng();

                    if((lat >= minLat && lat <= maxLat) && (lng >= minLng && lng <= maxLng)) filterCountries.add(country);
                }
                if(filterCountries.size() == 0) mCountryBounds = null;
                else mCountryBounds = filterCountries.get(0);
                mMapView.getMapAsync(MyMapView.this);

            }

            @Override
            public void onFailure(Call<List<CountryBounds>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        if(mCountryBounds == null) {
            map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
        } else {
            float minLat, minLng, maxLat, maxLng;
            CountryBounds.Bounds bounds = mCountryBounds.parseBounds();
            if(bounds == null) {
                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
                return;
            }
            minLat = bounds.getMinLat();
            minLng = bounds.getMinLng();
            maxLat = bounds.getMaxLat();
            maxLng = bounds.getMaxLng();

            map.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(
                   new LatLng(minLat, minLng), new LatLng(maxLat, maxLng)),0));
        }

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

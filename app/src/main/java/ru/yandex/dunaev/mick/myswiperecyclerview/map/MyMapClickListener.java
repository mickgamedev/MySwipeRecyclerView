package ru.yandex.dunaev.mick.myswiperecyclerview.map;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import ru.yandex.dunaev.mick.myswiperecyclerview.activites.MyMapView;

public class MyMapClickListener implements View.OnClickListener {
    private float lat;
    private float lng;
    private String countryName;
    private String countryCode;

    private Context mContext;

    public MyMapClickListener(Context context, float lat, float lng, String cn, String cc) {
        this.lat = lat;
        this.lng = lng;
        this.mContext = context;
        countryName = cn;
        countryCode = cc;
    }

    @Override
    public void onClick(View v) {
        Log.v("MyClickListener",String.format("%.2f %.2f",lat,lng));
        Intent intent = new Intent(mContext,MyMapView.class);
        intent.putExtra(MyMapView.EXTRA_LAT,lat);
        intent.putExtra(MyMapView.EXTRA_LNG,lng);
        intent.putExtra(MyMapView.EXTRA_COUNTRY_NAME,countryName);
        intent.putExtra(MyMapView.EXTRA_COUNTRY_CODE,countryCode);
        mContext.startActivity(intent);
    }
}

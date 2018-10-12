package ru.yandex.dunaev.mick.myswiperecyclerview.map;

import android.util.Log;
import android.view.View;

public class MyMapClickListener implements View.OnClickListener {
    private float lat;
    private float lng;

    public MyMapClickListener(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public void onClick(View v) {
        Log.v("MyClickListener",String.format("%.2f %.2f",lat,lng));
    }
}

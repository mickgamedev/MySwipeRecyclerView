package ru.yandex.dunaev.mick.myswiperecyclerview.map;

public class MyMap {
    private float lat;
    private float lng;

    private MyMap(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    private static MyMap instanse;

    public static MyMap getInstanse(){
        return instanse;
    }

    public static void init(float lat,float lng){
        instanse = new MyMap(lat,lng);
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}

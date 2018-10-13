package ru.yandex.dunaev.mick.myswiperecyclerview.model;

import java.util.List;

public class CountryBounds {
    private List<String> boundingbox;
    private String display_name;
    public List<String> getBoundingbox() {
        return boundingbox;
    }
    public Bounds parseBounds(){
        float minLat, minLng, maxLat, maxLng;
        List<String> bound = boundingbox;
        if(bound == null || bound.size() < 4) return null;
        try {
            minLat = Float.parseFloat(bound.get(0));
            minLng = Float.parseFloat(bound.get(2));
            maxLat = Float.parseFloat(bound.get(1));
            maxLng = Float.parseFloat(bound.get(3));
        } catch (NumberFormatException e) {return null;}
        if(minLat > maxLat) {
            float tmp = maxLat;
            maxLat = minLat;
            minLat = tmp;
        }
        if(minLng > maxLng) {
            float tmp = maxLng;
            maxLng = minLng;
            minLng = tmp;
        }
        return new Bounds(minLat,minLng,maxLat,maxLng);
    }

    public class Bounds{
        private float minLat, minLng, maxLat, maxLng;

        public Bounds(float minLat, float minLng, float maxLat, float maxLng) {
            this.minLat = minLat;
            this.minLng = minLng;
            this.maxLat = maxLat;
            this.maxLng = maxLng;
        }

        public float getMinLat() {
            return minLat;
        }

        public float getMinLng() {
            return minLng;
        }

        public float getMaxLat() {
            return maxLat;
        }

        public float getMaxLng() {
            return maxLng;
        }
    }
}

package com.ucam.bean;

import java.util.ArrayList;

public class GeoJsonPlacesBean {
    public ArrayList<GeoJsonFeatures> features;
    
    public GeoJsonPlacesBean(){
        features = new ArrayList<GeoJsonFeatures>();
    }

    public ArrayList<GeoJsonFeatures> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<GeoJsonFeatures> features) {
        this.features = features;
    }
    
    
}

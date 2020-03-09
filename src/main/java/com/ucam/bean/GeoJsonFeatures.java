package com.ucam.bean;

public class GeoJsonFeatures {
    public String type;
    public GeometryGeoJson geometry;
    public PropertiesGeoJson properties;
    
    public GeoJsonFeatures(){
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeometryGeoJson getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryGeoJson geometry) {
        this.geometry = geometry;
    }

    public PropertiesGeoJson getProperties() {
        return properties;
    }

    public void setProperties(PropertiesGeoJson properties) {
        this.properties = properties;
    }
    
    
    
}

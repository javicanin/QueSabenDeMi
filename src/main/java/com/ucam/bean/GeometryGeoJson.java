package com.ucam.bean;

public class GeometryGeoJson {
    public String type;
    public double[] coordinates;
    
    public GeometryGeoJson(){
        type = "";
        coordinates = new double[2];
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    
}

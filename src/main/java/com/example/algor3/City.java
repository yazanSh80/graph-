package com.example.algor3;



public class City {
    private String name;
    int x;
    int y;
    double lat ;
    double lon ;



    public City(String name, int x, int y , double lat , double lon) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.lat = lat ;
        this.lon = lon ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "city{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

}

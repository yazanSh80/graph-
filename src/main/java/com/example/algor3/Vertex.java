package com.example.algor3;

import java.util.*;

public class Vertex implements Comparable<Vertex> {

    private City city;
    private ArrayListt<Edge> adj;
    private boolean known;
    private Vertex prev;
    private double distance;
    private double cost; // New field for cost
    private double time; // New field for time
    public Vertex(City city) {
        this.city = city;
        this.adj = new ArrayListt<>();
    }

    public void addNeighbour(Edge edge) {
        this.adj.add(edge);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayListt<Edge> getAdj() {
        return adj;
    }

    public void setAdj(ArrayListt<Edge> adj) {
        this.adj = adj;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.distance, otherVertex.getDistance());
    }

}

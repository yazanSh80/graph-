package com.example.algor3;

import java.util.ArrayList;

public class Edge {

    private double dist; // Distance
    private double cost; // Cost
    private double time;  // Time
    private Vertex startVertex;
    private Vertex targetVertex;

    public Edge(Vertex startVertex, Vertex targetVertex, double weight, double cost, double time) {
        this.dist = weight;
        this.cost = cost;
        this.time = time;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return dist;
    }

    public void setWeight(double weight) {
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }
    public double getCost() {
        return cost;
    }

    public double getTime() {
        return time;
    }

}
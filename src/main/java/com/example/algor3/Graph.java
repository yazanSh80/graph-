package com.example.algor3;

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    public static ArrayListt<Vertex> cities = new ArrayListt<>();
    private static HashMap<String, Vertex> cityMap = new HashMap<>();

    // Dijkstra's algorithm to find the shortest path
    public void calculateShortestDistance(Vertex sourceVertex) {
        for (Vertex vertex : cities) {
            vertex.setDistance(Double.MAX_VALUE); // Set initial distance to infinity
            vertex.setCost(Double.MAX_VALUE); // Set initial cost to infinity
            vertex.setTime(Double.MAX_VALUE); // Set initial time to infinity
            vertex.setKnown(false); // Mark all vertices as unknown
            vertex.setPrev(null); // No previous vertex
        }

        sourceVertex.setDistance(0); // Distance to source is 0
        sourceVertex.setCost(0); // Cost to source is 0
        sourceVertex.setTime(0); // Time to source is 0

        while (true) {
            Vertex v = getSmallestUnknownVertexByDistance();
            if (v == null) break; // If no more unknown vertices, exit the loop

            v.setKnown(true); // Mark the current vertex as known

            // Update distances, costs, and times for neighbors
            for (Edge edge : v.getAdj()) {
                Vertex w = edge.getTargetVertex();
                if (!w.isKnown()) {
                    double newDist = v.getDistance() + edge.getWeight();
                    double newCost = v.getCost() + edge.getCost();
                    double newTime = v.getTime() + edge.getTime();
                    if (newDist < w.getDistance()) {
                        w.setDistance(newDist); // Update the distance
                        w.setPrev(v); // Set the previous vertex
                    }
                    if (newCost != w.getCost()) {
                        w.setCost(newCost); // Update the cost
                    }
                    if (newTime != w.getTime()) {
                        w.setTime(newTime); // Update the time
                    }
                }
            }
        }
    }

    public void calculateShortestCost(Vertex sourceVertex) {
        // Initialize all vertices
        for (Vertex vertex : cities) {
            vertex.setCost(Double.MAX_VALUE); // Set initial cost to infinity
            vertex.setTime(Double.MAX_VALUE); // Set initial time to infinity
            vertex.setDistance(Double.MAX_VALUE); // Set initial distance to infinity
            vertex.setKnown(false); // Mark all vertices as unknown
            vertex.setPrev(null); // No previous vertex
        }

        sourceVertex.setCost(0); // Cost to source is 0
        sourceVertex.setTime(0); // Time to source is 0
        sourceVertex.setDistance(0); // Distance to source is 0

        while (true) {
            Vertex v = getSmallestUnknownVertexByCost();
            if (v == null) break; // If no more unknown vertices, exit the loop

            v.setKnown(true); // Mark the current vertex as known

            // Update costs, distances, and times for neighbors
            for (Edge edge : v.getAdj()) {
                Vertex w = edge.getTargetVertex();
                if (!w.isKnown()) {
                    double newCost = v.getCost() + edge.getCost();
                    double newTime = v.getTime() + edge.getTime();
                    double newDistance = v.getDistance() + edge.getWeight();
                    if (newTime != w.getTime()) {
                        w.setTime(newTime); // Update the time
                        w.setPrev(v); // Set the previous vertex
                    }

                    // Update cost if the new cost is less
                    if (newCost < w.getCost()) {
                        w.setCost(newCost); // Update the cost
                    }

                    // Update distance if the new distance is less
                    if (newDistance != w.getDistance()) {
                        w.setDistance(newDistance); // Update the distance
                    }
                    // Update cost if the new cost is less

                }
            }
        }
    }

    public void calculateShortestTime(Vertex sourceVertex) {
        // Initialize all vertices
        for (Vertex vertex : cities) {
            vertex.setDistance(Double.MAX_VALUE); // Set initial distance to infinity
            vertex.setCost(Double.MAX_VALUE); // Set initial cost to infinity
            vertex.setTime(Double.MAX_VALUE); // Set initial time to infinity
            vertex.setKnown(false); // Mark all vertices as unknown
            vertex.setPrev(null); // No previous vertex
        }

        sourceVertex.setTime(0); // Time to source is 0
        sourceVertex.setCost(0); // Cost to source is 0
        sourceVertex.setDistance(0); // Distance to source is 0

        while (true) {
            Vertex v = getSmallestUnknownVertexByTime();
            if (v == null) break; // If no more unknown vertices, exit the loop

            v.setKnown(true); // Mark the current vertex as known

            // Update times, costs, and distances for neighbors
            for (Edge edge : v.getAdj()) {
                Vertex w = edge.getTargetVertex();
                if (!w.isKnown()) {
                    double newTime = v.getTime() + edge.getTime();
                    double newCost = v.getCost() + edge.getCost();
                    double newDistance = v.getDistance() + edge.getWeight();

                    // Update time if the new time is less
                    if (newCost != w.getCost()) {
                        w.setCost(newCost); // Update the cost
                        w.setPrev(v); // Set the previous vertex
                    }

                    // Update time if the new time is less
                    if (newTime < w.getTime()) {
                        w.setTime(newTime); // Update the time
                    }

                    // Update distance if the new distance is less
                    if (newDistance != w.getDistance()) {
                        w.setDistance(newDistance); // Update the distance
                    }
                }
            }
        }
    }

    // Read cities from the cities.txt file
    public static ArrayListt<Vertex> readCities(int size1, int size2) throws FileNotFoundException {
        InputStream inputStream = Graph.class.getResourceAsStream("/cities.txt");

        if (inputStream == null) {
            throw new FileNotFoundException("cities.txt not found in resources.");
        }

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            double x = (Double.parseDouble(tokens[1]) + 180) / 360.0;
            double y = (Double.parseDouble(tokens[2]) + 90) / 180.0;
            x *= size2;
            y *= size1;

            // Create the Vertex object and add it to both the list and HashMap
            Vertex vertex = new Vertex(new City(tokens[0], (int) x, (int) y, Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));
            cities.add(vertex);
            cityMap.put(tokens[0], vertex);  // Storing vertex by name in HashMap
        }

        readEdges();
        return cities;
    }

    public static void readEdges() throws FileNotFoundException {
        InputStream inputStream = Graph.class.getResourceAsStream("/data.txt");

        if (inputStream == null) {
            throw new FileNotFoundException("data.txt not found in resources.");
        }

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("-");

            // Check if the number of tokens is correct
            if (tokens.length < 4) {
                System.out.println("Invalid line format: " + line);
                continue; // Skip this line and move to the next
            }

            Vertex v1 = Search(tokens[0].trim());
            Vertex v2 = Search(tokens[1].trim());

            if (v1 == null) {
                System.out.println("Vertex " + tokens[0] + " not found.");
                continue;
            }
            if (v2 == null) {
                System.out.println("Vertex " + tokens[1] + " not found.");
                continue;
            }

            // Calculate the distance using the getDistance method
            Double distance = getDistance(v1, v2);
            if (distance == null) {
                System.out.println("Distance between " + tokens[0] + " and " + tokens[1] + " is invalid.");
                continue;
            }

            // Read cost and time from the tokens
            Double cost = null;
            Double time = null;
            try {
                cost = Double.parseDouble(tokens[2].trim()); // Assuming cost is in the third position
                time = Double.parseDouble(tokens[3].trim()); // Assuming time is in the fourth position
            } catch (NumberFormatException e) {
                System.out.println("Error parsing cost or time in line: " + line);
                continue;
            }

            // Create the edge with the calculated distance and read cost and time
            Edge e = new Edge(v1, v2, distance, cost, time);
            v1.addNeighbour(e);
        }
    }

    // Optimized search using the HashMap
    public static Vertex Search(String name) {
        return cityMap.get(name);  // Lookup the vertex by name in the HashMap
    }

    // Print the path from source to target using the prev pointer
    public ArrayListt<String> PrintPath(Vertex source, Vertex targetVertex) {
        ArrayListt<String> path = new ArrayListt<>();
        if (source.getCity().getName().equals(targetVertex.getCity().getName())) {
            path.add(source.getCity().getName() + " " + source.getDistance());
        } else if (targetVertex.getPrev() == null) {
            path.add("No Path");
        } else {
            for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPrev()) {
                path.add(vertex.getCity().getName() + " " + vertex.getDistance());
            }
            Collections.reverse(path);
        }
        return path;
    }

    public static Double getDistance(Vertex A, Vertex B) {
        if (A == null || B == null || A.getCity() == null || B.getCity() == null) {
            System.out.println("Either A, B, or their cities are null.");
            return null;
        }

        // Print out which cities are being compared
        System.out.println("Calculating distance between " + A.getCity().getName() + " and " + B.getCity().getName());

        // Get the geographical coordinates (latitude and longitude) of both cities
        double lat1 = Math.toRadians(A.getCity().getLat());
        double lon1 = Math.toRadians(A.getCity().getLon());
        double lat2 = Math.toRadians(B.getCity().getLat());
        double lon2 = Math.toRadians(B.getCity().getLon());

        // Haversine formula to calculate the distance between two points on the Earth
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radius = 6371; // Earth's radius in kilometers

        return radius * c;
    }

    public boolean containsVertex(String vertexName) {
        for (Vertex vertex : cities) {
            // Checking if the vertex's city name matches the given vertexName
            if (vertex.getCity().getName().equals(vertexName)) {
                return true; // Vertex found
            }
        }
        return false; // Vertex not found
    }
    private Vertex getSmallestUnknownVertexByDistance() {
        Vertex smallest = null;
        for (Vertex v : cities) {
            if (!v.isKnown() && (smallest == null || v.getDistance() < smallest.getDistance())) {
                smallest = v;
            }
        }
        return smallest;
    }

    private Vertex getSmallestUnknownVertexByCost() {
        Vertex smallest = null;
        for (Vertex v : cities) {
            if (!v.isKnown() && (smallest == null || v.getCost() < smallest.getCost())) {
                smallest = v;
            }
        }
        return smallest;
    }

    private Vertex getSmallestUnknownVertexByTime() {
        Vertex smallest = null;
        for (Vertex v : cities) {
            if (!v.isKnown() && (smallest == null || v.getTime() < smallest.getTime())) {
                smallest = v;
            }
        }
        return smallest;
    }

}
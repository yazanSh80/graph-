package com.example.algor3;

import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Driver extends Application {
    private String selectedCriteria = "Distance";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox(10);
        Image image = new Image(getClass().getResourceAsStream("/Map.jpg"));

        ImageView imageView = new ImageView(image);
        int height = 700;
        int width = 950;
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setLayoutX(7);
        imageView.setLayoutY(0);
        AnchorPane pane = new AnchorPane();
        pane.setPrefHeight(700);
        pane.setPrefWidth(1500);

        Label from = new Label("From");
        from.setFont(new Font("system", 14));
        from.setTextFill(Color.web("Black"));
        from.setStyle("-fx-font-weight: bold");
        from.setFont(Font.font("Baskerville Old Face", 22));
        from.setPrefWidth(100);
        from.setPrefHeight(30);

        ComboBox<String> comboBoxSource = new ComboBox<>();
        comboBoxSource.setPrefHeight(30);
        comboBoxSource.setPrefWidth(200);
        comboBoxSource.setEditable(true);
        comboBoxSource.setStyle("-fx-background-color: Gray;");
        comboBoxSource.setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");

        Label to = new Label("To");
        to.setFont(Font.font("Baskerville Old Face", 22));
        to.setTextFill(Color.web("Black"));
        to.setStyle("-fx-font-weight: bold");
        to.setPrefWidth(100);
        to.setPrefHeight(30);

        ComboBox<String> comboBoxTarget = new ComboBox<>();
        comboBoxTarget.setPrefHeight(30);
        comboBoxTarget.setPrefWidth(200);
        comboBoxTarget.setEditable(true);
        comboBoxTarget.setStyle("-fx-background-color: Gray;");
        comboBoxTarget.setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");

        Label pathLabel = new Label("Path");
        pathLabel.setFont(Font.font("Baskerville Old Face", 22));
        pathLabel.setPrefWidth(100);
        pathLabel.setPrefHeight(30);
        pathLabel.setTextFill(Color.web("Black"));
        pathLabel.setStyle("-fx-font-weight: bold");

        TextArea path = new TextArea();
        path.setPrefHeight(100);
        path.setPrefWidth(250);
        path.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");

        Label distanceLabel = new Label("Distance/Cost/Time");
        distanceLabel.setFont(Font.font("Baskerville Old Face", 22));
        distanceLabel.setTextFill(Color.web("Black"));
        distanceLabel.setStyle("-fx-font-weight: bold");
        distanceLabel.setPrefWidth(200);
        distanceLabel.setPrefHeight(30);

        TextArea distance = new TextArea();
        distance.setPrefHeight(75);
        distance.setPrefWidth(200);
        distance.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");

        Button runButton = new Button("Run");
        Button reset = new Button("Reset");

        // Set button styles
        runButton.setStyle("-fx-background-color: #ca85ff; -fx-text-fill: Black;");
        reset.setStyle("-fx-background-color: #ca85ff; -fx-text-fill: Black;");

        ComboBox<String> criteriaComboBox = new ComboBox<>();
        criteriaComboBox.getItems().addAll("Shortest Distance", "Shortest Cost", "Shortest Time");
        criteriaComboBox.setPrefHeight(30);
        criteriaComboBox.setPrefWidth(200);
        criteriaComboBox.setStyle("-fx-background-color: Gray;");
        criteriaComboBox.setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");


        // Layout for criteria buttons
        HBox criteriaBox = new HBox(10);
        criteriaBox.getChildren().addAll(criteriaComboBox);

        // Layout for run and reset buttons
        HBox actionBox = new HBox(20);
        actionBox.getChildren().addAll(runButton, reset);

        Label filter = new Label("Filter");
        filter.setFont(new Font("system", 14));
        filter.setTextFill(Color.web("Black"));
        filter.setStyle("-fx-font-weight: bold");
        filter.setFont(Font.font("Baskerville Old Face", 22));
        filter.setPrefWidth(100);
        filter.setPrefHeight(30);
        // Add components to the VBox
        vBox.getChildren().addAll(from, comboBoxSource, to, comboBoxTarget, filter,criteriaComboBox, actionBox , pathLabel,path, distanceLabel, distance);
        vBox.setLayoutX(1000);
        vBox.setLayoutY(10);

        pane.getChildren().addAll(vBox, imageView);
        Group g = new Group();
        g.getChildren().addAll(pane, imageView);
        ArrayListt<Vertex> cities = Graph.readCities(height, width);

        for (int i = 0; i < cities.size(); i++) {
            comboBoxSource.getItems().add(cities.get(i).getCity().getName());
            comboBoxTarget.getItems().add(cities.get(i).getCity().getName());
        }
        ArrayListt<RadioButton> array = new ArrayListt<>();
        for (int i = 0; i < cities.size(); i++) {
            RadioButton radioButton = new RadioButton(cities.get(i).getCity().getName());
            radioButton.setOnMouseClicked(e -> {
                for (int j = 0; j < array.size(); j++) {
                    array.get(j).setSelected(false);
                }
                if (comboBoxSource.getValue() == null || comboBoxSource.getValue().equals("")) {
                    radioButton.setSelected(true);
                    comboBoxSource.setValue(radioButton.getId());
                } else if (comboBoxTarget.getValue() == null || comboBoxTarget.getValue().equals("")) {
                    radioButton.setSelected(true);
                    comboBoxTarget.setValue(radioButton.getId());
                }
            });
            radioButton.setId(cities.get(i).getCity().getName());
            radioButton.setLayoutX(cities.get(i).getCity().getX());
            radioButton.setLayoutY(height - cities.get(i).getCity().getY());
            radioButton.setTextFill(Color.BLACK);
            radioButton.setStyle("-fx-font-size:7px;" + "-fx-font-weight: bold");
            array.add(radioButton);
            g.getChildren().addAll(radioButton);
        }

        primaryStage.setScene(new Scene(g, 1500, 700));
        primaryStage.show();

        reset.setOnAction(e -> {
            deleteingLines(g);
            comboBoxSource.setValue("");
            comboBoxTarget.setValue("");
            path.clear();
            criteriaComboBox.setValue("");
            distance.clear();
        });

        runButton.setOnAction(e -> {
            Graph graph= new Graph();
            // Clear previous lines and reset the UI
            deleteingLines(g);

            // Get the source and target city names from the combo boxes
            if (comboBoxSource.getValue() == null || comboBoxTarget.getValue() == null) {
                // Show an error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Missing Selection");
                alert.setContentText("Please select both a source and a target city before proceeding.");
                alert.showAndWait();
            } else {
                // Get the source and target city names
                String sourceName = comboBoxSource.getValue().trim();
                String targetName = comboBoxTarget.getValue().trim();
                selectedCriteria = criteriaComboBox.getValue(); // Get selected criteria

                // Check if both source and target vertices exist
                if (graph.containsVertex(sourceName) && graph.containsVertex(targetName)) {
                    Vertex sourceVertex = Graph.Search(sourceName);
                    Vertex targetVertex = Graph.Search(targetName);

                    // Calculate based on selected criteria
                    if (selectedCriteria == null) {
                        // Show an error alert
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No Criteria Selected");
                        alert.setContentText("Please select a criterion (Shortest Distance, Shortest Cost, or Shortest Time) before proceeding.");
                        alert.showAndWait();
                    } else {
                        // Proceed with the calculation based on the selected criteria
                        if (selectedCriteria.equals("Shortest Distance")) {
                            graph.calculateShortestDistance(sourceVertex); // Calculate based on distance
                        } else if (selectedCriteria.equals("Shortest Cost")) {
                            graph.calculateShortestCost(sourceVertex); // Calculate based on cost
                        } else if (selectedCriteria.equals("Shortest Time")) {
                            graph.calculateShortestTime(sourceVertex); // Calculate based on time
                        }
                    }


                    // Create a string builder to concatenate the results
                    StringBuilder resultString = new StringBuilder();
                    resultString.append("Distance: ")
                            .append(targetVertex.getDistance())
                            .append(" KM")
                            .append("\n")
                            .append("Cost: ")
                            .append(targetVertex.getCost())
                            .append(" $")
                            .append("\n")
                            .append("Time: ")
                            .append(targetVertex.getTime())
                            .append(" Hours")
                            .append("\n"); // Optional: Add an extra newline for better spacing

                    // Set the concatenated result to the distance text field
                    distance.setText(resultString.toString());

                    // Get the path and display it
                    ArrayListt<String> pathSegments = graph.PrintPath(sourceVertex, targetVertex);
                    StringBuilder pathString = new StringBuilder();
                    for (String pathSegment : pathSegments) {
                        pathString.append("->").append(pathSegment).append(" \n");
                    }
                    path.setText(pathString.toString());

                    // Update the graphical representation of the path
                    recScenes(targetVertex, g, height, pathLines); // Pass the pathLines list
                } else {
                    // Show an error message if either of the vertices is not found
                    System.out.println("One or both of the vertices were not found.");
                }
            }
        });
    }
    private ArrayList<Line> pathLines = new ArrayList<>();

    public static Group recScenes(Vertex target, Group myPane, int height, ArrayList<Line> pathLines) {
        while (target != null) {
            Vertex prev = target.getPrev();
            if (prev == null)
                break;

            Line line = new Line(target.getCity().getX() + 5, height - target.getCity().getY() + 5,
                    prev.getCity().getX() + 5, height - prev.getCity().getY() + 5);
            line.setStyle("-fx-stroke: Black; -fx-stroke-width: 2px;");
            myPane.getChildren().add(line);
            pathLines.add(line); // Add the line to the list
            target = prev;
        }
        return myPane;
    }

    public void deleteingLines(Group myPane) {
        if (myPane == null || pathLines == null) {
            // Handle the case where the Group or pathLines is null
            System.out.println("Error: Group or pathLines is null");
            return;
        }

        for (Line line : pathLines) {
            myPane.getChildren().remove(line);
        }
        pathLines.clear(); // Clear the list after removing the lines
    }

}
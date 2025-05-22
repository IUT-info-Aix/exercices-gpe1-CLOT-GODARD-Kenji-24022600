package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création des sliders
        Slider sliderCelsius = new Slider(0, 100, 0);
        sliderCelsius.setOrientation(Orientation.VERTICAL);
        sliderCelsius.setShowTickLabels(true);
        sliderCelsius.setShowTickMarks(true);
        sliderCelsius.setMajorTickUnit(10);
        sliderCelsius.setMinorTickCount(1);
        sliderCelsius.setPrefHeight(400);

        Slider sliderFahrenheit = new Slider(32, 212, 32);
        sliderFahrenheit.setOrientation(Orientation.VERTICAL);
        sliderFahrenheit.setShowTickLabels(true);
        sliderFahrenheit.setShowTickMarks(true);
        sliderFahrenheit.setMajorTickUnit(20);
        sliderFahrenheit.setMinorTickCount(1);
        sliderFahrenheit.setPrefHeight(400);

        // Création des champs de texte
        TextField textFieldCelsius = new TextField("0.00");
        textFieldCelsius.setPrefWidth(80);

        TextField textFieldFahrenheit = new TextField("32.00");
        textFieldFahrenheit.setPrefWidth(80);

        // 1. Binding bidirectionnel entre les sliders
        // Comme il n'existe pas de binding bidirectionnel direct avec conversion,
        // on utilise des listeners avec un flag pour éviter les cycles infinis
        final boolean[] isUpdating = {false};

        sliderCelsius.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!isUpdating[0]) {
                isUpdating[0] = true;
                double fahrenheit = newVal.doubleValue() * 9.0/5.0 + 32;
                sliderFahrenheit.setValue(fahrenheit);
                isUpdating[0] = false;
            }
        });

        sliderFahrenheit.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!isUpdating[0]) {
                isUpdating[0] = true;
                double celsius = (newVal.doubleValue() - 32) * 5.0/9.0;
                sliderCelsius.setValue(celsius);
                isUpdating[0] = false;
            }
        });

        // 2. Binding bidirectionnel entre les TextFields et les Sliders
        Bindings.bindBidirectional(
                textFieldCelsius.textProperty(),
                sliderCelsius.valueProperty(),
                new NumberStringConverter("#0.00")
        );

        Bindings.bindBidirectional(
                textFieldFahrenheit.textProperty(),
                sliderFahrenheit.valueProperty(),
                new NumberStringConverter("#0.00")
        );

        // Création des labels
        Label labelCelsius = new Label("°C");
        Label labelFahrenheit = new Label("°F");

        // Organisation des panneaux
        VBox panneauCelsius = new VBox(10);
        panneauCelsius.getChildren().addAll(labelCelsius, sliderCelsius, textFieldCelsius);
        panneauCelsius.setSpacing(10);

        VBox panneauFahrenheit = new VBox(10);
        panneauFahrenheit.getChildren().addAll(labelFahrenheit, sliderFahrenheit, textFieldFahrenheit);
        panneauFahrenheit.setSpacing(10);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Convertisseur de Températures");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
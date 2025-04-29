package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPrefSize(400, 200);

        //Label
        Label label = new Label("Cliquez sur un bouton");
        BorderPane.setAlignment(label, Pos.CENTER);
        root.setTop(label);

        //Pane
        Pane panneau = new Pane();
        root.setCenter(panneau);

        //Buttons
        Button vert = new Button("Vert");
        Button rouge = new Button("Rouge");
        Button bleu = new Button("Bleu");

        //Hbox
        HBox bas = new HBox(10, vert, rouge, bleu);
        bas.setAlignment(Pos.CENTER); // Centrer les boutons
        bas.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        root.setBottom(bas);

        vert.setOnAction(e -> {
            nbVert++;
            panneau.setStyle("-fx-background-color: #31bba3");
            label.setText("Vert choisi " + nbVert + " fois");
        });

        rouge.setOnAction(e -> {
            nbRouge++;
            panneau.setStyle("-fx-background-color: red");
            label.setText("Rouge choisi " + nbRouge + " fois");
        });

        bleu.setOnAction(e -> {
            nbBleu++;
            panneau.setStyle("-fx-background-color: blue");
            label.setText("Bleu choisi " + nbBleu + " fois");
        });

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


package fr.amu.iut.exercice12;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    private Label texteDuHaut;
    private Label texteDuBas;
    private Pane panneau;
    private CustomButton boutonVert;
    private CustomButton boutonRouge;
    private CustomButton boutonBleu;

    // Référence vers le dernier bouton cliqué
    private CustomButton sourceOfEvent;

    @Override
    public void start(Stage primaryStage) {
        // Construction de l'interface
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Label du haut
        texteDuHaut = new Label("Cliquez sur un bouton");
        texteDuHaut.setAlignment(Pos.CENTER);
        root.setTop(texteDuHaut);
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        // Panneau central qui changera de couleur
        panneau = new Pane();
        panneau.setPrefSize(300, 200);
        root.setCenter(panneau);

        // Boutons personnalisés en bas
        HBox boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);

        boutonVert = new CustomButton("Vert", "#00FF00");
        boutonRouge = new CustomButton("Rouge", "#FF0000");
        boutonBleu = new CustomButton("Bleu", "#0000FF");

        boutons.getChildren().addAll(boutonVert, boutonRouge, boutonBleu);

        // Label du bas
        texteDuBas = new Label();
        texteDuBas.setAlignment(Pos.CENTER);

        // Layout vertical pour placer les boutons et le texte du bas
        BorderPane bottomPane = new BorderPane();
        bottomPane.setTop(boutons);
        bottomPane.setBottom(texteDuBas);
        BorderPane.setAlignment(boutons, Pos.CENTER);
        BorderPane.setAlignment(texteDuBas, Pos.CENTER);
        bottomPane.setPadding(new Insets(10));

        root.setBottom(bottomPane);

        // Créer le gestionnaire d'événements pour les boutons
        EventHandler<ActionEvent> gestionnaireEvenement = event -> {
            // Récupérer le bouton cliqué
            sourceOfEvent = (CustomButton) event.getSource();

            // Incrémenter le compteur interne au bouton
            sourceOfEvent.setNbClics(sourceOfEvent.getNbClics() + 1);
        };

        // Associer le gestionnaire d'événement aux boutons
        boutonVert.setOnAction(gestionnaireEvenement);
        boutonRouge.setOnAction(gestionnaireEvenement);
        boutonBleu.setOnAction(gestionnaireEvenement);

        // Créer le listener pour les compteurs de clics
        ChangeListener<Number> nbClicsListener = (observable, oldValue, newValue) -> {
            // Mettre à jour le texte du haut
            if (sourceOfEvent != null) {
                texteDuHaut.setText(sourceOfEvent.getText() + " choisi " + newValue + " fois");

                // Mettre à jour la couleur du panneau
                panneau.setStyle("-fx-background-color: " + sourceOfEvent.getCouleur() + ";");

                // Mettre à jour le texte du bas
                texteDuBas.setText("Le " + sourceOfEvent.getText() + " est une jolie couleur !");
                texteDuBas.setStyle("-fx-text-fill: " + sourceOfEvent.getCouleur() + ";");
            }
        };

        // Associer le listener aux compteurs des boutons
        boutonVert.nbClicsProperty().addListener(nbClicsListener);
        boutonRouge.nbClicsProperty().addListener(nbClicsListener);
        boutonBleu.nbClicsProperty().addListener(nbClicsListener);

        // Configuration et affichage de la scène
        Scene scene = new Scene(root, 500, 350);
        primaryStage.setTitle("Palette de couleurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
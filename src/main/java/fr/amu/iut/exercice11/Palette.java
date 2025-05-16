package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private Label texteDuHaut;
    private Label texteDuBas;
    private Pane panneau;
    private Button boutonVert;
    private Button boutonRouge;
    private Button boutonBleu;

    // Propriétés demandées dans l'exercice
    private IntegerProperty nbFois;
    private StringProperty message;
    private StringProperty couleurPanneau;
    private StringProperty couleurChoisie;

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

        // Boutons en bas
        HBox boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);

        boutonVert = new Button("Vert");
        boutonRouge = new Button("Rouge");
        boutonBleu = new Button("Bleu");

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

        // Initialisation des propriétés
        nbFois = new SimpleIntegerProperty(0);
        message = new SimpleStringProperty("");
        couleurPanneau = new SimpleStringProperty("#000000");
        couleurChoisie = new SimpleStringProperty("Rouge"); // Valeur par défaut

        // Création des bindings
        createBindings();

        // Définition des gestionnaires d'événements
        boutonVert.setOnAction(event -> {
            nbFois.set(nbFois.get() + 1);
            message.set("Le Vert est une jolie couleur !");
            couleurPanneau.set("#00FF00");
            couleurChoisie.set("Vert");
        });

        boutonRouge.setOnAction(event -> {
            nbFois.set(nbFois.get() + 1);
            message.set("Le Rouge est une jolie couleur !");
            couleurPanneau.set("#FF0000");
            couleurChoisie.set("Rouge");
        });

        boutonBleu.setOnAction(event -> {
            nbFois.set(nbFois.get() + 1);
            message.set("Le Bleu est une jolie couleur !");
            couleurPanneau.set("#0000FF");
            couleurChoisie.set("Bleu");
        });

        // Configuration et affichage de la scène
        Scene scene = new Scene(root, 500, 350);
        primaryStage.setTitle("Palette de couleurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createBindings() {
        // Variable pour déterminer si un clic a été effectué
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty();
        pasEncoreDeClic.bind(Bindings.equal(nbFois, 0));

        // Binding pour le texte du haut
        texteDuHaut.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("Cliquez sur un bouton")
                        .otherwise(Bindings.concat(
                                couleurChoisie, " choisi ",
                                nbFois.asString(), " fois"
                        ))
        );

        // Binding pour le style du panneau
        panneau.styleProperty().bind(
                Bindings.concat("-fx-background-color: ", couleurPanneau, ";")
        );

        // Binding pour le label du bas (point 7)
        texteDuBas.textProperty().bind(message);
        texteDuBas.styleProperty().bind(
                Bindings.concat("-fx-text-fill: ", couleurPanneau, ";")
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
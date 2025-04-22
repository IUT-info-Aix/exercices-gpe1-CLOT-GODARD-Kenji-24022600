package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        Scene scene = new Scene(root, 800, 500);

        //Menu bar
        Menu menu1 = new Menu("File");
        MenuItem _New = new MenuItem("New");
        MenuItem _Open = new MenuItem("Open");
        MenuItem _Save = new MenuItem("Save");
        MenuItem _SaveAs = new MenuItem("Close");

        menu1.getItems().addAll(_New, _Open, _Save, _SaveAs);

        Menu menu2 = new Menu("Edit");
        MenuItem _Cut = new MenuItem("Cut");
        MenuItem _Copy = new MenuItem("Copy");
        MenuItem _Paste = new MenuItem("Paste");

        menu2.getItems().addAll(_Cut, _Copy,_Paste);

        Menu menu3 = new Menu("Help");

        MenuBar menuBar = new MenuBar(menu1, menu2, menu3);
        root.setTop(menuBar);

        //Buttons
        Button button1 = new Button("Bouton 1");
        Button button2 = new Button("Bouton 2");
        Button button3 = new Button("Bouton 3");
        VBox buttons = new VBox(new Label("Boutons :"),button1, button2, button3);
        buttons.setSpacing(10.0);
        buttons.setAlignment(Pos.CENTER);
        root.setLeft(buttons);

        //GridPane
        GridPane gridpane = new GridPane();
        gridpane.add(new Label("Name:"),0,0); gridpane.add(new TextField(),1,0);
        gridpane.add(new Label("Email:"),0,1); gridpane.add(new TextField(),1,1);
        gridpane.add(new Label("Password:"),0,2); gridpane.add(new TextField(),1,2);
        gridpane.setHgap(5);
        gridpane.setVgap(10);
        gridpane.setAlignment(Pos.CENTER);
        root.setCenter(gridpane);

        //Button under GridPane
        Button button4 = new Button("Submit");
        Button button5 = new Button("Cancel");
        HBox buttonsForm = new HBox(button4, button5);
        root.setBottom(buttonsForm);

        //Label
        HBox basDePages = new HBox(new Label("Ceci est un label de bas de page"));
        basDePages.setAlignment(Pos.CENTER);
        root.setBottom(basDePages);


        // Définir la scène principale de l'application
        primaryStage.setScene(scene);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.show();
    }


        public static void main (String[]args){
            launch(args);
    }
}


package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginControl extends GridPane implements Initializable {

    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createBindings();
    }

    private void createBindings() {
        // 1. Désactiver l'édition du mot de passe si identifiant < 6 caractères
        pwd.editableProperty().bind(userId.textProperty().length().greaterThanOrEqualTo(6));

        // 2. Bouton Cancel désactivé si les deux champs sont vides
        BooleanBinding champsVides = userId.textProperty().isEmpty().and(pwd.textProperty().isEmpty());
        cancelButton.disableProperty().bind(champsVides);

        // 3. Bouton OK désactivé tant que :
        //    - mot de passe < 8 caractères
        //    - pas de majuscule
        //    - pas de chiffre

        BooleanBinding motDePasseValide = Bindings.createBooleanBinding(() -> {
            String pass = pwd.getText();
            return pass.length() >= 8 &&
                    pass.matches(".*[A-Z].*") &&
                    pass.matches(".*[0-9].*");
        }, pwd.textProperty());

        okButton.disableProperty().bind(motDePasseValide.not());
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}

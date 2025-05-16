package fr.amu.iut.exercice12;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

/**
 * Bouton personnalisé qui inclut un compteur de clics et une couleur
 */
public class CustomButton extends Button {

    private IntegerProperty nbClics;
    private String couleur;

    /**
     * Constructeur du bouton personnalisé
     * @param texte Texte à afficher sur le bouton
     * @param couleur Code couleur hexadécimal
     */
    public CustomButton(String texte, String couleur) {
        super(texte);
        this.couleur = couleur;
        nbClics = new SimpleIntegerProperty(0);
    }

    /**
     * Getter pour la propriété nbClics
     * @return La propriété nbClics
     */
    public IntegerProperty nbClicsProperty() {
        return nbClics;
    }

    /**
     * Getter pour la valeur de nbClics
     * @return La valeur actuelle de nbClics
     */
    public int getNbClics() {
        return nbClics.get();
    }

    /**
     * Setter pour la valeur de nbClics
     * @param nbClics La nouvelle valeur de nbClics
     */
    public void setNbClics(int nbClics) {
        this.nbClics.set(nbClics);
    }

    /**
     * Getter pour la couleur
     * @return Le code couleur hexadécimal
     */
    public String getCouleur() {
        return couleur;
    }
}
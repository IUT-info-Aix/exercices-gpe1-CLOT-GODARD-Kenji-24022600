package fr.amu.iut.exercice14;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    private static IntegerBinding calculAgeMoyen;
    private static IntegerBinding calculNbParisiens;

    public static void main(String[] args) {

        lesPersonnes = new SimpleListProperty<>(FXCollections.observableArrayList());
        ageMoyen = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0);

        // BINDING POUR L'AGE MOYEN
        calculAgeMoyen = new IntegerBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((javafx.collections.ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded() || change.wasRemoved()) {
                            for (Personne p : change.getAddedSubList()) {
                                bind(p.ageProperty());
                                p.ageProperty().addListener((obs, oldVal, newVal) -> invalidate());
                            }
                            for (Personne p : change.getRemoved()) {
                                unbind(p.ageProperty());
                            }
                        }
                    }
                    invalidate();
                });
            }

            @Override
            protected int computeValue() {
                if (lesPersonnes.isEmpty()) return 0;
                int somme = 0;
                for (Personne p : lesPersonnes)
                    somme += p.getAge();
                return somme / lesPersonnes.size();
            }
        };

        ageMoyen.bind(calculAgeMoyen);

        // BINDING POUR LE NOMBRE DE PARISIENS
        calculNbParisiens = new IntegerBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((javafx.collections.ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded() || change.wasRemoved()) {
                            for (Personne p : change.getAddedSubList()) {
                                bind(p.villeDeNaissanceProperty());
                                p.villeDeNaissanceProperty().addListener((obs, oldVal, newVal) -> invalidate());
                            }
                            for (Personne p : change.getRemoved()) {
                                unbind(p.villeDeNaissanceProperty());
                            }
                        }
                    }
                    invalidate();
                });
            }

            @Override
            protected int computeValue() {
                int count = 0;
                for (Personne p : lesPersonnes) {
                    if (p.getVilleDeNaissance().equals("Paris"))
                        count++;
                }
                return count;
            }
        };

        nbParisiens.bind(calculNbParisiens);

        question1();
        question2();
    }

    public static void question1() {
        System.out.println("1 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne pierre = new Personne("Pierre", 20);
        lesPersonnes.add(pierre);
        System.out.println("2 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne paul = new Personne("Paul", 40);
        lesPersonnes.add(paul);
        System.out.println("3 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(jacques);
        System.out.println("4 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        paul.setAge(100);
        System.out.println("5 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        lesPersonnes.remove(paul);
        System.out.println("6 - L'age moyen est de " + ageMoyen.getValue() + " ans");
    }

    public static void question2() {
        System.out.println("Il y a " + nbParisiens.getValue() + " parisien(s)");
        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisien(s)");
        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisien(s)");
        for (Personne p : lesPersonnes)
            p.setVilleDeNaissance("Paris");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisien(s)");
    }

}

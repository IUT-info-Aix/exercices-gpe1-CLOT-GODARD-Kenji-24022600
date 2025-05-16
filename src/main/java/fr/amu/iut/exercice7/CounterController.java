package fr.amu.iut.exercice7;



import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CounterController {

    @FXML
    private Label labelCounter;

    private int counter = 0;

    @FXML
    public void initialize() {
        labelCounter.setText(String.valueOf(counter));
    }

    @FXML
    private void increment() {
        counter++;
        labelCounter.setText(String.valueOf(counter));
    }

    @FXML
    private void decrement() {
        counter--;
        labelCounter.setText(String.valueOf(counter));
    }
}

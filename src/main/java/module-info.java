 module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    exports com.example.partie1;
    exports com.example.partie2;
    opens com.example.partie3 to javafx.fxml;
    exports com.example.partie3;
    opens fr.amu.iut.exercice7 to javafx.fxml;
    exports fr.amu.iut.exercice7;
    exports fr.amu.iut.exercice11;
    exports fr.amu.iut.exercice12;
    exports fr.amu.iut.exercice13;
    exports fr.amu.iut.exercice14;
    exports fr.amu.iut.exercice15;
    opens fr.amu.iut.exercice15 to javafx.fxml;
    exports fr.amu.iut.exercice16 to javafx.graphics, javafx.fxml;
}

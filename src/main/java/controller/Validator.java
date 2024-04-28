package controller;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.Period;

public class Validator {
    public static boolean isEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (email.matches(emailRegex)) {
            System.out.println("Valid email");
            return true;
        }
        return false;
    }

    public static void alert(String HeaderText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(HeaderText);
        alert.showAndWait();
        return;
    }

    public static boolean validDateOfBirth(LocalDate localDate) {
        LocalDate currentDate = LocalDate.now();

        Period age = Period.between(localDate, currentDate);
        if (age.getYears() < 10) {
            return false;
        }
        return true;
    }
}

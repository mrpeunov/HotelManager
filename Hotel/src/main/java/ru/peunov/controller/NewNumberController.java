package ru.peunov.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import ru.peunov.enums.NumberClass;
import ru.peunov.model.NumberManager;
import ru.peunov.model.PersonalManager;
import ru.peunov.redefined.NumberTextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewNumberController implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {  }

    @FXML
    private static Stage stage;

    @FXML
    private NumberTextField capacity;

    @FXML
    private NumberTextField cost;

    @FXML
    private ChoiceBox<String> typeNumber;

    public static void setStage(Stage stage){
        NewNumberController.stage = stage;
    }

    @FXML
    public void newNumber(){
        try {
            String costText = cost.getText();
            String capacityText = capacity.getText();
            if(costText.length() != 0 && capacityText.length() != 0){
                NumberClass numberClass = NumberClass.parseNumberClass(typeNumber.getValue());
                int costInt = Integer.parseInt(costText);
                int capacityInt = Integer.parseInt(capacityText);
                NumberManager numberManager = NumberManager.getInstance();
                numberManager.addNumber(capacityInt, costInt, numberClass);
                stage.close();
            } else {
                if (costText.length() == 0) cost.setStyle("-fx-border-color: red;");
                else cost.setStyle("-fx-border-color: inherit;");

                if (capacityText.length() == 0) capacity.setStyle("-fx-border-color: red;");
                else capacity.setStyle("-fx-border-color: inherit;");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

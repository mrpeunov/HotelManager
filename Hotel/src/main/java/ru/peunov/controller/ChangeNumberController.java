package ru.peunov.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.NumberDAO;
import ru.peunov.enums.NumberClass;
import ru.peunov.model.NumberManager;
import ru.peunov.model.Number;
import ru.peunov.redefined.NumberTextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeNumberController implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(id);
        NumberDAO numberDAO = new NumberDAO(HibernateUtil.getSessionFactory());
        Number number = numberDAO.read(id);
        capacity.setText(String.valueOf(number.getCapacity()));
        cost.setText(String.valueOf(number.getPrice()));
        typeNumber.setValue(NumberClass.getString(number.getNumberClass()));
    }

    @FXML
    private static Stage stage;

    protected static long id;

    @FXML
    private NumberTextField capacity;

    @FXML
    private NumberTextField cost;

    @FXML
    private ChoiceBox<String> typeNumber;

    public static void setStage(Stage stage){
        ChangeNumberController.stage = stage;
    }

    @FXML
    public void changeNumber(){
        try{
            String costText = cost.getText();
            String capacityText = capacity.getText();
            if(costText.length() != 0 && capacityText.length() != 0){
                NumberClass numberClass = NumberClass.parseNumberClass(typeNumber.getValue());
                int costInt = Integer.parseInt(costText);
                int capacityInt = Integer.parseInt(capacityText);
                NumberManager numberManager = NumberManager.getInstance();
                numberManager.updateNumber(id, capacityInt, costInt, numberClass);
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

package ru.peunov.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.peunov.enums.Position;
import ru.peunov.model.PersonalManager;
import java.net.URL;
import java.util.ResourceBundle;




public class NewWorkerController implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private TextField fio;

    @FXML
    private TextField salary;

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private static Stage stage;

    @FXML
    public void newWorker(){
        try {
            String fioText = fio.getText();
            String salaryText = salary.getText();
            if(salaryText.length() != 0 && choice.getValue() != null && fioText.length() != 0){
                Position position = Position.parsePosition(choice.getValue());
                int salaryInt = Integer.parseInt(salaryText);
                PersonalManager personalManager = PersonalManager.getInstance();
                personalManager.makeNewWorker(fioText, salaryInt, position);
                stage.close();
            }
            else{
                if(fioText.length() == 0) fio.setStyle("-fx-border-color: red;");
                else fio.setStyle("-fx-border-color: inherit;");

                if(salaryText.length() == 0) salary.setStyle("-fx-border-color: red;");
                else salary.setStyle("-fx-border-color: inherit;");

                if(choice.getValue() == null) choice.setStyle("-fx-border-color: red;");
                else choice.setStyle("-fx-border-color: inherit;");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setStage(Stage stage){
        NewWorkerController.stage = stage;
    }
}

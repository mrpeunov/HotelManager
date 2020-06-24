package ru.peunov.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.peunov.HibernateUtil;
import ru.peunov.Main;
import ru.peunov.dao.WorkerDAO;
import ru.peunov.enums.Position;
import ru.peunov.model.PersonalManager;
import ru.peunov.model.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class NewWorkerController implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {

    }

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
                NewWorkerController.stage.close();
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

    @FXML
    public void newWorkerWindow(){
        try{
            Stage dialogWindow = new Stage();
            NewWorkerController.stage = dialogWindow;
            dialogWindow.initModality(Modality.APPLICATION_MODAL);
            dialogWindow.setTitle("Создать работника");
            String fxmlFile = "/fxml/windowNewWorker.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
            dialogWindow.setScene(new Scene(root));
            dialogWindow.setResizable(false);
            dialogWindow.show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void newReservation(){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        //нужно дописать и обрать отсюда нахер
    }


}

package ru.peunov.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.*;
import ru.peunov.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void newWorkerWindow(){
        try{
            Stage dialogWindow = new Stage();
            NewWorkerController.setStage(dialogWindow);
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

    @FXML
    public void newReservationWindow(){
        try{
            Stage dialogWindow = new Stage();
            NewReservationController.setStage(dialogWindow);
            dialogWindow.initModality(Modality.APPLICATION_MODAL);
            dialogWindow.setTitle("Создать бронирование");
            String fxmlFile = "/fxml/windowNewReservation.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
            dialogWindow.setScene(new Scene(root));
            dialogWindow.setResizable(false);
            dialogWindow.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void newNumberWindow(){
        try{
            Stage dialogWindow = new Stage();
            NewNumberController.setStage(dialogWindow);
            dialogWindow.initModality(Modality.APPLICATION_MODAL);
            dialogWindow.setTitle("Создать номер");
            String fxmlFile = "/fxml/windowNewNumber.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
            dialogWindow.setScene(new Scene(root));
            dialogWindow.setResizable(false);
            dialogWindow.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }







}

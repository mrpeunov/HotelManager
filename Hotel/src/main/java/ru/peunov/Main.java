package ru.peunov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import ru.peunov.controller.NewWorkerController;
import ru.peunov.dao.*;
import ru.peunov.enums.*;
import ru.peunov.model.*;
import ru.peunov.model.Number;

import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Hotel hotel = new Hotel();
        launch(args);
        System.exit(0);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/template.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Hotel Manager");
        //stage.setMaximized(true);
        stage.setResizable(false);
        stage.setScene(new Scene(root));

        stage.show();
    }


}

package ru.peunov.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.*;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.NumberDAO;
import ru.peunov.enums.NumberClass;
import ru.peunov.enums.Position;
import ru.peunov.model.*;
import ru.peunov.model.Number;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    public Label title;

    @FXML
    public ScrollPane scrollPane;

    @FXML VBox mainField;

    @FXML
    public void newWorkerWindow(){
        mainField.getChildren().clear();
        title.setText("");
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
        mainField.getChildren().clear();
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
        mainField.getChildren().clear();
        title.setText("");
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

    @FXML
    public void showCurrentWorker(){
        title.setText("Текущие работники");
        PersonalManager personalManager = PersonalManager.getInstance();
        //поправить
        List<Worker> personal = personalManager.getPersonal();
        showWorker(personal, false);
    }

    @FXML
    public void showAllWorker() {
        title.setText("Все работники");
        PersonalManager personalManager = PersonalManager.getInstance();
        List<Worker> personal = personalManager.getPersonal();
        showWorker(personal, true);
    }


    public void showWorker(List<Worker> personal, boolean all){
        mainField.getChildren().clear();
        GridPane gridPane = new GridPane();
        int i = 0;
        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(340), new ColumnConstraints(200),
                new ColumnConstraints(100), new ColumnConstraints(150),
                new ColumnConstraints(120), new ColumnConstraints(100));

        Label fioTitle = new Label("ФИО");
        Label positionTitle = new Label("Должность");
        Label salaryTitle = new Label("Зарплата");

        fioTitle.setStyle("-fx-font-size: 18px;");
        positionTitle.setStyle("-fx-font-size: 18px;");
        salaryTitle.setStyle("-fx-font-size: 18px;");

        gridPane.add(fioTitle, 0, i);
        gridPane.add(positionTitle, 1, i);
        gridPane.add(salaryTitle, 2, i);

        for(Worker worker : personal){
            i++;
            gridPane.getRowConstraints().add(new RowConstraints(40));
            Label fio = new Label(String.valueOf(worker.getName()));
            Label position = new Label(Position.getString(worker.getPosition()));
            Label salary = new Label(String.valueOf(worker.getSalary()));
            Button information = new Button("Вся информация");
            Button change = new Button("Изменить");
            Button delete = new Button("Удалить");

            fio.setStyle("-fx-font-size: 18px;");
            position.setStyle("-fx-font-size: 18px;");
            salary.setStyle("-fx-font-size: 18px;");

            change.setOnAction(a -> changeWorker(worker.getId() ));
            delete.setOnAction(a -> {deleteWorker(worker.getId(), all); });
            information.setOnAction(a -> informationWorker(worker.getId()));

            gridPane.setMargin(change, new Insets(0, 0, 0, 20));
            gridPane.setMargin(delete, new Insets(0, 0, 0, 20));

            gridPane.add(fio, 0, i);
            gridPane.add(position, 1, i);
            gridPane.add(salary, 2, i);
            gridPane.add(information, 3, i);
            gridPane.add(change, 4, i);
            gridPane.add(delete, 5, i);
        }
        mainField.setMargin(gridPane, new Insets(0, 40, 0, 40));
        mainField.getChildren().add(gridPane);
    }

    public void changeWorker(long id) {
        mainField.getChildren().clear();
        title.setText("");
        try{
            Stage dialogWindow = new Stage();
            ChangeWorkerController.setStage(dialogWindow);
            ChangeWorkerController.id = id;
            dialogWindow.initModality(Modality.APPLICATION_MODAL);
            dialogWindow.setTitle("Изменить работника " + id);
            String fxmlFile = "/fxml/windowChangeWorker.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
            dialogWindow.setUserData(id);
            dialogWindow.setScene(new Scene(root));
            dialogWindow.setResizable(false);
            dialogWindow.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteWorker(long id, boolean all){
        System.out.println(id);
        PersonalManager personalManager = PersonalManager.getInstance();
        personalManager.deleteWorker(id);
        if(all) {
            showAllWorker();
            System.out.println("ggggggggggg");
        }  else showCurrentWorker();
    }

    public void informationWorker(long id){

    }

    @FXML
    public void showAllNumber(){
        title.setText("Номера");
        mainField.getChildren().clear();
        NumberManager numberManager = NumberManager.getInstance();
        List<Number> numbers = numberManager.getNumbers();
        GridPane gridPane = new GridPane();
        int i = 0;
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(50),
                new ColumnConstraints(200), new ColumnConstraints(200),
                new ColumnConstraints(200), new ColumnConstraints(150),
                new ColumnConstraints(120), new ColumnConstraints(100));

        Label idTitle = new Label("№");
        Label typeTitle = new Label("Тип");
        Label capacityTitle = new Label("Вместимость");
        Label priceTitle = new Label("Стоимость");

        idTitle.setStyle("-fx-font-size: 18px;");
        capacityTitle.setStyle("-fx-font-size: 18px;");
        priceTitle.setStyle("-fx-font-size: 18px;");

        gridPane.add(idTitle, 0, i);
        gridPane.add(typeTitle, 1, i);
        gridPane.add(capacityTitle, 2, i);
        gridPane.add(priceTitle, 3, i);


        for(Number number : numbers){
            i++;
            gridPane.getRowConstraints().add(new RowConstraints(40));
            Label id = new Label(String.valueOf(number.getId()));
            Label type = new Label(NumberClass.getString(number.getNumberClass()));
            Label capacity = new Label(String.valueOf(number.getCapacity()));
            Label price = new Label(String.valueOf(number.getPrice()));
            Button information = new Button("Вся информация");
            Button change = new Button("Изменить");
            Button delete = new Button("Удалить");

            id.setStyle("-fx-font-size: 18px;");
            type.setStyle("-fx-font-size: 18px;");
            capacity.setStyle("-fx-font-size: 18px;");
            price.setStyle("-fx-font-size: 18px;");

            change.setOnAction(a -> changeNumber(number.getId()));
            delete.setOnAction(a -> deleteNumber(number.getId()));
            information.setOnAction(a -> informationNumber(number.getId()));

            gridPane.setMargin(change, new Insets(0, 0, 0, 20));
            gridPane.setMargin(delete, new Insets(0, 0, 0, 20));

            gridPane.add(id, 0, i);
            gridPane.add(type, 1, i);
            gridPane.add(capacity, 2, i);
            gridPane.add(price, 3, i);
            gridPane.add(information, 4, i);
            gridPane.add(change, 5, i);
            gridPane.add(delete, 6, i);
        }
        mainField.setMargin(gridPane, new Insets(0, 40, 0, 40));
        mainField.getChildren().add(gridPane);
    }

    public void changeNumber(long id){
        mainField.getChildren().clear();
        title.setText("");
        try{
            Stage dialogWindow = new Stage();
            ChangeNumberController.setStage(dialogWindow);
            ChangeNumberController.id = id;
            dialogWindow.initModality(Modality.APPLICATION_MODAL);
            dialogWindow.setTitle("Изменить номер " + id);
            String fxmlFile = "/fxml/windowChangeNumber.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
            dialogWindow.setUserData(id);
            dialogWindow.setScene(new Scene(root));
            dialogWindow.setResizable(false);
            dialogWindow.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void informationNumber(long id){
        System.out.println(id);
        title.setText("");
        // доделать
    }

    public void deleteNumber(long id){
        NumberManager numberManager = NumberManager.getInstance();
        numberManager.deleteNumber(id);
        showAllNumber();
    }
}

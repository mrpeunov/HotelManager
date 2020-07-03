package ru.peunov.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.*;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.NumberDAO;
import ru.peunov.dao.ReservationDAO;
import ru.peunov.enums.NumberClass;
import ru.peunov.enums.Position;
import ru.peunov.enums.ReservationStatus;
import ru.peunov.model.*;
import ru.peunov.model.Number;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;
import java.net.URL;
import java.util.*;

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
        title.setText("");
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

    @FXML
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

    @FXML
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

    @FXML
    public void deleteWorker(long id, boolean all){
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

    public void showAllReservations(){
        ReservationManager reservationManager = ReservationManager.getInstance();
        showReservations(reservationManager.getReservations(), true);
        title.setText("Все бронирования");
    }

    public void showCurrentReservations(){
        ReservationManager reservationManager = ReservationManager.getInstance();
        List<Reservation> reservations = reservationManager.getReservations();
        List<Reservation> currentReservations = new ArrayList<Reservation>();
        for(Reservation reservation : reservations){
            if(reservation.getReservationStatus() == ReservationStatus.CURRENT |
                reservation.getReservationStatus() == ReservationStatus.PAID){
                currentReservations.add(reservation);
            }
        }
        showReservations(currentReservations, false);
        title.setText("Текущие бронирования");
    }

    public void showReservations(List<Reservation> reservations, boolean all){
        mainField.getChildren().clear();
        GridPane gridPane = new GridPane();
        int i = 0;
        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(80), new ColumnConstraints(100), new ColumnConstraints(150),
                new ColumnConstraints(150), new ColumnConstraints(190), new ColumnConstraints(150),
                new ColumnConstraints(120), new ColumnConstraints(100));

        Label numberTop = new Label("Номер");
        Label numberTypeTop = new Label("Тип");
        Label startTop = new Label("Въезд");
        Label finishTop = new Label("Выезд");
        Label statusTop = new Label("Статус");
        Label numberCountTop = new Label("Количество");

        numberTop.setStyle("-fx-font-size: 18px;");
        numberTypeTop.setStyle("-fx-font-size: 18px;");
        startTop.setStyle("-fx-font-size: 18px;");
        finishTop.setStyle("-fx-font-size: 18px;");
        statusTop.setStyle("-fx-font-size: 18px;");
        numberCountTop.setStyle("-fx-font-size: 18px;");

        gridPane.add(numberTop, 0, i);
        gridPane.add(numberTypeTop, 1, i);
        gridPane.add(startTop, 2, i);
        gridPane.add(finishTop, 3, i);
        gridPane.add(statusTop, 4, i);
        gridPane.add(numberCountTop, 5, i);


        for(Reservation reservation : reservations){
            i++;
            gridPane.getRowConstraints().add(new RowConstraints(40));
            Label number = new Label(String.valueOf(reservation.getNumber().getId()));
            Label numberType = new Label(NumberClass.getString(reservation.getNumberClass()));
            Calendar startDate = reservation.getStart();
            Calendar finishDate = reservation.getFinish();
            Label start = new Label( String.format("%02d.%02d.%02d", startDate.get(Calendar.DAY_OF_MONTH),
                    startDate.get(Calendar.MONTH), startDate.get(Calendar.YEAR)));
            Label finish = new Label(String.format("%02d.%02d.%02d", finishDate.get(Calendar.DAY_OF_MONTH),
                    finishDate.get(Calendar.MONTH), finishDate.get(Calendar.YEAR)));

            Label numberCount = new Label(String.valueOf(reservation.getResidents().size()));

            ChoiceBox<String> status = new ChoiceBox<String>(FXCollections.observableArrayList("Забронировано", "Отмена", "Оплачено", "Закрыто"));
            status.setValue(ReservationStatus.getString(reservation.getReservationStatus()));
            Button information = new Button("Информация");
            Button delete = new Button("Удалить");

            number.setStyle("-fx-font-size: 18px;");
            numberType.setStyle("-fx-font-size: 18px;");
            start.setStyle("-fx-font-size: 18px;");
            finish.setStyle("-fx-font-size: 18px;");
            numberCount.setStyle("-fx-font-size: 18px;");

            status.setOnAction(a -> newReservationStatus(reservation.getId(), status.getValue(), all));
            delete.setOnAction(a -> deleteReservation(reservation.getId(), all));
            information.setOnAction(a -> informationReservation(reservation.getId()));

            gridPane.setMargin(delete, new Insets(0, 0, 0, 20));

            gridPane.add(number, 0, i);
            gridPane.add(numberType, 1, i);
            gridPane.add(start, 2, i);
            gridPane.add(finish, 3, i);
            gridPane.add(status, 4, i);
            gridPane.add(numberCount, 5, i);
            gridPane.add(information, 6, i);
            gridPane.add(delete, 7, i);
        }

        mainField.setMargin(gridPane, new Insets(0, 40, 0, 40));
        mainField.getChildren().add(gridPane);
    }

    public void newReservationStatus(long id, String str, boolean all){
        System.out.println(str + " " + id);
        ReservationManager reservationManager = ReservationManager.getInstance();
        reservationManager.updateStatus(id, str);
        if(all) showAllReservations();
        else showCurrentReservations();
    }

    public void deleteReservation(long id, boolean all){
        ReservationManager reservationManager = ReservationManager.getInstance();
        reservationManager.deleteReservation(id);
        if(all) showAllReservations();
        else showCurrentReservations();
    }

    public void informationReservation(long id){
        System.out.println("Информация " + id);
    }
}

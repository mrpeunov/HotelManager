package ru.peunov.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.peunov.enums.NumberClass;
import ru.peunov.model.Reservation;
import ru.peunov.model.ReservationManager;
import ru.peunov.model.Resident;
import ru.peunov.redefined.NumberTextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class NewReservationController implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("Привет");
        residents = new ArrayList<Resident>();
        residentNumber = 0;
        comment.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 250 ? change : null));
    }

    @FXML
    Button addGuest;

    @FXML
    TextField fioGuest;

    @FXML
    NumberTextField phoneGuest;

    @FXML
    Label status;

    @FXML
    Button create;

    @FXML
    ChoiceBox<String> choiceNumber;

    @FXML
    DatePicker dateStart;

    @FXML
    DatePicker dateFinish;

    @FXML
    TextArea comment;

    @FXML
    VBox guestBlock;

    @FXML
    private static Stage stage;

    private List<Resident> residents;
    private int residentNumber;

    public void newResident(){
        String stringFioGuest = fioGuest.getText();
        String stringPhoneGuest = phoneGuest.getAccessibleHelp();

        if(stringFioGuest.isEmpty()){
            fioGuest.setStyle("-fx-border-color: red;");
            status.setText("Введите ФИО");
        }
        else{
            fioGuest.setStyle("-fx-border-color: inherit;");
            addGuest.setStyle("-fx-border-color: inherit");
            status.setText(" ");
            Resident resident = new Resident(stringFioGuest, stringPhoneGuest);
            residents.add(resident);
            residentNumber++;

            addGuest.setDisable(true);
            if(residentNumber < 4){
                HBox newResidents = new HBox();
                VBox.setMargin(newResidents, new Insets(0, 0, 20,0));

                TextField newFIO = new TextField();
                newFIO.setPromptText("ФИО");
                newFIO.setPrefSize(300.0, 30.0);
                HBox.setMargin(newFIO, new Insets(0, 40, 0, 0));

                NumberTextField newPhone = new NumberTextField();
                newPhone.setPromptText("Телефон");
                newPhone.setPrefSize(200.0, 30.0);
                HBox.setMargin(newPhone, new Insets(0, 40, 0, 0));

                Button button = new Button("Добавить гостя");
                button.setPrefSize(180.0, 30.0);

                button.setOnAction(new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent event) {
                        newResident();
                    }
                });
                newResidents.getChildren().addAll(newFIO, newPhone, button);
                guestBlock.getChildren().add(newResidents);

                fioGuest = newFIO;
                phoneGuest = newPhone;
                addGuest = button;
            }
        }
    }

    public static void setStage(Stage stage){
        NewReservationController.stage = stage;
    }

    public void createReservation(){
        System.out.println("Создание бронирования");
        for(Resident resident : residents){
            System.out.println(resident.toString());
        }
        if(residents.size() != 0 && dateStart.getValue() != null &&
                dateFinish.getValue() != null && choiceNumber.getValue() != null){

            dateStart.setStyle("-fx-border-color: inherit;");
            dateFinish.setStyle("-fx-border-color: inherit;");
            choiceNumber.setStyle("-fx-border-color: inherit;");
            addGuest.setStyle("-fx-border-color: inherit");

            LocalDate localDateStart = dateStart.getValue();
            Calendar start = Calendar.getInstance();
            start.clear();
            start.set(localDateStart.getYear(), localDateStart.getMonthValue()-1, localDateStart.getDayOfMonth());

            LocalDate localDateFinish = dateFinish.getValue();
            Calendar finish = Calendar.getInstance();
            finish.clear();
            finish.set(localDateFinish.getYear(), localDateFinish.getMonthValue()-1, localDateFinish.getDayOfMonth());

            if(start.before(finish)){
                NumberClass numberClass = NumberClass.parseNumberClass(choiceNumber.getValue());
                Reservation reservation = new Reservation(residents, start, finish);
                ReservationManager reservationManager = ReservationManager.getInstance();
                Boolean flag = reservationManager.addReservation(reservation, numberClass);
                if(flag){
                    stage.close();
                } else {
                    status.setText("В данные даты нет свободных номеров данного типа");
                }
            }
            else{
                status.setText("Ошибка: заезд позже выезда");
            }
        }
        else{
            String newStatus = "Введите: ";
            if(dateStart.getValue() == null) {
                dateStart.setStyle("-fx-border-color: red;");
                newStatus += "дату заезда; ";
            }
            else dateStart.setStyle("-fx-border-color: inherit;");

            if(dateFinish.getValue() == null) {
                dateFinish.setStyle("-fx-border-color: red;");
                newStatus += "дату выезда; ";
            }
            else dateFinish.setStyle("-fx-border-color: inherit;");

            if(choiceNumber.getValue() == null) {
                choiceNumber.setStyle("-fx-border-color: red");
                newStatus += "тип номера; ";
            }
            else choiceNumber.setStyle("-fx-border-color: inherit;");

            if(residents.size() == 0) {
                addGuest.setStyle("-fx-border-color: red");
                newStatus += "гостей";
            }
            else addGuest.setStyle("-fx-border-color: inherit");

            status.setText(newStatus);
        }
    }
}

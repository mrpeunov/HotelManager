package ru.peunov.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import ru.peunov.enums.NumberClass;
import ru.peunov.enums.Position;
import ru.peunov.enums.ReservationStatus;
import ru.peunov.model.*;
import ru.peunov.model.Number;
import java.net.URL;
import java.time.LocalDate;
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
    public void showWorker(){
        title.setText("Персонал");
        PersonalManager personalManager = PersonalManager.getInstance();
        List<Worker> personal = personalManager.getPersonal();
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

            change.setOnAction(a -> changeWorker(worker.getId()));
            delete.setOnAction(a -> deleteWorker(worker.getId()));
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
    public void deleteWorker(long id){
        PersonalManager personalManager = PersonalManager.getInstance();
        personalManager.deleteWorker(id);
        showWorker();
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

    public void giveSalary(){
        PersonalManager personalManager = PersonalManager.getInstance();
        personalManager.giveSalaryAll();
    }

    public void showProfit(){
        title.setText("Прибыль");
        mainField.getChildren().clear();
        FinanceManager financeManager = FinanceManager.getInstance();
        List<Finance> finances = financeManager.getFinances();

        GridPane upPane = new GridPane();

        Label startTitle = new Label("С: ");
        DatePicker dateStart = new DatePicker();
        Label finishTitle = new Label("До: ");
        DatePicker dateFinish = new DatePicker();
        Button button = new Button("Показать");
        Label status = new Label("");

        GridPane gridPane = new GridPane();

        button.setOnAction(a -> {
            gridPane.getChildren().clear();
            LocalDate localDateStart = dateStart.getValue();
            LocalDate localDateFinish = dateFinish.getValue();

            try {
                Calendar start = Calendar.getInstance();
                start.clear();
                start.set(localDateStart.getYear(), localDateStart.getMonthValue() - 1, localDateStart.getDayOfMonth());

                Calendar finish = Calendar.getInstance();
                finish.clear();
                finish.set(localDateFinish.getYear(), localDateFinish.getMonthValue() - 1, localDateFinish.getDayOfMonth());
                finish.add(Calendar.HOUR, 1);
                if (finish.after(start) & finish != null & start != null) {
                    start.add(Calendar.HOUR, -1);
                    int profit = 0;
                    int i = 0;
                    gridPane.getColumnConstraints().addAll(new ColumnConstraints(150), new ColumnConstraints(200));
                    Label sizeTitle = new Label("Размер");
                    Label dateTitle = new Label("Дата");
                    sizeTitle.setStyle("-fx-font-size: 18px;");
                    dateTitle.setStyle("-fx-font-size: 18px;");
                    gridPane.add(sizeTitle, 0, i);
                    gridPane.add(dateTitle, 1, i);

                    for (Finance finance : finances) {
                        if (start.before(finance.getDate()) & finish.after(finance.getDate())) {
                            if (finance.isSign()) profit += finance.getSize();
                            else profit -= finance.getSize();

                            i++;
                            gridPane.getRowConstraints().add(new RowConstraints(40));
                            String plus;
                            if (finance.isSign()) plus = "+";
                            else plus = "- ";
                            Label size = new Label(plus + String.valueOf(finance.getSize()));

                            Calendar thisDate = finance.getDate();
                            Label date = new Label(String.format("%02d.%02d.%02d", thisDate.get(Calendar.DAY_OF_MONTH),
                                    thisDate.get(Calendar.MONTH), thisDate.get(Calendar.YEAR)));

                            size.setStyle("-fx-font-size: 18px;");
                            date.setStyle("-fx-font-size: 18px;");

                            gridPane.add(size, 0, i);
                            gridPane.add(date, 1, i);
                            mainField.setMargin(gridPane, new Insets(0, 40, 0, 40));
                        }
                    }
                    if (profit > 0) status.setText("Прибыль равна: " + profit);
                    else status.setText("Убыток равен: " + profit);

                } else {
                    status.setText("Начало позже конца");
                }
            } catch (NullPointerException e){
                status.setText("Некорректно");
            }
        });

        upPane.add(startTitle, 0, 0);
        upPane.add(dateStart, 1, 0);
        upPane.add(finishTitle, 2, 0);
        upPane.add(dateFinish, 3, 0);
        upPane.add(button, 4, 0);
        upPane.add(status, 5, 0);

        upPane.setMargin(startTitle, new Insets(5, 10, 0, 0));
        upPane.setMargin(dateStart, new Insets(5, 20, 0, 0));
        upPane.setMargin(finishTitle, new Insets(5, 10, 0, 0));
        upPane.setMargin(dateFinish, new Insets(5, 40, 0, 0));
        upPane.setMargin(button, new Insets(5, 20, 0, 0));

        mainField.setMargin(upPane, new Insets(0, 40, 0, 40));
        mainField.getChildren().add(upPane);
        mainField.getChildren().add(gridPane);
    }

    public void showRevenue(){
        title.setText("Выручка");
        mainField.getChildren().clear();
        FinanceManager financeManager = FinanceManager.getInstance();
        List<Finance> finances = financeManager.getFinances();

        GridPane upPane = new GridPane();

        Label startTitle = new Label("С: ");
        DatePicker dateStart = new DatePicker();
        Label finishTitle = new Label("До: ");
        DatePicker dateFinish = new DatePicker();
        Button button = new Button("Показать");
        Label status = new Label("");

        GridPane gridPane = new GridPane();

        button.setOnAction(a -> {
            gridPane.getChildren().clear();
            LocalDate localDateStart = dateStart.getValue();
            LocalDate localDateFinish = dateFinish.getValue();

            try {
                Calendar start = Calendar.getInstance();
                start.clear();
                start.set(localDateStart.getYear(), localDateStart.getMonthValue() - 1, localDateStart.getDayOfMonth());

                Calendar finish = Calendar.getInstance();
                finish.clear();
                finish.set(localDateFinish.getYear(), localDateFinish.getMonthValue() - 1, localDateFinish.getDayOfMonth());
                finish.add(Calendar.HOUR, 1);
                if (finish.after(start) & finish != null & start != null) {
                    start.add(Calendar.HOUR, -1);
                    int profit = 0;
                    int i = 0;
                    gridPane.getColumnConstraints().addAll(new ColumnConstraints(150), new ColumnConstraints(200));
                    Label sizeTitle = new Label("Размер");
                    Label dateTitle = new Label("Дата");
                    sizeTitle.setStyle("-fx-font-size: 18px;");
                    dateTitle.setStyle("-fx-font-size: 18px;");
                    gridPane.add(sizeTitle, 0, i);
                    gridPane.add(dateTitle, 1, i);

                    for (Finance finance : finances) {
                        if (start.before(finance.getDate()) & finish.after(finance.getDate()) & finance.isSign()) {
                            profit += finance.getSize();
                            i++;
                            gridPane.getRowConstraints().add(new RowConstraints(40));
                            String plus;
                            if (finance.isSign()) plus = "+";
                            else plus = "- ";
                            Label size = new Label(plus + String.valueOf(finance.getSize()));

                            Calendar thisDate = finance.getDate();
                            Label date = new Label(String.format("%02d.%02d.%02d", thisDate.get(Calendar.DAY_OF_MONTH),
                                    thisDate.get(Calendar.MONTH), thisDate.get(Calendar.YEAR)));

                            size.setStyle("-fx-font-size: 18px;");
                            date.setStyle("-fx-font-size: 18px;");

                            gridPane.add(size, 0, i);
                            gridPane.add(date, 1, i);
                            mainField.setMargin(gridPane, new Insets(0, 40, 0, 40));
                        }
                    }
                    if (profit > 0) status.setText("Выручка равна: " + profit);

                } else {
                    status.setText("Начало позже конца");
                }
            } catch (NullPointerException e){
                status.setText("Некорректно");
            }
        });

        upPane.add(startTitle, 0, 0);
        upPane.add(dateStart, 1, 0);
        upPane.add(finishTitle, 2, 0);
        upPane.add(dateFinish, 3, 0);
        upPane.add(button, 4, 0);
        upPane.add(status, 5, 0);

        upPane.setMargin(startTitle, new Insets(5, 10, 0, 0));
        upPane.setMargin(dateStart, new Insets(5, 20, 0, 0));
        upPane.setMargin(finishTitle, new Insets(5, 10, 0, 0));
        upPane.setMargin(dateFinish, new Insets(5, 40, 0, 0));
        upPane.setMargin(button, new Insets(5, 20, 0, 0));

        mainField.setMargin(upPane, new Insets(0, 40, 0, 40));
        mainField.getChildren().add(upPane);
        mainField.getChildren().add(gridPane);
    }

    public void showAllFinance(){
        title.setText("Сводка");
        mainField.getChildren().clear();
        FinanceManager financeManager = FinanceManager.getInstance();
        List<Finance> finances = financeManager.getFinances();
        GridPane gridPane = new GridPane();
        int i = 0;
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(150), new ColumnConstraints(200));

        Label sizeTitle = new Label("Размер");
        Label dateTitle = new Label("Дата");

        sizeTitle.setStyle("-fx-font-size: 18px;");
        dateTitle.setStyle("-fx-font-size: 18px;");

        gridPane.add(sizeTitle, 0, i);
        gridPane.add(dateTitle, 1, i);

        for(Finance finance : finances){
            i++;
            gridPane.getRowConstraints().add(new RowConstraints(40));
            String plus;
            if(finance.isSign()) plus = "+";
            else plus = "- ";
            Label size = new Label(plus + String.valueOf(finance.getSize()));

            Calendar thisDate = finance.getDate();
            Label date = new Label(String.format("%02d.%02d.%02d", thisDate.get(Calendar.DAY_OF_MONTH),
                    thisDate.get(Calendar.MONTH), thisDate.get(Calendar.YEAR)));

            size.setStyle("-fx-font-size: 18px;");
            date.setStyle("-fx-font-size: 18px;");

            gridPane.add(size, 0, i);
            gridPane.add(date, 1, i);
        }
        mainField.setMargin(gridPane, new Insets(0, 40, 0, 40));
        mainField.getChildren().add(gridPane);
    }
}

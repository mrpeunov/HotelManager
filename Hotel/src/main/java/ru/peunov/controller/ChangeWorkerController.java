package ru.peunov.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.NumberDAO;
import ru.peunov.dao.WorkerDAO;
import ru.peunov.enums.NumberClass;
import ru.peunov.enums.Position;
import ru.peunov.enums.Status;
import ru.peunov.model.Number;
import ru.peunov.model.NumberManager;
import ru.peunov.model.PersonalManager;
import ru.peunov.model.Worker;
import ru.peunov.redefined.NumberTextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeWorkerController implements Initializable {
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(id);
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        Worker worker = workerDAO.read(id);


        fio.setText(String.valueOf(worker.getName()));
        salary.setText(String.valueOf(worker.getSalary()));
        choice.setValue(Position.getString(worker.getPosition()));
    }

    @FXML
    private static Stage stage;

    protected static long id;

    @FXML
    private TextField fio;

    @FXML
    private NumberTextField salary;

    @FXML
    private ChoiceBox<String> choice;

    public static void setStage(Stage stage){
        ChangeWorkerController.stage = stage;
    }

    @FXML
    public void changeWorker(){
        try {
            String fioText = fio.getText();
            String salaryText = salary.getText();
            if(salaryText.length() != 0 && choice.getValue() != null && fioText.length() != 0){
                Position position = Position.parsePosition(choice.getValue());
                int salaryInt = Integer.parseInt(salaryText);
                PersonalManager personalManager = PersonalManager.getInstance();
                personalManager.changeWorker(id, fioText, salaryInt, position);
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
}

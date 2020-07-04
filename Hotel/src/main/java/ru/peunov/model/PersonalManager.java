package ru.peunov.model;

import ru.peunov.HibernateUtil;
import ru.peunov.dao.SalaryDAO;
import ru.peunov.dao.WorkerDAO;
import ru.peunov.enums.Position;
import ru.peunov.enums.Status;

import java.util.List;

public class PersonalManager implements Manager {
    /*
    * Using pattern Singleton
    */
    private static PersonalManager personalManager;
    private List<Worker> personal;
    private List<Salary> salaries;

    private PersonalManager() {
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        SalaryDAO salaryDAO = new SalaryDAO(HibernateUtil.getSessionFactory());

        personal = workerDAO.getAll();
        salaries = salaryDAO.getAll();
    };

    public static PersonalManager getInstance(){
        if(personalManager == null){
            personalManager = new PersonalManager();
        }
        return personalManager;
    }

    public void printAll(){
        System.out.println("Работники");
        for(Worker worker : personal){
            System.out.println(worker);
        }
        System.out.println("");
    }


    public static void update(){
        personalManager = new PersonalManager();
    }

    public void makeNewWorker(String name, int salary, Position position){
        Worker worker = new Worker(name, salary, position);
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        workerDAO.create(worker);
        personal.add(worker);
    }

    public void changeWorker(long id, String name, int salary, Position position){
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        Worker worker = workerDAO.read(id);
        worker.setPosition(position);
        worker.setName(name);
        worker.setSalary(salary);
        workerDAO.saveOrUpdate(worker);
        Hotel.updateAll();
    }

    public List<Worker> getPersonal() {
        return personal;
    }

    public void deleteWorker(long id){
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        workerDAO.delete(workerDAO.read(id));
        Hotel.updateAll();
    }

    public void setPersonal(List<Worker> personal) {
        this.personal = personal;
    }

    public void giveSalaryAll(){
        for(Worker worker : personal){
            if(worker.getStatus() == Status.WORKED) worker.giveSalary();
        }
    }

    public Worker getWorker(long id){
        Worker result = null;
        for(Worker worker : personal){
            if(worker.getId() == id){
                result = worker;
            }
        }
        return result;
    }

    public void changeStatusWorker(long id){
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        Worker worker = workerDAO.read(id);
        if(worker.getStatus() == Status.DISMISSED) worker.setStatus(Status.WORKED);
        else worker.setStatus(Status.DISMISSED);
        workerDAO.saveOrUpdate(worker);
        Hotel.updateAll();
    }
}

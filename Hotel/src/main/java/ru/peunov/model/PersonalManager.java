package ru.peunov.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.SalaryDAO;
import ru.peunov.dao.WorkerDAO;
import ru.peunov.enums.Position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PersonalManager implements Manager {
    /*
    * Using pattern Singleton
    **/
    private static PersonalManager personalManager;
    private List<Worker> personal;
    private List<Salary> salaries;

    private PersonalManager() {
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        SalaryDAO salaryDAO = new SalaryDAO(HibernateUtil.getSessionFactory());

        personal = workerDAO.getAll();
        salaries = salaryDAO.getAll();




        /*
        for(Salary salary : salaries){
            System.out.println(salary.getSize() + salary.getWorker().getId());
        }
        for(Worker worker : personal){
            worker.giveSalary();
        }
         */


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
        System.out.println("mm");
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
        System.out.println("hello");
        workerDAO.saveOrUpdate(worker);
        update();
    }

    public List<Worker> getPersonal() {
        return personal;
    }

    public void deleteWorker(long id){
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        workerDAO.delete(workerDAO.read(id));
        System.out.println("Здарова");
        update();
    }

    public void setPersonal(List<Worker> personal) {
        this.personal = personal;
    }
}

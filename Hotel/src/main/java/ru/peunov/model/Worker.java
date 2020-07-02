package ru.peunov.model;

import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.SalaryDAO;
import ru.peunov.dao.WorkerDAO;
import ru.peunov.enums.Position;
import ru.peunov.enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker {
    /**
     * Model worker for bd
     */

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "salary", nullable = false)
    private int salary;

    @Column(name = "position")
    @Enumerated(EnumType.ORDINAL)
    private Position position;

    @OneToMany(mappedBy = "worker", fetch = FetchType.EAGER)
    private List<Salary> allSalary;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Worker(String name, int salary, Position position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.status = Status.WORKED;
        this.allSalary = new ArrayList<Salary>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public Worker() { }

    public void printAllSalary(){
        for(Salary item :  allSalary){
            System.out.print(item.getSize());
        }
    }

    public void giveSalary(){
        allSalary.add(new Salary(salary, this));
        updateSalary();
    };

    public void updateSalary(){
        try {
            for (Salary salary : allSalary) {
                SalaryDAO salaryDAO = new SalaryDAO(HibernateUtil.getSessionFactory());
                salaryDAO.saveOrUpdate(salary);
            }
        } catch (Exception e) {
            System.out.println("Здесь происходит что-то непонятное и с этим надо разобраться");
            e.printStackTrace();
        }
    }

    /**
     * ОТЛДАДИТЬ ЭТУ ХРЕНЬ СВЕРХУ
     * 
     */

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", allSalarySize = " + allSalary.size() +
                ", position=" + position +
                ", status=" + status +
                '}';
    }
    /*

    public void dismiss(){
        status = Status.DISMISSED;
    };

    public void giveAction(Action action) {};

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }*/
}

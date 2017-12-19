package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Collections;

public class Employee {
    public int ID;
    public String name;
    public String shiftHours;
    public double salary;
    public String jobTitle;
    private Supermarket market;

    public Employee(){

    }
    public Employee(int ID, String name, String shiftHours, double salary, String jobTitle){
        this.ID = ID;
        this.name = name;
        this.shiftHours = shiftHours;
        this.salary = salary;
        this.jobTitle = jobTitle;
    }
}

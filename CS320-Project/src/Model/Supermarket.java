package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Supermarket {
    private double bank = 0;
    public Map<Integer, Product> products = new HashMap<>();
    public Map<Integer, Product> sortedProducts;
    public ArrayList<Employee> employees = new ArrayList<>();

    public void addToBank(double profit){
        bank += profit;
    }

    public void takeFromBank(double cost){
        bank -= cost;
    }

    public double getMoneyInBank(){
        return bank;
    }

    public void sortProducts(){
        sortedProducts = new TreeMap<>(this.products);
    }
}

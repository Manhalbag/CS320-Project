package Model;

public class Product{
    public String name;
    public int ID;
    public double cost;
    public double price;
    public String type;
    public int amount;
    public int originalAmount;
    

    public Product(String pName, int ID, double cost, double price, String type, int amount){
        this.name = pName;
        this.ID = ID;
        this.cost = cost;
        this.price = price;
        this.type = type;
        this.amount = amount;
        this.originalAmount = amount;
    }
}

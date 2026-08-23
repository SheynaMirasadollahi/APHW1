import java.util.ArrayList;

public class User {
    
    private int id;
    private String username;
    private String password;
    private double balance;
    private ArrayList<House> boughtHouses;
    private ArrayList<House> rentedHouses;

    public User(int id, String username, String password, double balance) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.boughtHouses = new ArrayList<>();
        this.rentedHouses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void showInfo() {
        System.out.println("User ID: " + id);
        System.out.println("Username:" + username);
        System.out.println("Balance:" + balance);
    }

    public void addBoughtHouse(House house) {
        boughtHouses.add(house);
    }

    public void showBoughtHouses() {
        if (boughtHouses.size() == 0) 
        System.out.println("No bought houses");
        else {
        for (House house : boughtHouses) {
            house.showInfo();
            System.out.println("----------------");
        }
    }
    }

    public void addRentedHouse(House house) {
        rentedHouses.add(house);
    }

    public void showRentedHouses() {
        if (rentedHouses.size() == 0)
            System.out.println("No rented houses");
        else {
            for (House house : rentedHouses) {
                house.showInfo();
                System.out.println("--------------");
            }
        }
    }
}

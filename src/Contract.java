public class Contract {

    private int id;
    private House house;
    private User owner;
    private User renter;
    private double price;
    private int duration;

    public Contract(int id, House house, User owner,
                    User renter, double price, int duration) {
                
        this.id = id;
        this.house = house;
        this.owner = owner;
        this.renter = renter;
        this.price = price;
        this.duration = duration;

    }

    public int getId() {
        return id;
    }

    public House getHouse() {
        return house;;
    }

    public User getOwner() {
        return renter;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public void showInfo() {

        System.out.println("Contract ID: " + id);
        System.out.println("Price: " + price);
        System.out.println("Duration: " + duration + " months");
        System.out.println("Owner: " + owner.getUsername());
        System.out.println("Renter: " + renter.getUsername());
    }
}

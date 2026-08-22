public abstract class House {

    private int id;
    private double area;
    private int bedrooms;
    private int bathrooms;
    private int floor;
    private int region;
    private User owner;
    private String status;

    public House (int id, double area, int bedrooms, int bathrooms, int floor, int region, User owner, String status) {

        this.id = id;
        this.area = area;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floor = floor;
        this.region = region;
        this.owner = owner;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int bathrooms() {
        return bathrooms;
    }
    public int getFloor() {
        return floor;
    }

    public int getRegion() {
        return region;
    }

    public User getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract double calculaterPrice();

    public void showInfo() {
        System.out.println("House ID: " + id);
        System.out.println("Area: " + area);
        System.out.println("Bedrroms: " + bedrooms);
        System.out.println("Bathrooms: " + bathrooms);
        System.out.println("Floor: " + floor);
        System.out.println("Region: " + region);
        System.out.println("Status: " + status);
    }
    
}

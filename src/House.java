public abstract class House {

    protected static final double BASE_PRICE_PER_METER = 10000000;
    protected static final double RENT_RATE = 0.004;

    private int id;
    private double area;
    private int bedrooms;
    private int bathrooms;
    private int floor;
    private int region;
    private User owner;
    private String status;
    private String statusBeforeRent;

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

    public int getBathrooms() {
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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isForSale() {
        return status.equals("Sale") || status.equals("Both");
    }

    public boolean isForRent() {
        return status.equals("Rent") || status.equals("Both");
    }

    public void markAsRented() {
        this.statusBeforeRent = this.status;
        this.status = "Rented";
    }

    public void restoreStatusAfterRentCancel() {
        if (statusBeforeRent != null)
            this.status = statusBeforeRent;
        else
            this.status = "Rent";
    }

    protected double getRegionCoefficient() {
        if (getRegion() == 1)
            return 1.8;
        else if (getRegion() == 2)
            return 1.4;
        else if (getRegion() == 3)
            return 1.1;
        else if (getRegion() == 4)
            return 0.8;
        else
            return 1;
    }

    protected double calculateBasePrice() {
        return getArea() * BASE_PRICE_PER_METER * getRegionCoefficient();
    }

    public abstract double calculatePrice();

    public double calculateMonthlyRent() {
        return calculatePrice() * RENT_RATE;
    }

    public void showInfo() {
        System.out.println("House ID: " + id);
        System.out.println("Area: " + area);
        System.out.println("Bedrroms: " + bedrooms);
        System.out.println("Bathrooms: " + bathrooms);
        System.out.println("Floor: " + floor);
        System.out.println("Region: " + region);
        System.out.println("Status: " + status);
        System.out.println("Owner: " + owner.getUsername());
    }

    public abstract String toFileLine();
    
}

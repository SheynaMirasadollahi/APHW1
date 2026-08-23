public class Villa extends House {

    private static final double YARD_PRICE_PER_METER = 3000000;
    private static final double FLOOR_PREMIUM = 20000000;

    private double landArea;
    private int floors;

    public Villa(int id, double area, int bedrooms, int bathrooms, int floor, int region, User owner,
                 String status, double landArea, int floors) {

        super(id, area, bedrooms, bathrooms, floor, region, owner, status);

        this.landArea = landArea;
        this.floors = floors;
    }

    public double getLandArea() {
        return landArea;
    }

    public int getFloors() {
        return floors;
    }

    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();
        double price = basePrice + (landArea * YARD_PRICE_PER_METER) + (floors * FLOOR_PREMIUM);

        return price;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Land Area: " + landArea);
        System.out.println("Villa Floors: " + floors);
    }

    @Override
    public String toFileLine() {
        return "Villa," + getId() + "," + getArea() + "," + getBedrooms() + "," + getBathrooms() + ","
                + getFloor() + "," + getRegion() + "," + getOwner().getId() + "," + getStatus() + ","
                + landArea + "," + floors;
    }
}
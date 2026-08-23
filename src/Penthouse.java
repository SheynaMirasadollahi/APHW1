public class Penthouse extends House {

    private static final double TERRACE_PRICE_PER_METER = 5000000;

    private double terraceArea;
    private double luxuryCoefficient;

    public Penthouse(int id, double area, int bedrooms, int bathrooms, int floor, int region, User owner,
                      String status, double terraceArea, double luxuryCoefficient) {

        super(id, area, bedrooms, bathrooms, floor, region, owner, status);

        this.terraceArea = terraceArea;
        this.luxuryCoefficient = luxuryCoefficient;
    }

    public double getTerraceArea() {
        return terraceArea;
    }

    public double getLuxuryCoefficient() {
        return luxuryCoefficient;
    }

    @Override
    public double calculatePrice() {

        double basePrice = calculateBasePrice();
        double price = (basePrice * luxuryCoefficient) + (terraceArea * TERRACE_PRICE_PER_METER);

        return price;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Terrace Area: " + terraceArea);
        System.out.println("Luxury Coefficient: " + luxuryCoefficient);
    }

    @Override
    public String toFileLine() {
        return "Penthouse," + getId() + "," + getArea() + "," + getBedrooms() + "," + getBathrooms() + ","
                + getFloor() + "," + getRegion() + "," + getOwner().getId() + "," + getStatus() + ","
                + terraceArea + "," + luxuryCoefficient;
    }
}
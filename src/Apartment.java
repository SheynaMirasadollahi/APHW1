public class Apartment extends House {

    private int unitNumber;
    private int buildingFloors;
    private int totalUnits;

    public Apartment(int id, double area, int bedrooms, int bathrooms, int floor, int region, User owner,
                      String status, int unitNumber, int buildingFloors, int totalUnits) {

        super(id, area, bedrooms, bathrooms, floor, region, owner, status);

        this.unitNumber = unitNumber;
        this.buildingFloors = buildingFloors;
        this.totalUnits = totalUnits;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public int getBuildingFloors() {
        return buildingFloors;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();

        double price = basePrice * (1 + 0.03 * getBedrooms()) * (1 + 0.01 * getFloor());
        return price;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Unit Number: " + unitNumber);
        System.out.println("Building Floors: " + buildingFloors);
        System.out.println("Total Units: " + totalUnits);
    }

    @Override
    public String toFileLine() {
        return "Apartment," + getId() + "," + getArea() + "," + getBedrooms() + "," + getBathrooms() + ","
                + getFloor() + "," + getRegion() + "," + getOwner().getId() + "," + getStatus() + ","
                + unitNumber + "," + buildingFloors + "," + totalUnits;
    }
}
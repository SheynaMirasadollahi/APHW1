public class Apartment extends House { 

private int unitNumber;
private int buildingFloors;
private int totalUnits;

public Apartment(int id, double area, int bedrooms, int bathrooms,
                 int floor, int region, User owner, String status,
                 int unitNumber, int buildingFloors, int totalUnits) {

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

    double regionCoefficient = 1;

    if (getRegion() == 1)
        regionCoefficient = 1.8;

    else if (getRegion() == 2) 
        regionCoefficient = 1.4;

    else if (getRegion() == 3)
        regionCoefficient = 1.1;

    else if (getRegion() == 4) 
        regionCoefficient = 0.8;
    
    double basePrice = getArea() * 10000000 * regionCoefficient;

    double price = basePrice * (1 + 0.03 * getBedrooms()) * (1 + 0.01 * getFloor());
    return price;
}

@Override
public void showInfo() {

    super.showInfo();

    System.out.println("Unit Number: " + unitNumber);
    System.out.println("Bulding Floors: " + buildingFloors);
    System.out.println("Total Units: " + totalUnits);
    }
}

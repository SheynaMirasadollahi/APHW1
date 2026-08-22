public class Villa extends House {

    private double landArea;
    private int floors;

    public Villa (int id, double area, int bedrooms, int bathrooms,
                  int floor, int region, User owner, String status,
                  double landArea, int floors) {

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

        double regionCoefficient = 1;

        if (getRegion() == 1) 
            regionCoefficient = 1.8;

        else if  (getRegion() == 2)
            regionCoefficient = 1.4;

        else if (getRegion() == 3)
            regionCoefficient = 1.1;

        else if (getRegion() == 4)
            regionCoefficient = 0.8;
    

    double basePrice = getArea() * 10000000 * regionCoefficient;
    double price = basePrice * (1 + 0.05 * floors);
    
    return price;

}
    @Override
    public void showInfo() {

        super.showInfo();

        System.out.println("Land Area: " + landArea);
        System.out.println("Villa Floors: " + floors);
    }

}

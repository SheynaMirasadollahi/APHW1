public class Penthouse extends House {

    private double terraceArea;
    private double luxuryCoefficient;

    public Penthouse(int id, double area, int bedrooms, int bathrooms,
        int floor, int region, User owner, 
        String status,
        double terraceArea, double luxuryCoefficient) {

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
            double price = (basePrice * luxuryCoefficient) + (terraceArea * 5000000);

            return price;
        }
    @Override
    public void showInfo() {

        super.showInfo();

        System.out.println("Terrace Area: " + terraceArea);
        System.out.println("Luxury CoeFFicient: " + luxuryCoefficient);
    }
    
}

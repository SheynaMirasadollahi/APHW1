import java.util.ArrayList;

public class Agency {

    private String name;
    private ArrayList<House> houses;

    public Agency(String name) {

        this.name = name;
        this.houses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public void addHouse(House house) {
        houses.add(house);
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    public void showHouses() {
        System.out.println("Agency: " + name);

        for (House house : houses) {
            house.showInfo();
            System.out.println("-------------");
        }
    }
    
}

import java.util.ArrayList;


public class RealEstateSystem {


    private ArrayList<User> users;
    private ArrayList<House> houses;
    private ArrayList<Contract> contracts;

    private int userId;
    private int houseId;
    private int contractId;

    public RealEstateSystem() {

        users = new ArrayList<>();
        houses = new ArrayList<>();
        contracts = new ArrayList<>();

        userId = 1;
        houseId = 1;
        contractId = 1;
    }

    public void addUser(String username, String password, double balance) {

        User user = new User(userId, username, password, balance);
        users.add(user);
        userId++;
    }

    public void addHouse(House house) {

        houses.add(house);
        houseId++;
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User findUserById(int id) {

        for(User user : users) {
            if(user.getId() == id)
                return user;
        }
        return null;
    }

    public House findHouseById(int id) {

        for(House house : houses) {
            if(house.getId() == id) 
                return house;
        }
        return null;
    }

    public void showUsers() {

        for(User user : users) {
            user.showInfo();
            System.out.println("----------------");
        }
    }

    public void showHouses() {

        for(House house : houses) {
            house.showInfo();
            System.out.println("Price: " + house.calculatePrice());
            System.out.println("----------------");
        }
    }

    public boolean buyHouse(int userId, int houseId) {

        User buyer = findUserById(userId);
        House house = findHouseById(houseId);

        if(buyer == null || house == null)
            return false;

        if(buyer.getBalance() >= house.calculatePrice()) {
            buyer.setBalance(buyer.getBalance() - house.calculatePrice());

            house.setStatus("Sold");

            buyer.addBoughtHouse(house);

            return true;
        }
        return false;
    }

    public boolean createContract(int renterId, int houseId) {

        User renter = findUserById(renterId);
        House house = findHouseById(houseId);

        if(renter == null || house == null)
            return false;

        Contract contract = new Contract(contractId, house, house.getOwner(),  renter,  house.calculatePrice(),  12);

        contracts.add(contract);
        contractId++;
        house.setStatus("Rented");

        return true;
    }

    public void showContracts() {

        for(Contract contract : contracts) {
            contract.showInfo();
            System.out.println("----------------");
        }
    }

}
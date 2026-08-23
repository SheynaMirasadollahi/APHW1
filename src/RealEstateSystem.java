import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RealEstateSystem {

    private ArrayList<User> users;
    private ArrayList<House> houses;
    private ArrayList<Contract> contracts;

    private int userId;
    private int houseId;
    private int contractId;
    private Agency agency;

    private static final String USERS_FILE = "users.txt";
    private static final String HOUSES_FILE = "houses.txt";
    private static final String CONTRACTS_FILE = "contracts.txt";

    public RealEstateSystem() {

        users = new ArrayList<>();
        houses = new ArrayList<>();
        contracts = new ArrayList<>();
        agency = new Agency("Real Estate Agency");

        userId = 1;
        houseId = 1;
        contractId = 1;
    }

    public int getNextHouseId() {
        return houseId;
    }

    public boolean addUser(String username, String password, double balance) {

        if (findUserByUsername(username) != null)
            return false;

        User user = new User(userId, username, password, balance);
        users.add(user);
        userId++;
        return true;
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
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
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public House findHouseById(int id) {
        for (House house : houses) {
            if (house.getId() == id)
                return house;
        }
        return null;
    }

    public Contract findContractById(int id) {
        for (Contract contract : contracts) {
            if (contract.getId() == id)
                return contract;
        }
        return null;
    }

    public void showUsers() {
        for (User user : users) {
            user.showInfo();
            System.out.println("----------------");
        }
    }

    public void showHouses() {
        for (House house : houses) {
            house.showInfo();
            System.out.println("Price: " + house.calculatePrice());
            System.out.println("----------------");
        }
    }

    public void showHousesForSale() {
        for (House house : houses) {
            if (house.isForSale()) {
                house.showInfo();
                System.out.println("Price: " + house.calculatePrice());
                System.out.println("----------------");
            }
        }
    }

    public void showHousesForRent() {
        for (House house : houses) {
            if (house.isForRent()) {
                house.showInfo();
                System.out.println("Monthly Rent: " + house.calculateMonthlyRent());
                System.out.println("----------------");
            }
        }
    }

    public void showAgencyHouses() {
        agency.showHouses();
    }

    public boolean buyHouse(int userId, int houseId) {

        User buyer = findUserById(userId);
        House house = findHouseById(houseId);

        if (buyer == null || house == null)
            return false;

        if (!house.isForSale())
            return false;

        if (buyer.getBalance() >= house.calculatePrice()) {

            buyer.setBalance(buyer.getBalance() - house.calculatePrice());

            house.getOwner().setBalance(house.getOwner().getBalance() + house.calculatePrice());

            house.setStatus("NotAvailable");
            agency.removeHouse(house);

            house.setOwner(buyer);

            buyer.addBoughtHouse(house);

            return true;
        }
        return false;
    }

    public boolean createContract(int renterId, int houseId) {

        User renter = findUserById(renterId);
        House house = findHouseById(houseId);

        if (renter == null || house == null)
            return false;

        if (!house.isForRent())
            return false;

        double monthlyRent = house.calculateMonthlyRent();

        Contract contract = new Contract(contractId, house, house.getOwner(), renter, monthlyRent, 12);

        contracts.add(contract);
        contractId++;

        house.markAsRented();

        renter.addRentedHouse(house);

        return true;
    }

    public void showContracts() {
        for (Contract contract : contracts) {
            contract.showInfo();
            System.out.println("----------------");
        }
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void showContracts(int userId) {
        for (Contract contract : contracts) {
            if (contract.getRenter().getId() == userId) {
                contract.showInfo();
                System.out.println("----------------");
            }
        }
    }

    public boolean cancelContract(int id) {

        Contract contract = findContractById(id);
        if (contract == null)
            return false;

        double penalty = contract.getPrice() * 0.1;
        User renter = contract.getRenter();
        User owner = contract.getOwner();

        if (renter.getBalance() < penalty)
            return false;

        renter.setBalance(renter.getBalance() - penalty);

        owner.setBalance(owner.getBalance() + penalty);

        contract.getHouse().restoreStatusAfterRentCancel();

        renter.removeRentedHouse(contract.getHouse());

        removeContract(contract);
        return true;
    }

    public void removeContract(Contract contract) {
        contracts.remove(contract);
    }

    public boolean sellToAgency(int userId, int houseId) {

        House house = findHouseById(houseId);
        if (house == null)
            return false;

        User seller = findUserById(userId);
        if(house.getOwner() != seller)
            return false;
        double payment = house.calculatePrice() * 0.9; 

        house.getOwner().setBalance(house.getOwner().getBalance() + payment);

        agency.buyHouse(house);

        houses.remove(house);

        house.setOwner(null);

        house.setStatus("Both");

        return true;
    }

    public void saveAll() {
        saveUsers();
        saveHouses();
        saveContracts();
    }

    private void saveUsers() {
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            for (User user : users) {
                writer.write(user.getId() + "," + user.getUsername() + "," + user.getPassword() + ","
                        + user.getBalance() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private void saveHouses() {
        try (FileWriter writer = new FileWriter(HOUSES_FILE)) {
            for (House house : houses) {
                writer.write(house.toFileLine() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving houses: " + e.getMessage());
        }
    }

    private void saveContracts() {
        try (FileWriter writer = new FileWriter(CONTRACTS_FILE)) {
            for (Contract contract : contracts) {
                writer.write(contract.toFileLine() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving contracts: " + e.getMessage());
        }
    }

    public void loadAll() {
        loadUsers();
        loadHouses();
        loadContracts();
        int maxId = 0;

        for(House house : houses){

            if(house.getId() > maxId){

        maxId = house.getId();

    }

}

houseId = maxId + 1;
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String username = parts[1];
                String password = parts[2];
                double balance = Double.parseDouble(parts[3]);

                User user = new User(id, username, password, balance);
                users.add(user);

                if (id > maxId)
                    maxId = id;
            }
            userId = maxId + 1;

        } catch (IOException e) {
        }
    }

    private void loadHouses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HOUSES_FILE))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] p = line.split(",");
                String type = p[0];
                int id = Integer.parseInt(p[1]);
                double area = Double.parseDouble(p[2]);
                int bedrooms = Integer.parseInt(p[3]);
                int bathrooms = Integer.parseInt(p[4]);
                int floor = Integer.parseInt(p[5]);
                int region = Integer.parseInt(p[6]);
                int ownerId = Integer.parseInt(p[7]);
                String status = p[8];

                User owner = findUserById(ownerId);
                House house = null;

                if (type.equals("Apartment")) {
                    int unitNumber = Integer.parseInt(p[9]);
                    int buildingFloors = Integer.parseInt(p[10]);
                    int totalUnits = Integer.parseInt(p[11]);
                    house = new Apartment(id, area, bedrooms, bathrooms, floor, region, owner, status,
                            unitNumber, buildingFloors, totalUnits);

                } else if (type.equals("Villa")) {
                    double landArea = Double.parseDouble(p[9]);
                    int floors = Integer.parseInt(p[10]);
                    house = new Villa(id, area, bedrooms, bathrooms, floor, region, owner, status,
                            landArea, floors);

                } else if (type.equals("Penthouse")) {
                    double terraceArea = Double.parseDouble(p[9]);
                    double luxuryCoefficient = Double.parseDouble(p[10]);
                    house = new Penthouse(id, area, bedrooms, bathrooms, floor, region, owner, status,
                            terraceArea, luxuryCoefficient);
                }

                if (house != null) {
                    houses.add(house);

                    if (status.equals("Sold") && owner != null)
                        owner.addBoughtHouse(house);

                    if (status.equals("Both") || status.equals("Sold"))
                        agency.addHouse(house);
                }

                if (id > maxId)
                    maxId = id;
            }
            houseId = maxId + 1;

        } catch (IOException e) {
        }
    }

    private void loadContracts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONTRACTS_FILE))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] p = line.split(",");
                int id = Integer.parseInt(p[0]);
                int houseIdRead = Integer.parseInt(p[1]);
                int ownerId = Integer.parseInt(p[2]);
                int renterId = Integer.parseInt(p[3]);
                double price = Double.parseDouble(p[4]);
                int duration = Integer.parseInt(p[5]);

                House house = findHouseById(houseIdRead);
                User owner = findUserById(ownerId);
                User renter = findUserById(renterId);

                if (house != null && owner != null && renter != null) {
                    Contract contract = new Contract(id, house, owner, renter, price, duration);
                    contracts.add(contract);
                    renter.addRentedHouse(house);
                }

                if (id > maxId)
                    maxId = id;
            }
            contractId = maxId + 1;

        } catch (IOException e) {
        }
    }
}
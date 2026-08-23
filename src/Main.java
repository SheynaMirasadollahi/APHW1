import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static RealEstateSystem system = new RealEstateSystem();
    static User currentUser = null;

    public static void main(String[] args) {

        system.loadAll();

        while (true) {

            System.out.println("\n--- Real Estate System ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Add Apartment");
            System.out.println("4. Add Villa");
            System.out.println("5. Add Penthouse");
            System.out.println("6. Show All Houses");
            System.out.println("7. Show Houses For Sale");
            System.out.println("8. Show Houses For Rent");
            System.out.println("9. Show House Details By ID");
            System.out.println("10. Create Rent Contract");
            System.out.println("11. Show All Contracts");
            System.out.println("12. Show Contract By ID");
            System.out.println("13. Buy House");
            System.out.println("14. Show My Bought Houses");
            System.out.println("15. Show My Rented Houses");
            System.out.println("16. Sell House To Agency (Instant Sale)");
            System.out.println("17. Show My Contracts");
            System.out.println("18. Show Balance");
            System.out.println("19. Cancel Contract");
            System.out.println("20. Exit");
            System.out.println("Choose: ");
            int choice = input.nextInt();

            if (choice == 1)
                signUp();
            else if (choice == 2)
                login();
            else if (choice == 3)
                addApartment();
            else if (choice == 4)
                addVilla();
            else if (choice == 5)
                addPenthouse();
            else if (choice == 6)
                system.showHouses();
            else if (choice == 7)
                system.showHousesForSale();
            else if (choice == 8)
                system.showHousesForRent();
            else if (choice == 9)
                showHouseById();
            else if (choice == 10)
                createContract();
            else if (choice == 11)
                system.showContracts();
            else if (choice == 12)
                showContractById();
            else if (choice == 13)
                buyHouse();
            else if (choice == 14)
                showMyBoughtHouses();
            else if (choice == 15)
                showMyRentedHouses();
            else if (choice == 16)
                sellToAgency();
            else if (choice == 17)
                showMyContracts();
            else if (choice == 18)
                showBalance();
            else if (choice == 19)
                cancelContract();
            else if (choice == 20) {
                system.saveAll();
                System.out.println("Data saved. Bye!");
                break;
            } else
                System.out.println("Wrong choice");
        }

        input.close();
    }

    static void signUp() {
        System.out.println("Username: ");
        String username = input.next();

        System.out.println("Password: ");
        String password1 = input.next();

        System.out.println("Password again: ");
        String password2 = input.next();

        if (!password1.equals(password2)) {
            System.out.println("Passwords do not match. Try again.");
            return;
        }

        System.out.println("Balance: ");
        double balance = input.nextDouble();

        boolean success = system.addUser(username, password1, balance);

        if (success)
            System.out.println("User added");
        else
            System.out.println("Username already taken");
    }

    static void login() {
        System.out.println("Username: ");
        String username = input.next();

        System.out.println("Password: ");
        String password = input.next();

        currentUser = system.login(username, password);

        if (currentUser != null)
            System.out.println("Login successful");
        else
            System.out.println("Wrong username or password");
    }

    static double[] readCommonHouseFields() {

        System.out.println("Area: ");
        double area = input.nextDouble();

        System.out.println("Bedrooms: ");
        double bedrooms = input.nextInt();

        System.out.println("Bathrooms: ");
        double bathrooms = input.nextInt();

        System.out.println("Floor: ");
        double floor = input.nextInt();

        System.out.println("Region (1-4): ");
        double region = input.nextInt();

        return new double[] { area, bedrooms, bathrooms, floor, region };
    }

    static String readStatus() {
        System.out.println("Status (Sale/Rent/Both): ");
        String status = input.next();

        while (!status.equals("Sale") && !status.equals("Rent") && !status.equals("Both")) {
            System.out.println("Invalid status. Enter Sale, Rent or Both: ");
            status = input.next();
        }
        return status;
    }

    static void addApartment() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }

        double[] common = readCommonHouseFields();

        System.out.println("Unit number: ");
        int unit = input.nextInt();

        System.out.println("Building floors: ");
        int floors = input.nextInt();

        System.out.println("Total units: ");
        int totalUnits = input.nextInt();

        String status = readStatus();

        int id = system.getNextHouseId();
        Apartment apartment = new Apartment(id, common[0], (int) common[1], (int) common[2], (int) common[3],
                (int) common[4], currentUser, status, unit, floors, totalUnits);

        system.addHouse(apartment);
        System.out.println("Apartment added");
    }

    static void addVilla() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }

        double[] common = readCommonHouseFields();

        System.out.println("Land area: ");
        double landArea = input.nextDouble();

        System.out.println("Floors: ");
        int floors = input.nextInt();

        String status = readStatus();

        int id = system.getNextHouseId();
        Villa villa = new Villa(id, common[0], (int) common[1], (int) common[2], (int) common[3],
                (int) common[4], currentUser, status, landArea, floors);

        system.addHouse(villa);
        System.out.println("Villa added");
    }

    static void addPenthouse() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }

        double[] common = readCommonHouseFields();

        System.out.println("Terrace area: ");
        double terrace = input.nextDouble();

        System.out.println("Luxury coefficient (e.g. 1.5): ");
        double luxury = input.nextDouble();

        String status = readStatus();

        int id = system.getNextHouseId();
        Penthouse penthouse = new Penthouse(id, common[0], (int) common[1], (int) common[2], (int) common[3],
                (int) common[4], currentUser, status, terrace, luxury);

        system.addHouse(penthouse);
        System.out.println("Penthouse added");
    }

    static void showHouseById() {
        System.out.println("House ID: ");
        int id = input.nextInt();

        House house = system.findHouseById(id);
        if (house == null) {
            System.out.println("House not found");
            return;
        }

        house.showInfo();
        System.out.println("Sale Price: " + house.calculatePrice());
        System.out.println("Monthly Rent: " + house.calculateMonthlyRent());
    }

    static void showContractById() {
        System.out.println("Contract ID: ");
        int id = input.nextInt();

        Contract contract = system.findContractById(id);
        if (contract == null) {
            System.out.println("Contract not found");
            return;
        }
        contract.showInfo();
    }

    static void showMyBoughtHouses() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }
        currentUser.showBoughtHouses();
    }

    static void showMyRentedHouses() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }
        currentUser.showRentedHouses();
    }

    static void showMyContracts() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }
        system.showContracts(currentUser.getId());
    }

    static void showBalance() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }
        System.out.println("Balance: " + currentUser.getBalance());
    }

    static void createContract() {
        System.out.println("House ID: ");
        int id = input.nextInt();

        System.out.println("Renter User ID: ");
        int renterId = input.nextInt();

        boolean result = system.createContract(renterId, id);
        if (result)
            System.out.println("Contract created");
        else
            System.out.println("Cannot create contract");
    }

    static void buyHouse() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }

        System.out.println("House ID: ");
        int id = input.nextInt();

        boolean result = system.buyHouse(currentUser.getId(), id);

        if (result)
            System.out.println("House bought");
        else
            System.out.println("Cannot buy house");
    }

    static void sellToAgency() {
        if (currentUser == null) {
            System.out.println("Login first");
            return;
        }

        System.out.println("House ID: ");
        int id = input.nextInt();

        if (system.sellToAgency(currentUser.getId(), id))
            System.out.println("House sold to agency");
        else
            System.out.println("House not found");
    }

    static void cancelContract() {
        System.out.println("Contract ID: ");
        int id = input.nextInt();

        if (system.cancelContract(id))
            System.out.println("Contract cancelled");
        else
            System.out.println("Cannot cancel contract (not found or insufficient balance for penalty)");
    }
}
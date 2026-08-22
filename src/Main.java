import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<User> users = new ArrayList<>();
        ArrayList<House> houses = new ArrayList<>();
        ArrayList<Contract> contracts = new ArrayList<>();
        Agency agency = new Agency("Real Estate Agency");

        int userId = 1;
        int houseId = 1;
        int contractId = 1;

        while (true) {

            System.out.println("\n--- Real Estate System ---");
            System.out.println("1. Create User");
            System.out.println("2. Add Apartment");
            System.out.println("3. Add Villa");
            System.out.println("4.Add Penthouse");
            System.out.println("5. Show Houses");
            System.out.println("6. Show Users");
            System.out.println("7. Create Contracts");
            System.out.println("8. Show Contracts");
            System.out.println("9. Show Agency Houses");
            System.out.println("10. Exit");
            System.out.println("Choose: ");
            int choice = input.nextInt();

            if (choice == 1) {
                System.out.println("Username: ");
                String username = input.next();

                System.out.println("Password: ");
                String password = input.next();

                System.out.println("Balance: ");
                double Balance = input.nextDouble();

                User user = new User( userId, username, password, Balance);

                users.add(user);
                userId++;
                System.out.println("User added");
            }
            
            else if (choice == 2) {
            if (users.size() == 0)
                System.out.println("First create a user");

            else {
                User owner = users.get(0);

                System.out.println("Area: ");
                double area = input.nextDouble();

                System.out.println("Bedrooms: ");
                int bedrooms = input.nextInt();

                System.out.println("Bathrooms: ");
                int bathrooms = input.nextInt();

                System.out.println("Floor: ");
                int floor = input.nextInt();

                System.out.println("Region: ");
                int region = input.nextInt();

                System.out.println("Unit number: ");
                int Unit = input.nextInt();

                System.out.println("Building floors: ");
                int floors = input.nextInt();

                System.out.println("Total units: ");
                int totalUnits = input.nextInt();

                Apartment apartment = new Apartment(houseId, area, bedrooms, bathrooms, floor, region, owner,"Sale", Unit, floors, totalUnits);

                houses.add(apartment);
                agency.addHouse(apartment);
                houseId++;
                System.out.println("Apartment added");
            }
        }

        else if (choice == 3) {
            if (users.size() == 0)
                System.out.println("Craete user first");

            else {
                User owner = users.get(0);

                System.out.println("Area: ");
                double area = input.nextDouble();

                System.out.println("Bedrooms: ");
                int bedrooms = input.nextInt();

                System.out.println("Bathrooms: ");
                int bathrooms = input.nextInt();

                System.out.println("Floor: ");
                int floor = input.nextInt();

                System.out.println("Region: ");
                int region = input.nextInt();

                System.out.println("Land area: ");
                double landArea = input.nextDouble();

                System.out.println("Floors: ");
                int floors = input.nextInt();

                Villa villa = new Villa(houseId, area, bedrooms, bathrooms, floor, region, owner, "Sale", landArea, floors);

                houses.add(villa);
                agency.addHouse(villa);
                houseId++;
                System.out.println("Villa added");
            }
        }

        else if (choice == 4) {
            if (users.size() == 0)
                System.out.println("Create user first");

            else {
                User owner = users.get(0);

                System.out.println("Area: ");
                double area = input.nextDouble();

                System.out.println("Bedrooms: ");
                int bedrooms = input.nextInt();

                System.out.println("Bathrooms: ");
                int bathrooms = input.nextInt();

                System.out.println("Floor: ");
                int floor = input.nextInt();

                System.out.println("Region: ");
                int region = input.nextInt();

                System.out.println("Terrace area: ");
                double terrace = input.nextDouble();

                System.out.println("Luxury coefficient: ");
                double Luxury = input.nextDouble();

                Penthouse penthouse = new Penthouse(houseId, area, bedrooms, bathrooms, floor, region, owner, "Sale", terrace, Luxury);

                houses.add(penthouse);
                agency.addHouse(penthouse);
                houseId++;
                System.out.println("Penthouse added");
            }
        }

        else if (choice == 5) {
            for (House house : houses) {
                house.showInfo();
                System.out.println("Price:" + house.calculatePrice());
                System.out.println("-------------");
            }
        }

        else if (choice == 6) {
            for (User user : users) {
                user.showInfo();
                System.out.println("--------------");
            }
        }

        else if (choice == 7) {
            System.out.println("House ID: ");
            int id = input.nextInt();

            System.out.println("Renter User ID: ");
            int renterId = input.nextInt();

            House selectdHouse = null;

            for (House house : houses) {
                if (house.getId() == id )
                    selectdHouse = house;
            }

            User renter = users.get(renterId - 1);

            Contract contract = new Contract(contractId, selectdHouse, selectdHouse.getOwner(), renter, selectdHouse.calculatePrice(), 12 );

            contracts.add(contract);
            contractId++;
            System.out.println("Contract created");
        }

        else if (choice == 8) {
            for (Contract contract : contracts)
                contract.showInfo();
            System.out.println("-------------");
        }

        else if (choice == 9)
            agency.showHouses();

        else if (choice == 10) {
            System.out.println("Exit");
            break;
        }

        else
            System.out.println("Wrong chice");
        }

        input.close();
    }
}
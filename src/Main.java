import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<House> houses = new ArrayList<>();
    static ArrayList<Contract> contracts = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Real Estate System ---");
            System.out.println("1. Show Houses");
            System.out.println("2. Exit");
            System.out.println("Choose: ");

            int choice = input.nextInt();

            if (choice == 1)
                showHouses();
            
            else if (choise == 2) {
                System.out.println("Goodbye");
                break;
            }

            else
                System.out.println("Invalid choice");
        }

        input.close();
    }

    public static void showHouses() {

        if (houses.size() == 0) 
            System.out.println("No houses available");

        else 
            for (House house : houses) {
                
                house.showInfo();
                System.out.println("-------------");
            }
    }
}
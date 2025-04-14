package main;

import dao.DonationDAO;
import dao.DonationDAOImpl;
import dao.PetDAO;
import dao.PetDAOImpl;
import entity.model.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        PetDAO petDAO = new PetDAOImpl();
        DonationDAO donationDAO = new DonationDAOImpl();
        PetShelter petShelter = new PetShelter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPet Adoption Platform");
            System.out.println("1. Add Pet");
            System.out.println("2. List Available Pets");
            System.out.println("3. Add Donation");
            System.out.println("4. List Donations");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Pet
                    System.out.println("Enter pet type (Dog/Cat):");
                    String petType = scanner.nextLine();
                    System.out.println("Enter pet name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter pet age:");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter pet breed:");
                    String breed = scanner.nextLine();
                    Pet pet;

                    try {
                        if ("Dog".equalsIgnoreCase(petType)) {
                            pet = new Dog(name, age, breed);
                        } else if ("Cat".equalsIgnoreCase(petType)) {
                            System.out.println("Enter cat color:");
                            String color = scanner.nextLine(); // Optional: color input for Cat
                            pet = new Cat(name, age, breed, color);
                        } else {
                            System.out.println("Invalid pet type. Please enter either Dog or Cat.");
                            break; // Exit this case if the pet type is invalid
                        }

                        // Add pet to the DAO and the shelter
                        petDAO.addPet(pet);
                        petShelter.addPet(pet);
                        System.out.println("Pet added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2: // List Available Pets
                    System.out.println("Available Pets:");
                    for (Pet p : petShelter.listAvailablePets()) {
                        System.out.println(p);
                    }
                    break;

                case 3: // Add Donation
                    System.out.println("Enter donor name:");
                    String donorName = scanner.nextLine();
                    System.out.println("Enter donation amount:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Is it a cash or item donation? (cash/item):");
                    String type = scanner.nextLine();
                    LocalDateTime donationDate = LocalDateTime.now();
                    if ("cash".equalsIgnoreCase(type)) {
                        
                        CashDonation cashDonation = new CashDonation(donorName, amount, donationDate);
                        donationDAO.addDonation(cashDonation);
                    } else {
                        System.out.println("Enter item type:");
                        String itemType = scanner.nextLine();
                        ItemDonation itemDonation = new ItemDonation(donorName, amount, itemType, donationDate);
                        donationDAO.addDonation(itemDonation);
                    }
                    System.out.println("Donation added successfully.");
                    break;

                case 4: // List Donations
                    System.out.println("Donations:");
                    for (Donation donation : donationDAO.getAllDonations()) {
                        System.out.println(donation.getDonationDate()+", "+donation.getDonorName());
                    }
                    break;

                case 5: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

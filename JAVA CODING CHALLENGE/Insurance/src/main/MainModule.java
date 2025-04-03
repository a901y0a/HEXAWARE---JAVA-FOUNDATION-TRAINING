package main;

import dao.InsuranceServiceImpl;
import entity.Claim;
import entity.Policy;
import exception.PolicyNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\n========== INSURANCE MANAGEMENT SYSTEM ==========");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy by ID");
            System.out.println("3. Get All Policies");
            System.out.println("4. File a Claim");
            System.out.println("5. View All Claims");
            System.out.println("6. Update Policy");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1: // Create Policy
                    System.out.print("Enter Policy ID: ");
                    int policyId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Policy Name: ");
                    String policyName = scanner.nextLine();
                    System.out.print("Enter Premium Amount: ");
                    double premiumAmount = scanner.nextDouble();

                    Policy policy = new Policy(policyId, policyName, premiumAmount);
                    boolean created = insuranceService.createPolicy(policy);
                    System.out.println(created ? "✅ Policy created successfully!" : "❌ Failed to create policy.");
                    break;

                case 2: // Get Policy by ID
                    System.out.print("Enter Policy ID to search: ");
                    int searchId = scanner.nextInt();
                    try {
                        Policy foundPolicy = insuranceService.getPolicy(searchId);
                        System.out.println("✅ Policy Found: " + foundPolicy);
                    } catch (PolicyNotFoundException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;

                case 3: // Get All Policies
                    List<Policy> policies = insuranceService.getAllPolicies();
                    if (policies.isEmpty()) {
                        System.out.println("❌ No policies found.");
                    } else {
                        System.out.println("✅ Available Policies:");
                        for (Policy p : policies) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 4: // File a Claim
                    System.out.print("Enter Claim ID: ");
                    int claimId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Claim Number: ");
                    String claimNumber = scanner.nextLine();
                    System.out.print("Enter Claim Amount: ");
                    double claimAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Claim Status: ");
                    String status = scanner.nextLine();
                    System.out.print("Enter Date Filed (yyyy-MM-dd): ");
                    String dateFiledStr = scanner.nextLine();
                    
                    Date dateFiled;
                    try {
                        dateFiled = dateFormat.parse(dateFiledStr);
                    } catch (ParseException e) {
                        System.out.println("❌ Invalid date format. Please use yyyy-MM-dd.");
                        break;
                    }

                    System.out.print("Enter Policy ID Associated with Claim: ");
                    int policyForClaimId = scanner.nextInt();
                    System.out.print("Enter Client ID Associated with Claim: ");
                    int clientForClaimId = scanner.nextInt();

                    try {
                        // Check if policy exists
                        insuranceService.getPolicy(policyForClaimId);
                        // Check if client exists
                        insuranceService.getClient(clientForClaimId);
                        
                        Claim claim = new Claim(claimId, claimNumber, dateFiled, claimAmount, status, policyForClaimId, clientForClaimId);
                        boolean claimCreated = insuranceService.createClaim(claim);
                        System.out.println(claimCreated ? "✅ Claim filed successfully!" : "❌ Failed to file claim.");
                    } catch (Exception e) {
                        System.out.println("❌ Invalid Policy or Client. Claim cannot be filed.");
                    }
                    break;

                case 5: // View All Claims
                    List<Claim> claims = insuranceService.getAllClaims();
                    if (claims.isEmpty()) {
                        System.out.println("❌ No claims found.");
                    } else {
                        System.out.println("✅ Available Claims:");
                        for (Claim c : claims) {
                            System.out.println(c);
                        }
                    }
                    break;
                case 6: // Update Policy
                    System.out.print("Enter Policy ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Policy Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Premium Amount: ");
                    double newPremium = scanner.nextDouble();

                    Policy updatedPolicy = new Policy(updateId, newName, newPremium);
                    boolean isUpdated = insuranceService.updatePolicy(updatedPolicy);
                    System.out.println(isUpdated ? "✅ Policy updated successfully!" : "❌ Failed to update policy.");
                    break;

               


                case 7: // Exit
                    System.out.println("🚀 Exiting Insurance Management System. Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }
}

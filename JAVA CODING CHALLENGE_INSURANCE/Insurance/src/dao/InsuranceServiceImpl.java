package dao;

import entity.Claim;
import entity.Client;
import entity.Policy;
import exception.PolicyNotFoundException;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsuranceServiceImpl implements IPolicyService {
    
    private Connection conn = DBConnection.getConnection();

    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO Policy (policyId, policyName, premiumAmount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, policy.getPolicyId());
            stmt.setString(2, policy.getPolicyName());
            stmt.setDouble(3, policy.getPremiumAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        String query = "SELECT * FROM Policy WHERE policyId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Policy(rs.getInt("policyId"), rs.getString("policyName"), rs.getDouble("premiumAmount"));
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PolicyNotFoundException("Database error occurred.");
        }
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String query = "SELECT * FROM Policy";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                policies.add(new Policy(rs.getInt("policyId"), rs.getString("policyName"), rs.getDouble("premiumAmount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE Policy SET policyName = ?, premiumAmount = ? WHERE policyId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, policy.getPolicyName());
            stmt.setDouble(2, policy.getPremiumAmount());
            stmt.setInt(3, policy.getPolicyId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM Policy WHERE policyId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 New Method: Get Client by ID
 // 🔹 Get Client by ID (Including Policy)
    public Client getClient(int clientId) {
        String query = "SELECT c.clientId, c.clientName, c.contactInfo, c.policyId, " +
                       "p.policyName, p.premiumAmount " +
                       "FROM Client c LEFT JOIN Policy p ON c.policyId = p.policyId " +
                       "WHERE c.clientId = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int policyId = rs.getInt("policyId");
                Policy policy = null;
                
                // Check if policy exists for the client
                if (policyId > 0) {
                    policy = new Policy(
                        policyId,
                        rs.getString("policyName"),
                        rs.getDouble("premiumAmount")
                    );
                }

                return new Client(
                    rs.getInt("clientId"),
                    rs.getString("clientName"),
                    rs.getString("contactInfo"),
                    rs.getInt("PolicyId")  // Now assigning the fetched policy
                );
            } else {
                System.out.println("❌ Client not found.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 🔹 New Method: Create a Claim
    public boolean createClaim(Claim claim) {
        String query = "INSERT INTO Claim (claimNumber, dateFiled, claimAmount, status, policyId, clientId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, claim.getClaimNumber());
            stmt.setDate(2, new java.sql.Date(claim.getDateFiled().getTime()));
            stmt.setDouble(3, claim.getClaimAmount());
            stmt.setString(4, claim.getStatus());
            stmt.setInt(5, claim.getPolicyId());  // Directly using policyId
            stmt.setInt(6, claim.getClientId());  // Directly using clientId
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // 🔹 New Method: Get All Claims
    public List<Claim> getAllClaims() {
        List<Claim> claims = new ArrayList<>();
        String query = "SELECT * FROM Claim";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                claims.add(new Claim(
                    rs.getInt("claimId"),
                    rs.getString("claimNumber"),
                    rs.getDate("dateFiled"),
                    rs.getDouble("claimAmount"),
                    rs.getString("status"),
                    rs.getInt("policyId"),  // Directly fetching policyId
                    rs.getInt("clientId")   // Directly fetching clientId
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }

public boolean updatePolicy1(Policy policy) {
    String query = "UPDATE Policy SET policyName = ?, premiumAmount = ? WHERE policyId = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, policy.getPolicyName());
        stmt.setDouble(2, policy.getPremiumAmount());
        stmt.setInt(3, policy.getPolicyId());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}



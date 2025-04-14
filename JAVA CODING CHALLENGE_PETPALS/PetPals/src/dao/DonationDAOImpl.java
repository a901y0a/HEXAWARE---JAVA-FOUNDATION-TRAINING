package dao;

import entity.model.CashDonation;
import entity.model.Donation;
import entity.model.ItemDonation;
import utily.DBConnUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonationDAOImpl implements DonationDAO {
    private static final String INSERT_DONATION_SQL = "INSERT INTO donations (donor_name, amount, donation_type, donation_date, item_type) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_DONATIONS_SQL = "SELECT * FROM donations";

    @Override
    public void addDonation(Donation donation) {
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware JDBC\\PetPals\\resources\\db.properties");
             PreparedStatement stmt = conn.prepareStatement(INSERT_DONATION_SQL)) {

            stmt.setString(1, donation.getDonorName());
            stmt.setDouble(2, donation.getAmount());
            
            if (donation instanceof CashDonation) {
                stmt.setString(3, "Cash");
                stmt.setTimestamp(4, Timestamp.valueOf(((CashDonation) donation).getDonationDate()));
                stmt.setString(5, null);
            } else if (donation instanceof ItemDonation) {
                stmt.setString(3, "Item");
               // LocalDateTime donationDate = LocalDateTime.now();
                stmt.setTimestamp(4, Timestamp.valueOf(((ItemDonation) donation).getDonationDate()));
                stmt.setString(5, ((ItemDonation) donation).getItemType());
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Donation> getAllDonations() {
        List<Donation> donations = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware JDBC\\PetPals\\resources\\db.properties");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_DONATIONS_SQL)) {

            while (rs.next()) {
                String donorName = rs.getString("donor_name");
                double amount = rs.getDouble("amount");
                String donationType = rs.getString("donation_type");
                //Date donationDate = rs.getDate("donation_date");
                

                if ("Cash".equalsIgnoreCase(donationType)) {
                	LocalDateTime donationDate = rs.getTimestamp("donation_date").toLocalDateTime();
                	System.out.println("Donation date is "+donationDate);
                    donations.add(new CashDonation(donorName, amount, donationDate));
                } else if ("Item".equalsIgnoreCase(donationType)) {
                	LocalDateTime donationDate = rs.getTimestamp("donation_date").toLocalDateTime();
                    String itemType = rs.getString("item_type");
                    donations.add(new ItemDonation(donorName, amount, itemType, donationDate));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donations;
    }
}
package dao;

import entity.model.Donation;
import java.util.List;

public interface DonationDAO {
    void addDonation(Donation donation);
    List<Donation> getAllDonations();
}

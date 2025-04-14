package entity.model;
import java.time.LocalDateTime;
public class CashDonation extends Donation {
	//private LocalDateTime donationDate;

	public CashDonation(String donorName, double amount, LocalDateTime donationDate) {
		super(donorName, amount);
		this.donationDate = donationDate;
	}

//	public LocalDateTime getDonationDate() { return donationDate; }
//	public void setDonationDate(LocalDateTime donationDate) { this.donationDate = donationDate; }

	@Override
	public String toString() {
		return "CashDonation [donationDate=" + donationDate + ", donorName=" + donorName + ", amount=" + amount
				+ ", getDonationDate()=" + getDonationDate() + ", getDonorName()=" + getDonorName() + ", getAmount()="
				+ getAmount() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public void recordDonation() {
		System.out.println("Recording cash donation from " + donorName + " of amount $" + amount + " on " + donationDate);
	}
}

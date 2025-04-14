package entity.model;

import java.time.LocalDateTime;

public abstract class Donation {
	protected String donorName;
	protected double amount;
	protected LocalDateTime donationDate;

	public Donation(String donorName, double amount) {
		this.donorName = donorName;
		this.amount = amount;
	}
	
	

	public Donation(String donorName, double amount, LocalDateTime donationDate) {
		super();
		this.donorName = donorName;
		this.amount = amount;
		this.donationDate = donationDate;
	}



	public String getDonorName() { return donorName; }
	public void setDonorName(String donorName) { this.donorName = donorName; }

	public double getAmount() { return amount; }
	public void setAmount(double amount) { this.amount = amount; }

	public abstract void recordDonation();
	
	public LocalDateTime getDonationDate() { return donationDate; }
	public void setDonationDate(LocalDateTime donationDate) { this.donationDate = donationDate; }
}

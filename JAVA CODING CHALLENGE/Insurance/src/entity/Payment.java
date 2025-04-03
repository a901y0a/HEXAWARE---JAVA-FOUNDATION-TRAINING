package entity;

import java.util.Date;

public class Payment {
    private int paymentId;
    private Date paymentDate;
    private double paymentAmount;
    private int clientId;  // Storing clientId instead of a full Client object

    public Payment() {}

    public Payment(int paymentId, Date paymentDate, double paymentAmount, int clientId) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.clientId = clientId;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    @Override
    public String toString() {
        return "Payment{paymentId=" + paymentId + ", paymentAmount=" + paymentAmount + ", clientId=" + clientId + "}";
    }
}

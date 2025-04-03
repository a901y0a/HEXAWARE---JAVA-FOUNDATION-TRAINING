package entity;

import java.util.Date;

public class Claim {
    private int claimId;
    private String claimNumber;
    private Date dateFiled;
    private double claimAmount;
    private String status;
    private int policyId;  // Storing policyId instead of Policy object
    private int clientId;  // Storing clientId instead of Client object

    public Claim() {}

    public Claim(int claimId, String claimNumber, Date dateFiled, double claimAmount, String status, int policyId, int clientId) {
        this.claimId = claimId;
        this.claimNumber = claimNumber;
        this.dateFiled = dateFiled;
        this.claimAmount = claimAmount;
        this.status = status;
        this.policyId = policyId;
        this.clientId = clientId;
    }

    public int getClaimId() { return claimId; }
    public void setClaimId(int claimId) { this.claimId = claimId; }

    public String getClaimNumber() { return claimNumber; }
    public void setClaimNumber(String claimNumber) { this.claimNumber = claimNumber; }

    public Date getDateFiled() { return dateFiled; }
    public void setDateFiled(Date dateFiled) { this.dateFiled = dateFiled; }

    public double getClaimAmount() { return claimAmount; }
    public void setClaimAmount(double claimAmount) { this.claimAmount = claimAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPolicyId() { return policyId; }
    public void setPolicyId(int policyId) { this.policyId = policyId; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    @Override
    public String toString() {
        return "Claim{claimId=" + claimId + ", claimNumber='" + claimNumber + "', status='" + status + "', policyId=" + policyId + ", clientId=" + clientId + "}";
    }
}

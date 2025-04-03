package entity;

public class Policy {
    private int policyId;
    private String policyName;
    private double premiumAmount;

    public Policy() {}

    public Policy(int policyId, String policyName, double premiumAmount) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.premiumAmount = premiumAmount;
    }

    public int getPolicyId() { return policyId; }
    public void setPolicyId(int policyId) { this.policyId = policyId; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public double getPremiumAmount() { return premiumAmount; }
    public void setPremiumAmount(double premiumAmount) { this.premiumAmount = premiumAmount; }

    @Override
    public String toString() {
        return "Policy{policyId=" + policyId + ", policyName='" + policyName + "'}";
    }
}

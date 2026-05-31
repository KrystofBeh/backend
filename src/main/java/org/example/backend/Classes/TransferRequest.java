package org.example.backend.Classes;

public class TransferRequest {
    private Long userId;
    private Long sourceAccountId;
    private String targetAccountName;
    private double amount;

    // Gettery a Settery
    public Long getUserId() { return userId; }
    public void setUserId( long userId) { this.userId = userId; }

    public Long getSourceAccountId() { return sourceAccountId; }
    public void setSourceAccountId(Long sourceAccountId) { this.sourceAccountId = sourceAccountId; }

    public String getTargetAccountName() { return targetAccountName; }
    public void setTargetAccountId(String targetAccountId) { this.targetAccountName = targetAccountName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
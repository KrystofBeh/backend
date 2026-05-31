package org.example.backend.Classes;

public class TransferRequest {
    private Long userId; // případně Long/int podle toho, jaký typ používáš
    private Long sourceAccountId;
    private Long targetAccountId;
    private double amount;

    // Gettery a Settery
    public Long getUserId() { return userId; }
    public void setUserId( long userId) { this.userId = userId; }

    public Long getSourceAccountId() { return sourceAccountId; }
    public void setSourceAccountId(Long sourceAccountId) { this.sourceAccountId = sourceAccountId; }

    public Long getTargetAccountId() { return targetAccountId; }
    public void setTargetAccountId(Long targetAccountId) { this.targetAccountId = targetAccountId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
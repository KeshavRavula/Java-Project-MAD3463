package models;

import java.util.Date;
import java.util.UUID;

public class Transactions {
    private UUID tranxID;
    private String tranxType;
    private Date tranxDate;
    private double tranxAmount;

    public UUID getTranxID() {
        return tranxID;
    }

    public void setTranxID(UUID tranxID) {
        this.tranxID = tranxID;
    }

    public String getTranxType() {
        return tranxType;
    }

    public void setTranxType(String tranxType) {
        this.tranxType = tranxType;
    }

    public Date getTranxDate() {
        return tranxDate;
    }

    public void setTranxDate(Date tranxDate) {
        this.tranxDate = tranxDate;
    }

    public double getTranxAmount() {
        return tranxAmount;
    }

    public void setTranxAmount(double tranxAmount) {
        this.tranxAmount = tranxAmount;
    }

    @Override
    public String toString() {
        return "Transactions:" + "\nTransaction ID=" + tranxID +
                "\nTransaction Type=" + tranxType+
                "\nTransaction Date=" + tranxDate +
                "\nTransaction Amount=" + tranxAmount;
    }
}

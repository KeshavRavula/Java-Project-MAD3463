package models;

import java.util.Date;
import java.util.UUID;

public class Transactions {
    private UUID tranxID;
    private String tranxType;
    private Date tranxDate;

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
}

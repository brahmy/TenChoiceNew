package com.vmrits.android.app;

public class TransactionHistoryPOJO {
    private String trnsaction_id;

    public String getTrnsaction_id() {
        return trnsaction_id;
    }

    public void setTrnsaction_id(String trnsaction_id) {
        this.trnsaction_id = trnsaction_id;
    }

    public String getTransaction_approved_date() {
        return transaction_approved_date;
    }

    public void setTransaction_approved_date(String transaction_approved_date) {
        this.transaction_approved_date = transaction_approved_date;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    private String transaction_approved_date;
    private String transaction_amount;
    private String transaction_date;
}

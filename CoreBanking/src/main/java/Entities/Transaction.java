package Entities;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class Transaction {

    private int idtransaction;
    private Date transaction_date;
    private Time transaction_time;
    private BigDecimal transaction_amount;
    private char transaction_status;
    private int transaction_customer_id;
    private int transaction_origin;
    private int transaction_destination;
    private String kind_of_transaction;

    public Transaction(int idtransaction, Date transaction_date, Time transaction_time, BigDecimal transaction_amount, char transaction_status, int transaction_customer_id, int transaction_origin, int transaction_destination, String kind_of_transaction) {
        this.idtransaction = idtransaction;
        this.transaction_date = transaction_date;
        this.transaction_time = transaction_time;
        this.transaction_amount = transaction_amount;
        this.transaction_status = transaction_status;
        this.transaction_customer_id = transaction_customer_id;
        this.transaction_origin = transaction_origin;
        this.transaction_destination = transaction_destination;
        this.kind_of_transaction = kind_of_transaction;
    }

    public Transaction(){}

    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Time getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(Time transaction_time) {
        this.transaction_time = transaction_time;
    }

    public BigDecimal getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(BigDecimal transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public char getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(char transaction_status) {
        this.transaction_status = transaction_status;
    }

    public int getTransaction_customer_id() {
        return transaction_customer_id;
    }

    public void setTransaction_customer_id(int transaction_customer_id) {
        this.transaction_customer_id = transaction_customer_id;
    }

    public int getTransaction_origin() {
        return transaction_origin;
    }

    public void setTransaction_origin(int transaction_origin) {
        this.transaction_origin = transaction_origin;
    }

    public int getTransaction_destination() {
        return transaction_destination;
    }

    public void setTransaction_destination(int transaction_destination) {
        this.transaction_destination = transaction_destination;
    }

    public String getKind_of_transaction() {
        return kind_of_transaction;
    }

    public void setKind_of_transaction(String kind_of_transaction) {
        this.kind_of_transaction = kind_of_transaction;
    }
}

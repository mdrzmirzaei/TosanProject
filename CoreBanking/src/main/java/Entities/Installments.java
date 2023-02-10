package Entities;

import java.util.Date;

public class Installments {
    private String installments_idloan;
    private int installments_months;
    private double installments_principle_amount;
    private double installments_interest;
    private double installments_sum_pi_amount;
    private char installments_status;
    private int installments_customer_id;
    private int installments_account_id;
    private Date installments_dueDate;

    public Installments(String installments_idloan, int installments_months, double installments_principle_amount, double installments_interest, double installments_sum_pi_amount, char installments_status, int installments_customer_id, int installments_account_id, Date installments_dueDate) {
        this.installments_idloan = installments_idloan;
        this.installments_months = installments_months;
        this.installments_principle_amount = installments_principle_amount;
        this.installments_interest = installments_interest;
        this.installments_sum_pi_amount = installments_sum_pi_amount;
        this.installments_status = installments_status;
        this.installments_customer_id = installments_customer_id;
        this.installments_account_id = installments_account_id;
        this.installments_dueDate = installments_dueDate;

    }

    public String getInstallments_idloan() {
        return installments_idloan;
    }

    public void setInstallments_idloan(String installments_idloan) {
        this.installments_idloan = installments_idloan;
    }

    public int getInstallments_months() {
        return installments_months;
    }

    public void setInstallments_months(int installments_months) {
        this.installments_months = installments_months;
    }

    public double getInstallments_principle_amount() {
        return installments_principle_amount;
    }

    public void setInstallments_principle_amount(double installments_principle_amount) {
        this.installments_principle_amount = installments_principle_amount;
    }

    public double getInstallments_interest() {
        return installments_interest;
    }

    public void setInstallments_interest(double installments_interest) {
        this.installments_interest = installments_interest;
    }

    public double getInstallments_sum_pi_amount() {
        return installments_sum_pi_amount;
    }

    public void setInstallments_sum_pi_amount(double installments_sum_pi_amount) {
        this.installments_sum_pi_amount = installments_sum_pi_amount;
    }

    public char getInstallments_status() {
        return installments_status;
    }

    public void setInstallments_status(char installments_status) {
        this.installments_status = installments_status;
    }

    public int getInstallments_customer_id() {
        return installments_customer_id;
    }

    public void setInstallments_customer_id(int installments_customer_id) {
        this.installments_customer_id = installments_customer_id;
    }

    public int getInstallments_account_id() {
        return installments_account_id;
    }

    public void setInstallments_account_id(int installments_account_id) {
        this.installments_account_id = installments_account_id;
    }

    public Date getInstallments_dueDate() {
        return installments_dueDate;
    }

    public void setInstallments_dueDate(Date installments_dueDate) {
        this.installments_dueDate = installments_dueDate;
    }
}

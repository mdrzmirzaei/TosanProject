package installments;

import java.util.Date;

public class installments {
    private String installments_idloan;
    private int installments_months;
    private double installments_principle_amount;
    private double installments_interest;
    private double installments_sum_pi_amount;
    private char installments_status;
    private int installments_customer_id;
    private int installments_account_id;
    private Date installments_dueDate;

    public installments(String installments_idloan, int installments_months, double installments_principle_amount, double installments_interest, double installments_sum_pi_amount, char installments_status, int installments_customer_id, int installments_account_id, Date installments_dueDate) {
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


}

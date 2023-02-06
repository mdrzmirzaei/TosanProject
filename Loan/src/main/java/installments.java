import java.util.Date;

public class installments {
    private String idloan;
    private Date dueDate;
    private double loan_principle_amount;
    private double loan_interest;
    private  double loan_sum_pi_amount;
    private char loan_status;
    private int  id_customer_loan;
    private int id_bank_account_loan;


    public installments(String idloan, Date dueDate, double loan_principle_amount, double loan_interest, double loan_sum_pi_amount, char loan_status, int id_ustomer_loan, int id_bank_account_loan) {
        this.idloan = idloan;
        this.dueDate = dueDate;
        this.loan_principle_amount = loan_principle_amount;
        this.loan_interest = loan_interest;
        this.loan_sum_pi_amount = loan_sum_pi_amount;
        this.loan_status = loan_status;
        this.id_customer_loan = id_ustomer_loan;
        this.id_bank_account_loan = id_bank_account_loan;
    }

    public String getIdloan() {
        return idloan;
    }

    public void setIdloan(String idloan) {
        this.idloan = idloan;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getLoan_principle_amount() {
        return loan_principle_amount;
    }

    public void setLoan_principle_amount(double loan_principle_amount) {
        this.loan_principle_amount = loan_principle_amount;
    }

    public double getLoan_interest() {
        return loan_interest;
    }

    public void setLoan_interest(double loan_interest) {
        this.loan_interest = loan_interest;
    }

    public double getLoan_sum_pi_amount() {
        return loan_sum_pi_amount;
    }

    public void setLoan_sum_pi_amount(double loan_sum_pi_amount) {
        this.loan_sum_pi_amount = loan_sum_pi_amount;
    }

    public char getLoan_status() {
        return loan_status;
    }

    public void setLoan_status(char loan_status) {
        this.loan_status = loan_status;
    }

    public int getId_customer_loan() {
        return id_customer_loan;
    }

    public void setId_customer_loan(int id_customer_loan) {
        this.id_customer_loan = id_customer_loan;
    }

    public int getId_bank_account_loan() {
        return id_bank_account_loan;
    }

    public void setId_bank_account_loan(int id_bank_account_loan) {
        this.id_bank_account_loan = id_bank_account_loan;
    }
}

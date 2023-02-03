public class loan {
    private int idloan;
    private long loan_principle_amount;
    private int loan_interest;
    private long loan_sum_pi_amount;
    private char loan_status;

    public loan(int idloan, long loan_principle_amount, int loan_interest, long loan_sum_pi_amount, char loan_status) {
        this.idloan = idloan;
        this.loan_principle_amount = loan_principle_amount;
        this.loan_interest = loan_interest;
        this.loan_sum_pi_amount = loan_sum_pi_amount;
        this.loan_status = loan_status;
    }

    public int getIdloan() {
        return idloan;
    }

    public void setIdloan(int idloan) {
        this.idloan = idloan;
    }

    public long getLoan_principle_amount() {
        return loan_principle_amount;
    }

    public void setLoan_principle_amount(long loan_principle_amount) {
        this.loan_principle_amount = loan_principle_amount;
    }

    public int getLoan_interest() {
        return loan_interest;
    }

    public void setLoan_interest(int loan_interest) {
        this.loan_interest = loan_interest;
    }

    public long getLoan_sum_pi_amount() {
        return loan_sum_pi_amount;
    }

    public void setLoan_sum_pi_amount(long loan_sum_pi_amount) {
        this.loan_sum_pi_amount = loan_sum_pi_amount;
    }

    public char getLoan_status() {
        return loan_status;
    }

    public void setLoan_status(char loan_status) {
        this.loan_status = loan_status;
    }
}

package DB;

public class tables {
    /**
     * Table of ustomers
     * {@link #idCustomer}
     * {@link #customer_name}
     * {@link #customer_family}
     * {@link #customer_address}
     */
    public  enum customer {
        idCustomer,customer_name,customer_family,customer_address;
    }

    /**
     * Table of Loans
     * {@link #idloan}
     * {@link #loan_customer_id}
     * {@link #loan_account_id}
     */
    public enum loan {
        idloan,loan_customer_id,loan_account_id;
    }

    /**
     * Table of Loans
     * {@link #USD}
     * {@link #RIL}
     */
    public enum currency {

        USD('$'),RIL( 'R');

        private final char currencySymbol;

        private  currency (char currencySymbol){
            this.currencySymbol=currencySymbol;

        }

    }

    public enum bank_account{idbank_account,bank_account_balance, kind_of_currency, bank_account_customer_id}


    public enum dateTime{
        fullyear,year,months,days,houreANDminutes;
    }

}

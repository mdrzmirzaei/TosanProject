package Entities;

import java.math.BigDecimal;


/**
 * Interface of Transactions
 * @author Mirza
 */

public interface TransactionsInterface {

   boolean deposit(String idcustomer, String bankAccountId, String amount);
   BigDecimal withDraw(String idCustomer);
   boolean accountToaccount(Customer customer);
}

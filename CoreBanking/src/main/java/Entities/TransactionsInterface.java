package Entities;

import java.math.BigDecimal;


/**
 * Interface of Transactions
 * @author Mirza
 */

public interface TransactionsInterface {

   boolean deposit();
   BigDecimal withDraw();
   boolean accountToaccount();
}

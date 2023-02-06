package Entities;

import DB.commandSQL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Interface of Transactions
 * @author Mirza
 */

public interface transactions {

  boolean deposit();
  BigDecimal withDraw();
}

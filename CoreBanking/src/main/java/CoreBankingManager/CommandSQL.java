package CoreBankingManager;


import DatabaseManager.InitDB;
import Entities.BankAccount;
import Entities.Customer;
import Entities.Installments;
import Entities.Transaction;

import javax.sql.rowset.CachedRowSet;
import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CommandSQL implements AutoCloseable {
    static Scanner scanner = new Scanner(System.in);
    public CachedRowSet cachedRowSet;
    BigDecimal financial_amount;
    Customer customer = new Customer();
    ArrayList<Customer> customerArray = new ArrayList<>();
    ArrayList<BankAccount> bankAccountArray = new ArrayList<>();
    ArrayList<Installments> installmentsArray = new ArrayList<>();


    public CommandSQL() {
        try {
            InitDB.ConnectOk();
            cachedRowSet = InitDB.initCachedRowset();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    public ArrayList<Customer> selectAllCustomer() {
        try {

            cachedRowSet.setCommand("select * from customer");
            cachedRowSet.execute();
            //to get information about table
            ResultSetMetaData RST = cachedRowSet.getMetaData();
            while (cachedRowSet.next()) {
                new Customer(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3), cachedRowSet.getString(4));
                customerArray.add(new Customer(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3), cachedRowSet.getString(4)));
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getCause());
        } finally {
            try {
                InitDB.releaseDB();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        return customerArray;

    }


    public Double getTotalInterest() {
        try {
            cachedRowSet.setCommand("select sum(installments.installments_interest) SumOfInterest from installments where installments.installments_status='T'");
            cachedRowSet.execute();
            if (cachedRowSet.next()) {
                return cachedRowSet.getDouble("SumOfInterest");
            } else return null;

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getCause());
            return null;
        }
    }

    public BigDecimal get_financial_ressource_cmd() {
        try {
            cachedRowSet.setCommand("select financial_amount from financial_ressource");
            cachedRowSet.execute();

            if (cachedRowSet.next()) {
                this.financial_amount = cachedRowSet.getBigDecimal("financial_amount");
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return this.financial_amount;
    }

    public BigDecimal set_financial_ressource_cmd(Double financialAmount, char oprator) {
        try {
            cachedRowSet.setCommand("update financial_ressource set financial_amount =" + get_financial_ressource_cmd() + " " + oprator + " ?");
            cachedRowSet.setString(1, financialAmount.toString());
            cachedRowSet.execute();

            cachedRowSet.setCommand("select financial_amount from financial_ressource");
            cachedRowSet.execute();

            if (cachedRowSet.next()) {
                this.financial_amount = cachedRowSet.getBigDecimal("financial_amount");
                System.out.println("new Amount of financial is " + financial_amount);
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            InitDB.releaseDB();
        }
        return this.financial_amount;
    }

    public Boolean setLoanRate(double Rate) {

        try {

            cachedRowSet.setCommand("select loanRate from loan");
            cachedRowSet.execute();
            if (cachedRowSet.next()) {
                Double ss = cachedRowSet.getDouble("loanRate");
                System.out.println("Rate of loan is : " + cachedRowSet.getDouble("loanRate"));
            }
            System.out.println("if do you want change it press 1(number) key and then enter new rate");

            ;
            if (scanner.nextInt() == 1) {
                this.update_cmd("loan", 1, "loanRate", String.valueOf(Rate));
            }

        } catch (SQLException e) {
            e.getMessage();
            e.getCause();
            return false;

        } finally {
            InitDB.releaseDB();
        }
        return true;
    }

    public ArrayList<Installments> select_installment_cmd(Customer Customer) {
        try {
            cachedRowSet.setCommand("select * from installments where installments_customer_id = ? and installments_status = 'F' order by installments_months");
            cachedRowSet.setInt(1, Customer.getIdCustomer());
            cachedRowSet.execute();
            ResultSetMetaData RST = cachedRowSet.getMetaData();
            //to get information about table
            while (cachedRowSet.next()) {
                installmentsArray.add(new Installments(cachedRowSet.getString(1), cachedRowSet.getInt(2), cachedRowSet.getDouble(3), cachedRowSet.getInt(4), cachedRowSet.getDouble(5), cachedRowSet.getString(6).charAt(0), cachedRowSet.getInt(7), cachedRowSet.getInt(8), cachedRowSet.getDate(9)));
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(cachedRowSet.getString(i) + "   ");
                }

                System.out.println("");
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getCause());
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        return installmentsArray;
    }

    public boolean select_financial_cmd(String tableName, String columnName, String condition, String value) {
        try {
            cachedRowSet.setCommand("select * from " + tableName + " where " + columnName + " " + condition + " ?");
            if (columnName.contains("id")) {
                cachedRowSet.setInt(1, Integer.valueOf(value));
            } else {
                cachedRowSet.setString(1, value);
            }
            cachedRowSet.execute();
            //to get information about table
            if (cachedRowSet.next()) {
                return true;
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getCause());
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        return false;

    }

    public ArrayList<BankAccount> select_bank_accounts(String columnName, String condition, String value) {
        try {

            cachedRowSet.setCommand("select * from bank_account where " + columnName + " " + condition + " ?");
            if (columnName.contains("id")) {
                cachedRowSet.setInt(1, Integer.valueOf(value));
            } else {
                cachedRowSet.setString(1, value);
            }
            cachedRowSet.execute();
            ResultSetMetaData RST = cachedRowSet.getMetaData();
            //to get information about table
            while (cachedRowSet.next()) {
                bankAccountArray.add(new BankAccount(cachedRowSet.getInt(1), cachedRowSet.getBigDecimal(2), cachedRowSet.getString(3).charAt(0), cachedRowSet.getInt(4)));
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(cachedRowSet.getString(i) + "   ");
                }
                System.out.println("__");
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getCause());
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        return bankAccountArray;


    }

    public BankAccount select_one_bank_account(ArrayList<BankAccount> bankAccountsArray, String bankAccountId) {
        BankAccount selected_bankAccount = new BankAccount();

        try {
            for (int i = 0; i < bankAccountArray.size(); i++) {
                if (bankAccountArray.get(i).getIdbank_account() == Integer.valueOf(bankAccountId)) {
                    selected_bankAccount = bankAccountsArray.get(i);
                }

            }
            return selected_bankAccount;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getCause();
            return null;
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }
    }


    /*   cachedRowSet.setCommand("select * from bank_account where " + columnName + " " + condition + " ?");
       if (columnName.contains("id")) {
           cachedRowSet.setInt(1, Integer.valueOf(value));
       } else {
           cachedRowSet.setString(1, value);
       }
       cachedRowSet.execute();
       ResultSetMetaData RST = cachedRowSet.getMetaData();
       //to get information about table
       while (cachedRowSet.next()) {
           bankAccountArray.add(new BankAccount(cachedRowSet.getInt(1), cachedRowSet.getBigDecimal(2), cachedRowSet.getString(3).charAt(0), cachedRowSet.getInt(4)));
           for (int i = 1; i < RST.getColumnCount(); i++) {
               System.out.print(cachedRowSet.getString(i) + "   ");
           }
           System.out.println("__");
       }
       if (bankAccountArray != null) {
           System.out.println("find Bank Account!!!");
           System.out.println("please select acccount :");
           int sel = scanner.nextInt();
           for (int i = 0; i < bankAccountArray.size(); i++) {
               if (bankAccountArray.get(i).getIdbank_acocunt() == sel) {
                   selected_bankAccount = bankAccountArray.get(i);
               }
           }
       }

   } catch (SQLException se) {
       System.out.println(se.getMessage());
       System.out.println(se.getCause());
   } finally {
       try {
           InitDB.releaseDB();
           cachedRowSet.close();
       } catch (Exception e) {
           System.out.println(e.getMessage());
           System.out.println(e.getCause());
       }
   }

   return selected_bankAccount;


}


     */
    public Customer select_customer_cmd(String columnName, String condition, String value) {
        try {

            cachedRowSet.setCommand("select * from customer where " + columnName + " " + condition + " ?");
            if (columnName.contains("id")) {
                cachedRowSet.setInt(1, Integer.valueOf(value));
            } else {
                cachedRowSet.setString(1, value);
            }
            cachedRowSet.execute();

            //to get information about table
            ResultSetMetaData RST = cachedRowSet.getMetaData();
            if (cachedRowSet.next()) {

                customer = new Customer(cachedRowSet.getInt("idCustomer"), cachedRowSet.getString("customer_name"), cachedRowSet.getString("customer_family"), cachedRowSet.getString("customer_address"));
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(cachedRowSet.getString(i) + "   ");
                }
            } else
                return null;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }
        return customer;
    }

    public Boolean insert_cmd(String tableName, HashMap<String, String> colVal) {
        StringBuilder inserintoDB = new StringBuilder("insert into corebanking." + tableName + " (");
        try {

            for (Map.Entry<String, String> cc : colVal.entrySet()) {
                inserintoDB.append(cc.getKey().toString() + ",");
            }

            //inserintoDB.deleteCharAt(inserintoDB.lastIndexOf(","));
            inserintoDB.delete(inserintoDB.length() - 1, inserintoDB.length());
            inserintoDB.append(")  values (");
            for (int i = 0; i < colVal.size(); i++) {
                inserintoDB.append("? , ");
            }
            inserintoDB.delete(inserintoDB.length() - 2, inserintoDB.length());
            inserintoDB.append(" )");


            cachedRowSet.setCommand(inserintoDB.toString());
            int i = 1;
            for (Map.Entry<String, String> cc : colVal.entrySet()) {

                cachedRowSet.setString(i, cc.getValue());
                i++;
            }

            cachedRowSet.execute();
            System.out.println("عملیات با موفقیت انجام شد ");
            return true;

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getStackTrace());
            System.out.println("عملیات موفق آمیز نبود");
            return false;
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }


    }

    public Boolean update_cmd(String tableName, int id_ForSelect, HashMap<String, String> ColVal) {

        try {
            String columnId = "";
            switch (tableName) {
                case "customer": {
                    columnId = "idCustomer";
                    break;
                }
                case "Loan": {
                    columnId = "idLoan";
                    break;
                }
                case "bank_account": {
                    columnId = "idbank_account";
                    break;
                }
                case "financial_ressource": {
                    columnId = "idfinancial_ressource_id";
                    break;
                }

                case "installments": {
                    columnId = "installments_status";
                    break;
                }
            }

            StringBuilder Query = new StringBuilder("UPDATE " + tableName + " set ");

            int i = 1;
            for (Map.Entry<String, String> entry : ColVal.entrySet()
            ) {
                Query.append(entry.getKey() + "= ? , ");
                i += 2;
            }

            Query.delete(Query.length() - 2, Query.length());
            //selectedRow = select_cmd(tableName, columnName, "=", String.valueOf(id));
            if (tableName == "installments") {
                Query.append(" where " + columnId + " = '" + (char) id_ForSelect + "'  order by installments_months limit 1");
            } else
                Query.append(" where " + columnId + " = " + String.valueOf(id_ForSelect));

            cachedRowSet.setCommand(Query.toString());
            i = 1;
            for (Map.Entry<String, String> entry : ColVal.entrySet()

            ) {
                cachedRowSet.setString(i, entry.getValue());
                i++;
            }

            cachedRowSet.execute();
            System.out.println("ویرایش با موفقیت انجام شد");
            return true;

        } catch (SQLException se) {
            se.getMessage();
            System.out.println("امکان انجام ویرایش وجود ندارد!!!!");
            return false;
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }


    }

    public Boolean update_cmd(String tableName, int id_ForSelect, String columnName, String Value) {

        try {
            String columnId = "";
            switch (tableName) {
                case "customer": {
                    columnId = "idCustomer";
                    break;
                }
                case "loan": {
                    columnId = "idLoan";
                    break;
                }
                case "bank_account": {
                    columnId = "idbank_account";
                    break;
                }
                case "financial_ressource": {
                    columnId = "idfinancial_ressource_id";
                    break;
                }
                case "installments": {
                    columnId = "installments_status";
                    break;
                }
            }

            StringBuilder Query = new StringBuilder("UPDATE " + tableName + " set " + columnName + " = ? ");


            //selectedRow = select_cmd(tableName, columnName, "=", String.valueOf(id));
            if (tableName == "installments") {
                Query.append(" where " + columnId + " = '" + (char) id_ForSelect + "'  order by installments_months limit 1");
            } else {
                Query.append(" where " + columnId + " = " + String.valueOf(id_ForSelect));

                cachedRowSet.setCommand(Query.toString());
                cachedRowSet.setString(1, Value);

            }

            cachedRowSet.execute();
            System.out.println("ویرایش با موفقیت انجام شد");
            return true;

        } catch (SQLException se) {
            se.getMessage();
            System.out.println("امکان انجام ویرایش وجود ندارد!!!!");
            return false;
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }


    }

    public Boolean delete_cmd(String tableName, HashMap<String, String> colval) {
        StringBuilder delFromDB = new StringBuilder("DELETE from corebanking." + tableName + " where ");
        String columnId = "";
        switch (tableName) {
            case "customer": {
                columnId = "idCustomer";
                break;
            }
            case "Loan": {
                columnId = "idLoan";
                break;
            }
            case "bank_account": {
                columnId = "idbank_account";
                break;
            }
        }
        try {
            for (Map.Entry<String, String> cc : colval.entrySet()) {
                delFromDB.append(cc.getKey().toString() + " = ? and ");
            }

            //inserintoDB.deleteCharAt(inserintoDB.lastIndexOf(","));
            delFromDB.delete(delFromDB.length() - 4, delFromDB.length());
            cachedRowSet.setCommand(delFromDB.toString());
            int i = 1;
            for (Map.Entry<String, String> cc : colval.entrySet()) {

                cachedRowSet.setString(i, cc.getValue());
                i++;
            }

            cachedRowSet.execute();
            System.out.println("اطلاعات  مورد نظر شما حذف گردید!");
            return true;

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getStackTrace());
            System.out.println("عملیات  حذف  موفق آمیز نبود");
            return false;
        } finally {
            try {
                InitDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

    }

    public ArrayList<Transaction> getTransacitons(char select /* (A or F) all or five records*/) {
        ArrayList<Transaction> transactionsList = new ArrayList<>();

        switch (select) {
            case 'F': {
                try {
                    cachedRowSet.setCommand("select * from transaction order by idtransaction desc limit 5");
                    cachedRowSet.execute();
                    while (cachedRowSet.next()) {
                        transactionsList.add(new Transaction(cachedRowSet.getInt(1), cachedRowSet.getDate(2), cachedRowSet.getTime(3), cachedRowSet.getBigDecimal(4), cachedRowSet.getString(5).charAt(0), cachedRowSet.getInt(6), cachedRowSet.getInt(7), cachedRowSet.getInt(8), cachedRowSet.getString(9)));
                    }
                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                    System.out.println(se.getCause());
                    return null;
                } finally {
                    InitDB.releaseDB();
                }
                break;
            }

            case 'A': {
                try {
                    cachedRowSet.setCommand("select * from transaction order by idtransaction desc");
                    cachedRowSet.execute();
                    transactionsList.add(new Transaction());
                    while (cachedRowSet.next()) {
                        transactionsList.add(new Transaction(cachedRowSet.getInt(1), cachedRowSet.getDate(2), cachedRowSet.getTime(3), cachedRowSet.getBigDecimal(4), cachedRowSet.getString(5).charAt(0), cachedRowSet.getInt(6), cachedRowSet.getInt(7), cachedRowSet.getInt(8), cachedRowSet.getString(9)));
                    }
                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                    System.out.println(se.getCause());
                    return null;
                } finally {
                    InitDB.releaseDB();

                }
                break;
            }

        }
        return transactionsList;
    }

    public HashMap<String,String> getTotalInterests() {
        HashMap<String,String> hm=new HashMap<>();

        try {
            cachedRowSet.setCommand("select installments_customer_id,sum(installments_interest) from installments where installments_status='T' group by installments_customer_id");
            cachedRowSet.execute();
            while (cachedRowSet.next()) {
                hm.put(cachedRowSet.getString(1),cachedRowSet.getString(2));
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getCause());
            return null;
        } finally {
            InitDB.releaseDB();
        }
        return hm;
}




@Override
public void close()throws Exception{
        try{
        InitDB.releaseDB();
        }catch(Exception e){
        System.out.println(e.getMessage());
        System.out.println(e.getCause());
        }finally{
        try{
        InitDB.releaseDB();
        cachedRowSet.close();
        }catch(Exception e){
        System.out.println(e.getMessage());
        System.out.println(e.getCause());
        }
        }
        }


        }



/*
    initDB.ConnectOk();
    CachedRowSet crs = null;
    crs = initDB.initCachedRowset();
            crs.setCommand("select * from customer where customer_name='samin'");
            crs.execute();
            while (crs.next()) {
        System.out.println(crs.getString(1) + "             " + crs.getString(2) + "_" + crs.getString(3));
        */




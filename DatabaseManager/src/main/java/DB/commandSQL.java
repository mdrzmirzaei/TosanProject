package DB;

import Entities.bank_account;
import Entities.customer;
import installments.installments;

import javax.sql.rowset.CachedRowSet;
import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class commandSQL implements AutoCloseable{
    customer customer = new customer();
    Scanner scanner = new Scanner(System.in);
    private CachedRowSet cachedRowSet = null;
    ArrayList<customer> customerArray = new ArrayList<>();
    ArrayList<bank_account> bank_accountArray = new ArrayList<>();
    ArrayList<installments> installmentsArray = new ArrayList<>();

    BigDecimal financial_amount;



    public commandSQL() {
        initDB.ConnectOk();
        cachedRowSet = initDB.initCachedRowset();
            }

    /*public ArrayList<customer> select_cmd(String tableName, String columnName, String condition, String value) {
    try {

        cachedRowSet.setCommand("select * from " + tableName + " where " + columnName + " " + condition + " ?");
        if (columnName.contains("id")) {
            cachedRowSet.setInt(1, Integer.valueOf(value));
        } else {
            cachedRowSet.setString(1, value);
        }
        cachedRowSet.execute();
        //to get information about table
        ResultSetMetaData RST = cachedRowSet.getMetaData();
        while (cachedRowSet.next()) {
            new customer(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3), cachedRowSet.getString(4));
            customerArray.add(new customer(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3), cachedRowSet.getString(4)));
            for (int i = 1; i < RST.getColumnCount(); i++) {
                System.out.print(cachedRowSet.getString(i) + "   ");
            }
            System.out.println("__");
        }

    } catch (SQLException se) {
        System.out.println(se.getMessage());
        System.out.println(se.getCause());
    }
    finally {
        try {
            initDB.releaseDB();
        }
        catch(Exception e ){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    return customerArray;

}

 */

    public ArrayList<installments> select_installment_cmd(customer Customer) {
        try {
            cachedRowSet.setCommand("select * from installments where installments_customer_id = ? and installments_status = 'N' order by installments_months");
            cachedRowSet.setInt(1, Customer.getIdCustomer());
            cachedRowSet.execute();
            ResultSetMetaData RST = cachedRowSet.getMetaData();
            //to get information about table
            while (cachedRowSet.next()) {
                installmentsArray.add(new installments(cachedRowSet.getString(1), cachedRowSet.getInt(2), cachedRowSet.getDouble(3), cachedRowSet.getInt(4), cachedRowSet.getDouble(5), cachedRowSet.getString(6).charAt(0), cachedRowSet.getInt(7), cachedRowSet.getInt(8), cachedRowSet.getDate(9)));
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
                initDB.releaseDB();
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
                initDB.releaseDB();
                cachedRowSet.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        return false;

    }

    public ArrayList<bank_account> select_bank_accounts(String columnName, String condition, String value) {
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
                bank_accountArray.add(new bank_account(cachedRowSet.getInt(1), cachedRowSet.getBigDecimal(2), cachedRowSet.getString(3).charAt(0), cachedRowSet.getInt(4)));
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
                initDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        return bank_accountArray;


    }

    public bank_account select_one_bank_account(String columnName, String condition, String value) {
        bank_account selected_bank_account = new bank_account();

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
                bank_accountArray.add(new bank_account(cachedRowSet.getInt(1), cachedRowSet.getBigDecimal(2), cachedRowSet.getString(3).charAt(0), cachedRowSet.getInt(4)));
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(cachedRowSet.getString(i) + "   ");
                }
                System.out.println("__");
            }
            if (bank_accountArray != null) {
                System.out.println("find Bank Account!!!");
                System.out.println("please select acccount :");
                int sel = scanner.nextInt();
                for (int i = 0; i < bank_accountArray.size(); i++) {
                    if (bank_accountArray.get(i).getIdbank_acocunt() == sel) {
                        selected_bank_account = bank_accountArray.get(i);
                    }
                }
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getCause());
        } finally {
            try {
                initDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
        }

        return selected_bank_account;


    }

    public customer select_customer_cmd(String columnName, String condition, String value) {
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

                customer = new customer(cachedRowSet.getInt("idCustomer"), cachedRowSet.getString("customer_name"), cachedRowSet.getString("customer_family"), cachedRowSet.getString("customer_address"));
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(cachedRowSet.getString(i) + "   ");
                }
            }
            System.out.println("__");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                initDB.releaseDB();
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
                initDB.releaseDB();
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
                initDB.releaseDB();
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
                initDB.releaseDB();
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
                initDB.releaseDB();
                cachedRowSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
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
        return financial_amount;
    }

    @Override
    public void close() throws Exception {
        try{
            initDB.releaseDB();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        finally {
try {
    initDB.releaseDB();
    cachedRowSet.close();
}catch (Exception e)
{
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




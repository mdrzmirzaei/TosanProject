package DB;

import Entities.customer;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class commandSQL {

    Scanner scanner = new Scanner(System.in);
    ArrayList<customer> CustomerArray = new ArrayList<customer>();
    private CachedRowSet cachedRowSet = null;


    public commandSQL() {
        initDB.ConnectOk();
        cachedRowSet = initDB.initCachedRowset();

    }


    public ArrayList<customer> select_cmd(String tableName) {
        try {
            cachedRowSet.setCommand("select * from " + tableName);
            cachedRowSet.execute();
            //to get information about table
            ResultSetMetaData RST = cachedRowSet.getMetaData();
            while (cachedRowSet.next()) {
                CustomerArray.add(new customer(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3), cachedRowSet.getString(4)));
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(cachedRowSet.getString(i) + "   ");

                }
                System.out.println("__");
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return CustomerArray;

    }

    public ArrayList<customer> select_cmd(String tableName, String columnName, String condition, String value) {
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
                CustomerArray.add(new customer(cachedRowSet.getInt(1), cachedRowSet.getString(2), cachedRowSet.getString(3), cachedRowSet.getString(4)));
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(cachedRowSet.getString(i) + "   ");
                }
                System.out.println("__");
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return CustomerArray;

    }

    public void insert_cmd(String tableName, HashMap<String, String> cv) {
        StringBuilder inserintoDB = new StringBuilder("insert into corebanking." + tableName + " (");
        try {

            for (Map.Entry<String, String> cc : cv.entrySet()) {
                inserintoDB.append(cc.getKey().toString() + ",");
            }

            //inserintoDB.deleteCharAt(inserintoDB.lastIndexOf(","));
            inserintoDB.delete(inserintoDB.length() - 1, inserintoDB.length());
            inserintoDB.append(")  values (?,?,?)");

            cachedRowSet.setCommand(inserintoDB.toString());
            int i = 1;
            for (Map.Entry<String, String> cc : cv.entrySet()) {

                cachedRowSet.setString(i, cc.getValue());
                i++;
            }

            cachedRowSet.execute();
            System.out.println("the operation was Done, Customer is added in database");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getStackTrace());

            System.out.println("عملیات موفق آمیز نبود");
        } finally {
            initDB.releaseDB();
        }

    }

    public void update_cmd(String tableName, int id, HashMap<String, String> ColVal) {
        ArrayList<customer> selectedRow = new ArrayList<>();

        try {
            String columnId = (tableName == "customer" ? "idCustomer" : "idLoan");
            System.out.println("please enter id : ");
            id = scanner.nextInt();
            StringBuilder Query = new StringBuilder("UPDATE " + tableName + " set ");

            int i = 1;
            for (Map.Entry<String, String> entry : ColVal.entrySet()
            ) {
                Query.append(entry.getKey() + "= ? , ");
                i += 2;
            }

            Query.delete(Query.length() - 2, Query.length());
            //selectedRow = select_cmd(tableName, columnName, "=", String.valueOf(id));
            Query.append(" where " + columnId + " = " + String.valueOf(id));

            cachedRowSet.setCommand(Query.toString());
            i = 1;
            for (Map.Entry<String, String> entry : ColVal.entrySet()

            ) {
                cachedRowSet.setString(i, entry.getValue());
                i++;
            }

            cachedRowSet.execute();
            System.out.println("ویرایش با موفقیت انجام شد");

        } catch (SQLException se) {
            se.getMessage();
            System.out.println("امکان انجام ویرایش وجود ندارد!!!!");
        }

    }


    public void delete_cmd(String tableName, HashMap<String, String> colval) {
        StringBuilder delFromDB = new StringBuilder("DELETE from corebanking." + tableName +" where ");
        String columnId = (tableName == "customer" ? "idCustomer" : "idLoan");
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
            System.out.println("اطلاعات مشتری مورد نظر شما حذف گردید!");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.getStackTrace());

            System.out.println("عملیات  حذف مشتری موفق آمیز نبود");
        } finally {
            initDB.releaseDB();
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


}

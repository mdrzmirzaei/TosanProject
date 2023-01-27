package DB;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class commandSQL {

    private CachedRowSet cachedRowSet = null;
    private PreparedStatement preparedStatement=null;


    public commandSQL() {
        initDB.ConnectOk();
        cachedRowSet = initDB.initCachedRowset();

    }


    public void select_cmd(String tableName) {
        try {
            preparedStatement=initDB.ConnectOk().prepareStatement("select * from " + tableName );
           ResultSet rs= preparedStatement.executeQuery();
            //to get information about table
            ResultSetMetaData RST = rs.getMetaData();

        while (rs.next()) {
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "   ");
                }
                System.out.println("__");
            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    public void select_cmd(String tableName, String columnName,String condition, String value) {
        try {
            preparedStatement = initDB.ConnectOk().prepareStatement("select * from " + tableName + " where " + columnName + " " + condition + " ?");
            if (columnName.contains("id")) {
                preparedStatement.setInt(1, Integer.valueOf(value));
            } else {
                preparedStatement.setString(1, value);
            }
            ResultSet rs= preparedStatement.executeQuery();
            //to get information about table
            ResultSetMetaData RST = rs.getMetaData();

            while (rs.next()) {
                for (int i = 1; i < RST.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "   ");
                }
                System.out.println("__");
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    public void insert_cmd(String table_name, HashMap<String, String> cv) {
        StringBuilder inserintoDB = new StringBuilder("insert into corebanking." + table_name + " (");
        try {

            for (Map.Entry<String, String> cc : cv.entrySet()) {
                inserintoDB.append(cc.getKey().toString() + ",");
            }

            //inserintoDB.deleteCharAt(inserintoDB.lastIndexOf(","));
            inserintoDB.delete(inserintoDB.length() - 1, inserintoDB.length());
            inserintoDB.append(")  values (?,?,?)");

            PreparedStatement preparedStatement = initDB.ConnectOk().prepareStatement(inserintoDB.toString());
            int i = 1;
            for (Map.Entry<String, String> cc : cv.entrySet()) {

                preparedStatement.setString(i, cc.getValue());
                i++;
            }

            preparedStatement.executeUpdate();
            System.out.println("the operation was Done, Customer is added in database");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.fillInStackTrace());

            System.out.println("عملیات موفق آمیز نبود");
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

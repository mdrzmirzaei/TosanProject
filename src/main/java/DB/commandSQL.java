package DB;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class commandSQL {

    public CachedRowSet cachedRowSet = null;


    public commandSQL() {
        initDB.ConnectOk();
        cachedRowSet = initDB.initCachedRowset();

    }


    public void select_cmd() {
        try {
            cachedRowSet.setCommand("select * from customer");
            cachedRowSet.execute();
            while (cachedRowSet.next()) {
                System.out.println(cachedRowSet.getString(2));
            }

            initDB.ConnectOk();
            CachedRowSet crs = null;
            crs = initDB.initCachedRowset();
            crs.setCommand("select * from customer where customer_name='samin'");
            crs.execute();
            while (crs.next()) {
                System.out.println(crs.getString(1) + "             " + crs.getString(2) + "_" + crs.getString(3));
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

            System.out.println(inserintoDB.length());
            System.out.println(inserintoDB.length()-1);
            inserintoDB.delete(inserintoDB.length()-1,inserintoDB.length());

            /*for (int i = 0; i < cv.size(); i++) {
                inserintoDB.append(cv.get("key").toString());

            }
            */
            inserintoDB.append(")  values (?,?,?)");
            System.out.println(inserintoDB.toString());


            PreparedStatement preparedStatement = initDB.ConnectOk().prepareStatement(inserintoDB.toString());
            int i = 1;
            for (Map.Entry<String, String> cc : cv.entrySet()) {

                preparedStatement.setString(i, cc.getValue());
                i++;
            }

            String s = preparedStatement.toString();
            preparedStatement.executeUpdate();


        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println(se.fillInStackTrace());

            System.out.println("عملیات موفق آمیز نبود");
        } finally {
            initDB.releaseDB();
        }

    }


}

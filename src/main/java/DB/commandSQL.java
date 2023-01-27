package DB;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

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

    public void insert_cmd(String table_name, String columns, String values) {
        try {
            cachedRowSet.setCommand("select * from customer");
            cachedRowSet.execute();
            cachedRowSet.getMetaData();
            //cachedRowSet.setCommand("insert into ? ( ? ) values ( ? )");
            cachedRowSet.moveToInsertRow();
            cachedRowSet.updateString(2, "zahra");
            cachedRowSet.updateString(3, "eslami");
            cachedRowSet.updateString(3, "Tehranpars");
            cachedRowSet.insertRow();
            cachedRowSet.acceptChanges();

            cachedRowSet.commit();
            System.out.println("اطلاعات مشتری با موفقیت ذخیره گردید");
            //cachedRowSet.moveToCurrentRow();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println("عملیات موفق آمیز نبود");
        }

    }


}

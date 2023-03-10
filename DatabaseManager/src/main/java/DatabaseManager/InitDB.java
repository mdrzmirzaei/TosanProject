package DatabaseManager;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public abstract class InitDB {
    private static CachedRowSet cachedRowSet = null;
    private static ConnectionPool cp = new ConnectionPool("jdbc:mysql://localhost:3306/corebanking", "cbadmin", "cb");

    public static Connection ConnectOk() {

        try {
            cp.createConnection();


        } catch (SQLException se) {
            System.out.println("امکان ایجاد بستری برای ارتباط وجود ندارد");
            System.out.println(se.getMessage());

        }
        return cp.getConnection();
    }


    public static CachedRowSet initCachedRowset() {
        try {
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            cachedRowSet = rowSetFactory.createCachedRowSet();
            cachedRowSet.setUrl(cp.getUrl());
            cachedRowSet.setUsername(cp.getUser());
            cachedRowSet.setPassword(cp.getPassword());
        } catch (SQLException se) {
            System.out.println("اطلاعات برای استفاده از کانکشن کافی نیست!!");
            System.out.println(se.getMessage());
        }
        return cachedRowSet;
    }

    public static void releaseDB() {
        try {
            cp.releaseConnection(InitDB.ConnectOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/*
    public static Statement initStatement() {
        try {
            Statement statement=
            cachedRowSet.setUrl(cp.getUrl());
            cachedRowSet.setUsername(cp.getUser());
            cachedRowSet.setPassword(cp.getPassword());
        } catch (SQLException se) {
            System.out.println("اطلاعات برای استفاده از کافی نیست!!");
            System.out.println(se.getMessage());
        }
        return cachedRowSet;
    }
*/


    public static CachedRowSet initCommand(String cmd) {
        try {

            cachedRowSet.setCommand(cmd);
            cachedRowSet.execute();
            return cachedRowSet;

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return cachedRowSet;
    }


}

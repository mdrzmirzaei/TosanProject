package DB;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public abstract class initDB {
    private static CachedRowSet cachedRowSet = null;
    private static connectionPool cp = new connectionPool("jdbc:mysql://localhost:3306/corebanking", "admin_cb", "cb");
    private static PreparedStatement preparedStatement=null;

    public static Connection ConnectOk() {

        try {
            cp.createConneciton();


        } catch (SQLException se) {
            System.out.println("امکان ایجاد استخر ارتباط وجود ندارد");
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
            System.out.println("اطلاعات برای استفاده از کافی نیست!!");
            System.out.println(se.getMessage());
        }
        return cachedRowSet;
    }



/*
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

*/

}

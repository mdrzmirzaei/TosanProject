package DB;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.SQLException;

public class initDB {
    private connectionPool cp = null;
    public CachedRowSet crowSet = null;


    public Connection ConnectOk() {

        try {
            cp = new connectionPool("jdbc:mysql://localhost:3306/corebanking", "admin_cb", "cb");
            cp.createConneciton();

            return cp.getConnection();

        } catch (SQLException se) {
            System.out.println("امکان ایجاد استخر ارتباط وجود ندارد");
            System.out.println(se.getMessage());

        }
        return cp.getConnection();
    }


    public CachedRowSet initCachedRowset() {
        try {
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            this.crowSet = rowSetFactory.createCachedRowSet();
            crowSet.setUrl(cp.getUrl());
            crowSet.setUsername(cp.getUser());
            crowSet.setPassword(cp.getPassword());
            return crowSet;
        } catch (SQLException se) {
            System.out.println("اطلاعات برای استفاده از کافی نیست!!");
            System.out.println(se.getMessage());
        }
        return crowSet;
    }


    public CachedRowSet initCommand(String cmd) {
        try {

            this.crowSet.setCommand(cmd);
            this.crowSet.execute();

            return this.crowSet;

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }

        return this.crowSet;
    }


}

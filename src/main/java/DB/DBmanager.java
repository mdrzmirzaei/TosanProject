package DB;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class DBmanager {
    private final String URL = "jdbc:mysql://localhost:3306/corebanking";

    public void DB_PoolConnection() {

        try {
            RowSetFactory factory = RowSetProvider.newFactory();


            CachedRowSet cachedRowSet = factory.createCachedRowSet();
cachedRowSet.setUrl(URL);
cachedRowSet.setUsername("admin_cb");
cachedRowSet.setPassword("cb");
cachedRowSet.setCommand("select * from customer");
cachedRowSet.execute();

while (cachedRowSet.next()){
    System.out.println(cachedRowSet.getInt(1)+ cachedRowSet.getString(2));
}




        } catch (Exception se) {
            System.out.println(se.getMessage());
        }
    }


}

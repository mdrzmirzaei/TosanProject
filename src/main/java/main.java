import DB.connectionPool;
import DB.initDB;


import java.sql.Connection;

public class main {
    public static void main(String[] args) {
        System.out.println("Inam alakie ? ");
        try {

initDB idb=new initDB();
Connection c= idb.ConnectOk();
idb.initCachedRowset();
idb.initCommand("select * from customer");
if (idb.crowSet!=null) {
    while (idb.crowSet.next())
    {
        System.out.println(idb.crowSet.getString("customer_name")+"   " + idb.crowSet.getString(3));
    }

}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

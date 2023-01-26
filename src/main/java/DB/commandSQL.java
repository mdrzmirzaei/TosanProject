package DB;

import DB.initDB;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class commandSQL {
public CachedRowSet cachedRowSet=null;


public commandSQL (){
    initDB.ConnectOk();
    cachedRowSet=initDB.initCachedRowset();

}


public void select_cmd (){
    try {
        cachedRowSet.setCommand("select * from customer");
        cachedRowSet.execute();
        while( cachedRowSet.next())
        {
            System.out.println(cachedRowSet.getString(2));
        }

        initDB.ConnectOk();
        CachedRowSet crs=null;
        crs=initDB.initCachedRowset();
        crs.setCommand("select * from customer where customer_name='samin'");
        crs.execute();
        while (crs.next()){
            System.out.println(crs.getString(1)+"             "+crs.getString(2)+"_"+crs.getString(3));
        }
    }
    catch (SQLException se){
        System.out.println(se.getMessage());
    }
}



}

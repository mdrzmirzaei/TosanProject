package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class connectionPool implements  AutoCloseable{
    private final int max_conneciton = 10;
    private List<Connection> availabeConnecitons = new ArrayList<Connection>();
    private List<Connection> usedConnecitons = new ArrayList<Connection>();
    private String url;
    private String user;
    private String password;

    /**
     * Initilize all connections
     * @author Mirza
     */

    public connectionPool(String url, String user, String password)  {
        try {
        this.url = url;
        this.user = user;
        this.password = password;

        for (int count = 0; count < max_conneciton; count++) {
            availabeConnecitons.add(this.createConnection());
        }}
        catch (SQLException se){
            System.out.println(se.getMessage());
            }

    }

    /**
     * Private function,
     * used by the Pool to create new connection internally
     **/

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.password);
    }


    /**
     * Public function, used by us to get connection from Pool
     **/
    public Connection getConnection() {
        if (availabeConnecitons.size() == 0) {
            System.out.println("all Coonnection are busy!! , please try  again later");
            return null;
        } else {
            Connection con = availabeConnecitons.remove(availabeConnecitons.size() - 1);
            usedConnecitons.add(con);
            return con;
        }
    }

    /**
     * Public function, to return connection back to the Pool
     **/
    public boolean releaseConnection(Connection con) {
        if (con != null) {
          try {
              usedConnecitons.remove(con);
              availabeConnecitons.add(con);
          }
          catch (Exception e){
              System.out.println(e.getMessage());
          }
          finally {
              try {
                  usedConnecitons.remove(con);
                  availabeConnecitons.add(con);
              }
              catch (Exception e){
                  System.out.println(e.getMessage());
              }
          }
        }
        return true;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public void close()  {
        try {
            usedConnecitons.clear();
            availabeConnecitons.clear();
            createConnection().close();
        }
        catch (Exception e)
        {
            e.getMessage();
            System.out.println("i can not close connections");
        }
    }
}


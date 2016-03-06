package net.chuiev.selcommittee.repository;

import sun.jdbc.odbc.ee.ConnectionPool;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Алексей on 3/5/2016.
 */
public class ConnectionPoolFactory {
    //private static DataSource dataSource;
    static {

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        String driverString ="org.apache.derby.jdbc.ClientDriver";
        String connectionURL = "jdbc:derby://localhost:1527/myDB;create=true;";


        Class c = Class.forName(driverString);
        Driver driver = (Driver)c.newInstance();
        DriverManager.registerDriver(driver);
        //ConnectionPool pool = new ConnectionPool("localPool", 5, 10, 30, 180, connectionURL, "b_lightyear", "BeyondInfinity");
        return null;
    }

}

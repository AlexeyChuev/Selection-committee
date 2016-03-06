package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.exception.BrokenConnectionException;
import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Алексей on 3/5/2016.
 */
public class ConnectionCreator {
    private ConnectionCreator(){};

    private static Connection connection;
    private static DataSource dataSource;

    private static DataSource getDS(String database, String user, String password) throws SQLException {
        ClientDataSource ds = new ClientDataSource();
        // DatabaseName can include Derby URL Attributes
        ds.setDatabaseName(database);
        if (user != null)
            ds.setUser(user);
        if (password != null)
            ds.setPassword(password);
        // The host on which Network Server is running
        ds.setServerName("localhost");
        // port on which Network Server is listening
        ds.setPortNumber(1527);
        return ds;
    }

    public static Connection getConnection() {
        if(connection==null)
        {
            try {
                dataSource = getDS("myDB;create=true", null, null);
                connection = dataSource.getConnection("admin", "admin");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BrokenConnectionException();
            }
        }
        return connection;
    }
}

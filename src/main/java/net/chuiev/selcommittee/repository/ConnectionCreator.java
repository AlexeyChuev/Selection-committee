package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.exception.BrokenConnectionException;
import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Алексей on 3/5/2016.
 */
public final class ConnectionCreator {

    private DataSource dataSource;

    private DataSource getDS(String database, String user, String password) throws SQLException {
        ClientDataSource ds = new ClientDataSource();
        ds.setDatabaseName(database);
        if (user != null) {
            ds.setUser(user);
        }
        if (password != null) {
            ds.setPassword(password);
        }
        // The host on which Network Server is running
        ds.setServerName("localhost");
        // port on which Network Server is listening
        ds.setPortNumber(1527);
        return ds;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            dataSource = getDS("myDB;create=true", null, null);
            connection = dataSource.getConnection("admin", "admin");
        } catch (SQLException e) {
            throw new BrokenConnectionException(e);
        }
        return connection;
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
            }
        }
    }

    protected void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
            }
        }
    }
}

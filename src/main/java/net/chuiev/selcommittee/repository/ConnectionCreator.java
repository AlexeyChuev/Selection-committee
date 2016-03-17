package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.exception.BrokenConnectionException;
import org.apache.derby.jdbc.ClientDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Class that produces connection to DB
 *
 * @author Oleksii Chuiev
 */
public final class ConnectionCreator {
    private static final Logger LOG = Logger.getLogger(ConnectionCreator.class);

    private DataSource dataSource;

    /**
     * Returns DataSource for connection creating.
     *
     * @param database Name of my DB (myDB) + create=true.
     * @param user     User name
     * @param password User password
     * @return DataSource.
     * @throws SQLException
     */
    private DataSource getDS(String database, String user, String password) throws SQLException {
        LOG.debug("start to create DataSource");
        ClientDataSource ds = new ClientDataSource();
        ds.setDatabaseName(database);
        if (user != null) {
            ds.setUser(user);
            LOG.trace("user set " + user);
        }
        if (password != null) {
            ds.setPassword(password);
            LOG.trace("password set " + password);
        }
        // The host on which Network Server is running
        ds.setServerName("localhost");
        // port on which Network Server is listening
        ds.setPortNumber(1527);

        LOG.debug("DataSource creating");
        return ds;
    }

    /**
     * Returns Connection
     */
    public Connection getConnection() {
        LOG.debug("start to create Connection");
        Connection connection = null;
        try {
            dataSource = getDS("myDB;create=true", null, null);
            connection = dataSource.getConnection("admin", "admin");
            LOG.trace("connection created " + connection);
        } catch (SQLException e) {
            LOG.error("error while working with DB" + e);
            throw new BrokenConnectionException(e);
        }
        return connection;
    }

    /**
     * Safety close ResultSet
     */
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error("error while working with DB" + e);
            }
        }
    }

    /**
     * Safety close Statement
     */
    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOG.error("error while working with DB" + e);
            }
        }
    }

    /**
     * Safety rollback Connection
     */
    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                LOG.error("error while working with DB" + e);
            }
        }
    }

    /**
     * Safety close Connection
     */
    protected void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                LOG.error("error while working with DB" + e);
            }
        }
    }
}

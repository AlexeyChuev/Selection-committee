package net.chuiev;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Алексей on 03.03.2016.
 */
public class Test1 {
    public static void main(String[] args) throws SQLException {


        String driver ="org.apache.derby.jdbc.ClientDriver";
        String connectionURL = "jdbc:derby://localhost:1527/myDB;create=true";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (java.sql.Connection conn = DriverManager.getConnection(connectionURL, "admin", "admin")) {// body of code to go here


            Statement st = conn.createStatement();
          // st.execute("create table users2 (first_name varchar(30) not null, last_name varchar (30) not null, us_id integer not null)");
           //st.execute("insert into subject (name) values ('Math')");
           //st.execute("insert into subject (name) values ('Geography')");
           //st.execute("insert into subject (name) values ('English')");




            ResultSet rs = st.executeQuery("SELECT * FROM subject");
            while (rs.next()) {
                int first = rs.getInt(1);
                String last = rs.getString(2);

                System.out.println(first + " " + last + " ");

            }


        } catch (Throwable er) {
            er.printStackTrace();
        }

    }
}

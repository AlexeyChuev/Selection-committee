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
        String connectionURL = "jdbc:derby://localhost:1527/C:/apache/myDB;create=true";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (java.sql.Connection conn = DriverManager.getConnection(connectionURL)) {// body of code to go here


            Statement st = conn.createStatement();
//           st.execute("create table users2 (first_name varchar(30) not null, last_name varchar (30) not null, us_id integer not null)");
//            st.execute("insert into users2 values ('Tom', 'Anderson',1)");
//            st.execute("insert into users2 values ('Gab', 'Robsky',2)");
//            st.execute("insert into users2 values ('Игорь', 'Николаев',3)");




            ResultSet rs = st.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                String first = rs.getString(1);
                String last = rs.getString(2);
                int id = rs.getInt(3);

                System.out.println(id + " " + first + " " + last + " ");

            }


        } catch (Throwable er) {
            er.printStackTrace();
        }

    }
}

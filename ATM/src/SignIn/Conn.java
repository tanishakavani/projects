package SignIn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

    public Connection con;

	public Conn() throws Exception {

        String dbURL = "jdbc:mysql://localhost:3306/bank";
        String dbUserID = "root";
        String dbPass = "";
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
        con = DriverManager.getConnection(dbURL, dbUserID, dbPass);
    }

}

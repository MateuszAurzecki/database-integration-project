package pl.b2b.aurzecki.pharmacy.creator;

import org.apache.log4j.Logger;
import pl.b2b.aurzecki.pharmacy.domain.H2Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2Creator {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/pharmacy";

    public static final String USER = "sa";
    public static final String PASS = "";

    private Connection conn = null;
    private Statement stmt = null;



    public List<H2Database> getH2Database(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<H2Database> list = new ArrayList<>();
        String sqlWithResources = "SELECT * FROM medicine";
        try (Connection con1 = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
             Statement stmt = con1.createStatement();
             ResultSet rs = stmt.executeQuery(sqlWithResources)) {

        } catch (SQLException ex) {

        }

        try {
            conn = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM medicine";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long id = Long.valueOf(rs.getInt("identyfikator"));
                String name = rs.getString("nazwa_leku");
                Long ministerstwo = Long.valueOf(rs.getString("min"));
                H2Database data = new H2Database();
                data.setIdentyfikator(id);
                data.setNazwaLeku(name);
                data.setMin(ministerstwo);
                list.add(data);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }


    private void closeConnection() {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

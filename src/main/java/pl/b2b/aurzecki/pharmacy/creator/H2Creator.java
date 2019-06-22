package pl.b2b.aurzecki.pharmacy.creator;

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


    public List<H2Database> getH2Database(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<H2Database> result = new ArrayList<>();
        String sql = "SELECT * FROM medicine";

        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = Long.valueOf(rs.getInt("identyfikator"));
                String name = rs.getString("nazwa_leku");
                Long ministerstwo = Long.valueOf(rs.getString("min"));
                H2Database data = new H2Database();
                data.setIdentyfikator(id);
                data.setNazwaLeku(name);
                data.setMin(ministerstwo);
                result.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}

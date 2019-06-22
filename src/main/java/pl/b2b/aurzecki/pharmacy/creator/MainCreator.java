package pl.b2b.aurzecki.pharmacy.creator;

import pl.b2b.aurzecki.pharmacy.domain.MainDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainCreator {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/medicine";

    private static final String USER = "sa";
    private static final String PASS = "";


    public List<MainDatabase> getMainDatabase() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<MainDatabase> result = new ArrayList<>();
        String sql = "SELECT * FROM pharmacy";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = (long) rs.getInt("id");
                String name = rs.getString("name");
                Long govNumber = Long.valueOf(rs.getString("government_number"));
                MainDatabase data = new MainDatabase();
                data.setId(id);
                data.setName(name);
                data.setGovernmentNumber(govNumber);
                result.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

package pl.b2b.aurzecki.pharmacy.creator;

import org.apache.log4j.Logger;
import pl.b2b.aurzecki.pharmacy.domain.H2Database;
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

    private Connection conn = null;
    private Statement stmt = null;

    private static final Logger LOG = Logger.getLogger(H2Database.class);


    public List<MainDatabase> getMainDatabase() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<MainDatabase> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM pharmacy";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long id = (long) rs.getInt("id");
                String name = rs.getString("name");
                Long govNumber = Long.valueOf(rs.getString("government_number"));
                MainDatabase data = new MainDatabase();
                data.setId(id);
                data.setName(name);
                data.setGovernmentNumber(govNumber);
                list.add(data);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        LOG.info("Found " + list.size() + " matches");
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
        LOG.debug("Connection closed");
    }
}

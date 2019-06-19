package pl.b2b.aurzecki.pharmacy.creator;

import org.apache.log4j.Logger;
import pl.b2b.aurzecki.pharmacy.domain.SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlCreator {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    public static final String USER = "root";
    public static final String PASS = "root";

    private Connection conn = null;
    private Statement stmt = null;

    private static final Logger LOG = Logger.getLogger(SqlDatabase.class);


    public List<SqlDatabase> getSqlDatabase(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<SqlDatabase> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM medicine";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long ident = (long) rs.getInt("ident");
                String name = rs.getString("nazwa");
                Long ministerstwo = Long.valueOf(rs.getString("ministerstwo"));
                SqlDatabase data = new SqlDatabase();
                data.setIdent(ident);
                data.setNazwa(name);
                data.setMinisterstwo(ministerstwo);
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
        LOG.debug("Connection closed");
    }
}

package pl.b2b.aurzecki.pharmacy.service;

import pl.b2b.aurzecki.pharmacy.exceptions.ExceptionsHandler;
import pl.b2b.aurzecki.pharmacy.model.H2Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2Creator {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String sql = "SELECT * FROM medicine";
    private ExceptionsHandler exceptionsHandler = new ExceptionsHandler();

    public List<H2Database> getH2Database(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<H2Database> list = new ArrayList<>();

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
                list.add(data);
            }
        } catch (SQLException e) {
            exceptionsHandler.driverError();
        }
        return list;
    }

    public List<String> h2TableColumnNames(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        List<String> result = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                result.add(resultSetMetaData.getColumnName(i + 1));
            }
        }
        return result;
    }

}

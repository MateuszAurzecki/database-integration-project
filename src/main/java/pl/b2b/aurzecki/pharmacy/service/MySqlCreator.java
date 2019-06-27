package pl.b2b.aurzecki.pharmacy.service;

import pl.b2b.aurzecki.pharmacy.exceptions.ExceptionsHandler;
import pl.b2b.aurzecki.pharmacy.model.SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlCreator {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";


    String sql = "SELECT * FROM medicine";

    private ExceptionsHandler exceptionsHandler = new ExceptionsHandler();

    public List<SqlDatabase> getSqlDatabase(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {

        Class.forName(JDBC_DRIVER);
        List<SqlDatabase> result = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long ident = (long) rs.getInt("ident");
                String name = rs.getString("nazwa");
                Long ministerstwo = Long.valueOf(rs.getString("ministerstwo"));
                SqlDatabase data = new SqlDatabase();
                data.setIdent(ident);
                data.setNazwa(name);
                data.setMinisterstwo(ministerstwo);
                result.add(data);
            }
        } catch (
                SQLException e) {
            exceptionsHandler.connectionError();
        }
        return result;
    }

    public List<String> MySqlTableColumnNames(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
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
        } catch (SQLException e) {
            exceptionsHandler.connectionError();
        }
        return result;
    }
}

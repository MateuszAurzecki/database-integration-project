package pl.b2b.aurzecki.pharmacy.service;

import pl.b2b.aurzecki.pharmacy.exceptions.ExceptionsHandler;
import pl.b2b.aurzecki.pharmacy.model.MySqlDatabaseModel;

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


    //function mapping database table to MySqlDatabaseModel objects and return it as a list of objects
    public List<MySqlDatabaseModel> getSqlDatabase(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {

        Class.forName(JDBC_DRIVER);
        List<MySqlDatabaseModel> result = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long ident = (long) rs.getInt("ident");
                String name = rs.getString("nazwa");
                Long ministerstwo = Long.valueOf(rs.getString("ministerstwo"));
                MySqlDatabaseModel data = new MySqlDatabaseModel();
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

    //function returns list of string with names of columns in table
    public List<String> mySqlTableColumnNames(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
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

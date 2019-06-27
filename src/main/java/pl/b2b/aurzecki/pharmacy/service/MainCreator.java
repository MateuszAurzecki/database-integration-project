package pl.b2b.aurzecki.pharmacy.service;

import pl.b2b.aurzecki.pharmacy.model.MainDatabaseModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainCreator {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/medicine";

    private static final String USER = "sa";
    private static final String PASS = "";
    String sql = "SELECT * FROM pharmacy";


    //function mapping main database table to MainDatabaseModel objects and return it as a list of objects
    public List<MainDatabaseModel> getMainDatabase() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<MainDatabaseModel> result = new ArrayList<>();


        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = (long) rs.getInt("id");
                String name = rs.getString("name");
                Long govNumber = Long.valueOf(rs.getString("government_number"));
                MainDatabaseModel data = new MainDatabaseModel();
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

    //function returns list of string with names of columns in table
    public List<String> mainTableColumnNames() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        List<String> result = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                result.add(resultSetMetaData.getColumnName(i+1));
            }
        }
        return result;
    }

}

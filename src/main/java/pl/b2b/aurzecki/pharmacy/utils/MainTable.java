package pl.b2b.aurzecki.pharmacy.utils;

import pl.b2b.aurzecki.pharmacy.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTable {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/medicine";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static final String dropTable = "DROP TABLE pharmacy if exists";
    private static final String createTableId = "CREATE TABLE pharmacy(id bigint primary key not null, name varchar, government_number int)";
    private static final String createTableGov = "CREATE TABLE pharmacy(id bigint auto_increment, name varchar, government_number int unique)";

    Connection conn = null;
    private Statement stmt = null;


    public void CreateMainTable() throws SQLException {

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            try (PreparedStatement preparedStatement = conn.prepareStatement(dropTable)) {
                preparedStatement.execute();
            }
            if (App.number == 1) {
                try (PreparedStatement createById = conn.prepareStatement(createTableId)) {
                    createById.execute();
                }
            } else if (App.number == 2) {
                try (PreparedStatement createByGov = conn.prepareStatement(createTableGov)) {
                    createByGov.execute();
                }
            }
        } finally {
            closeConnection();
        }
    }


    public void deleteAllFromMainDatabase() throws SQLException {

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        try (
                PreparedStatement preparedStatement = conn.prepareStatement("delete from pharmacy")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
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

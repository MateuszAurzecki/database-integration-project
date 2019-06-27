package pl.b2b.aurzecki.pharmacy.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainTable {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/medicine";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static final String dropTable = "DROP TABLE pharmacy if exists";



    public void deleteAllFromMainDatabase() {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = conn.prepareStatement("delete from pharmacy")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

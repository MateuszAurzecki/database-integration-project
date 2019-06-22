package pl.b2b.aurzecki.pharmacy.utils;

import pl.b2b.aurzecki.pharmacy.App;
import pl.b2b.aurzecki.pharmacy.creator.ExcelCreator;
import pl.b2b.aurzecki.pharmacy.creator.H2Creator;
import pl.b2b.aurzecki.pharmacy.creator.MySqlCreator;
import pl.b2b.aurzecki.pharmacy.domain.ExcelDatabase;
import pl.b2b.aurzecki.pharmacy.domain.H2Database;
import pl.b2b.aurzecki.pharmacy.domain.SqlDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class AddDatabaseToMedicine {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/medicine";

    private static final String USER = "sa";
    private static final String PASS = "";
    private final String queryCheck = "SELECT * from pharmacy WHERE id = ?";
    private final String queryId = "INSERT INTO pharmacy(id, name, government_number) values (?,?,?)";
    private final String queryGov = "INSERT INTO pharmacy(name, government_number) values (?,?)";


    public void addExcelToMedicine() throws IOException {

        ExcelCreator excelCreator = new ExcelCreator();
        List<ExcelDatabase> excelList = excelCreator.getDatabase("src/main/resources/ExcelDatabase.xlsx");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Iterator<ExcelDatabase> it = excelList.iterator();

            while (it.hasNext()) {
                ExcelDatabase e = it.next();
                if (App.number == 1) {

                    try (PreparedStatement ps = conn.prepareStatement(queryId);
                         PreparedStatement check = conn.prepareStatement(queryCheck)) {

                        check.setLong(1, e.getLp());
                        ResultSet recordExist = check.executeQuery();
                        if (recordExist.next()) {
                            continue;
                        }
                        ps.setLong(1, e.getLp());
                        ps.setString(2, e.getNazwa());
                        ps.setLong(3, e.getId_w_ministerstwie());
                        ps.addBatch();
                        ps.execute();
                        recordExist.close();
                    }
                } else if (App.number == 2) {
                    try (
                            PreparedStatement ps = conn.prepareStatement(queryGov)) {

                        ps.setString(1, e.getNazwa());
                        ps.setLong(2, e.getId_w_ministerstwie());
                        ps.addBatch();
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addH2ToMedicine() throws ClassNotFoundException {
        H2Creator h2Creator = new H2Creator();
        List<H2Database> h2List = h2Creator.getH2Database(H2Creator.DB_URL, H2Creator.USER, H2Creator.PASS);

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Iterator<H2Database> it = h2List.iterator();

            while (it.hasNext()) {
                H2Database e = it.next();
                if (App.number == 1) {
                    try (
                            PreparedStatement preparedStatement = conn.prepareStatement(queryId);
                            PreparedStatement checkIfRecordExist = conn.prepareStatement(queryCheck)
                    ) {

                        checkIfRecordExist.setLong(1, e.getIdentyfikator());
                        ResultSet recordExist = checkIfRecordExist.executeQuery();
                        if (recordExist.next()) {
                            continue;
                        }
                        preparedStatement.setLong(1, e.getIdentyfikator());
                        preparedStatement.setString(2, e.getNazwaLeku());
                        preparedStatement.setLong(3, e.getMin());
                        preparedStatement.addBatch();
                        preparedStatement.execute();
                        recordExist.close();
                    }
                } else if (App.number == 2) {
                    try (
                            PreparedStatement preparedStatement = conn.prepareStatement(queryGov)) {

                        preparedStatement.setString(1, e.getNazwaLeku());
                        preparedStatement.setLong(2, e.getMin());
                        preparedStatement.addBatch();
                        preparedStatement.execute();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMySqlToMedicine() throws ClassNotFoundException {

        MySqlCreator mySqlCreator = new MySqlCreator();
        List<SqlDatabase> mySqlList = mySqlCreator.getSqlDatabase(MySqlCreator.DB_URL, MySqlCreator.USER, MySqlCreator.PASS);

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            Iterator<SqlDatabase> it = mySqlList.iterator();


            while (it.hasNext()) {
                SqlDatabase e = it.next();
                if (App.number == 1) {
                    try (
                            PreparedStatement preparedStatement = conn.prepareStatement(queryId);
                            PreparedStatement checkIfRecordExist = conn.prepareStatement(queryCheck)) {

                        checkIfRecordExist.setLong(1, e.getIdent());
                        ResultSet recordExist = checkIfRecordExist.executeQuery();
                        if (recordExist.next()) {
                            continue;
                        }
                        preparedStatement.setLong(1, e.getIdent());
                        preparedStatement.setString(2, e.getNazwa());
                        preparedStatement.setLong(3, e.getMinisterstwo());
                        preparedStatement.addBatch();
                        preparedStatement.execute();
                        recordExist.close();
                    }
                } else if (App.number == 2) {
                    try (
                            PreparedStatement ps = conn.prepareStatement(queryGov)) {

                        ps.setString(1, e.getNazwa());
                        ps.setLong(2, e.getMinisterstwo());
                        ps.addBatch();
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

package pl.b2b.aurzecki.pharmacy.service;

import pl.b2b.aurzecki.pharmacy.exceptions.ExceptionsHandler;
import pl.b2b.aurzecki.pharmacy.model.ExcelDatabase;
import pl.b2b.aurzecki.pharmacy.model.H2Database;
import pl.b2b.aurzecki.pharmacy.model.SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddDatabaseToMedicine {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/medicine";
    private static final String USER = "sa";
    private static final String PASS = "";

    private final String queryCheck = "SELECT * from pharmacy WHERE id = ?";
    private final String queryId = "INSERT INTO pharmacy(id, name, government_number) values (?,?,?)";

    private MySqlCreator mySqlCreator = new MySqlCreator();
    private H2Creator h2Creator = new H2Creator();
    private ExceptionsHandler exceptionsHandler = new ExceptionsHandler();


    public void addExcelToMedicine(final List<String> columnMappingList, final String path) {

        ExcelCreator excelCreator = new ExcelCreator();
        List<ExcelDatabase> excelList = excelCreator.getDatabase(path);

        int column1 = 0;
        int column2 = 0;
        int column3 = 0;

        for (int i = 0; i < columnMappingList.size(); i++) {
            if (columnMappingList.get(i).contains("lp")) {
                column1 = i + 1;
            } else if (columnMappingList.get(i).contains("nazwa")) {
                column2 = i + 1;
            } else if (columnMappingList.get(i).contains("id_w_ministerstwie")) {
                column3 = i + 1;
            }
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Iterator<ExcelDatabase> it = excelList.iterator();

            while (it.hasNext()) {
                ExcelDatabase e = it.next();

                try (PreparedStatement ps = conn.prepareStatement(queryId);
                     PreparedStatement check = conn.prepareStatement(queryCheck)) {

                    check.setLong(1, e.getLp());
                    ResultSet recordExist = check.executeQuery();
                    if (recordExist.next()) {
                        continue;
                    }
                    ps.setLong(column1, e.getLp());
                    ps.setString(column2, e.getNazwa());
                    ps.setLong(column3, e.getId_w_ministerstwie());
                    ps.addBatch();
                    ps.execute();
                    recordExist.close();
                }

            }
        } catch (SQLException e) {
            exceptionsHandler.columnMatchingError();

        }
    }


    //function insert MySql database to main database
    public void addMySqlToMedicine(final List<String> columnMappingList, final String dbUrl, final String dbPass, final String dbLogin) {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<SqlDatabase> sqlList = new ArrayList<>();

        int column1 = 0;
        int column2 = 0;
        int column3 = 0;

        for (int i = 0; i < columnMappingList.size(); i++) {
            if (columnMappingList.get(i).contains("IDENT")) {
                column1 = i + 1;
            } else if (columnMappingList.get(i).contains("NAZWA")) {
                column2 = i + 1;
            } else if (columnMappingList.get(i).contains("MINISTERSTWO")) {
                column3 = i + 1;
            }
        }
        try {
            sqlList = mySqlCreator.getSqlDatabase(dbUrl, dbLogin, dbPass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Iterator<SqlDatabase> it = sqlList.iterator();

            while (it.hasNext()) {
                SqlDatabase e = it.next();
                try (
                        PreparedStatement preparedStatement = conn.prepareStatement(queryId);
                        PreparedStatement checkIfRecordExist = conn.prepareStatement(queryCheck)
                ){
                    //checking if record exist in database
                    checkIfRecordExist.setLong(1, e.getIdent());
                    ResultSet recordExist = checkIfRecordExist.executeQuery();
                    if (recordExist.next()) {
                        continue;
                    }
                    preparedStatement.setLong(column1, e.getIdent());
                    preparedStatement.setString(column2, e.getNazwa());
                    preparedStatement.setLong(column3, e.getMinisterstwo());
                    preparedStatement.addBatch();
                    preparedStatement.execute();
                    recordExist.close();
                } catch (SQLException e1) {
                    exceptionsHandler.columnMatchingError();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //function adding h2 database to main database
    public void addH2ToMedicine(final List<String> columnMappingList, final String dbUrl, final String dbPass, final String dbLogin) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<H2Database> h2List = new ArrayList<>();

        int column1 = 0;
        int column2 = 0;
        int column3 = 0;

        for (int i = 0; i < columnMappingList.size(); i++) {
            if (columnMappingList.get(i).contains("IDENTYFIKATOR")) {
                column1 = i + 1;
            } else if (columnMappingList.get(i).contains("NAZWA_LEKU")) {
                column2 = i + 1;
            } else if (columnMappingList.get(i).contains("MIN")) {
                column3 = i + 1;
            }
        }
        try {
            h2List = h2Creator.getH2Database(dbUrl, dbLogin, dbPass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Iterator<H2Database> it = h2List.iterator();

            while (it.hasNext()) {
                H2Database e = it.next();
                try (
                        PreparedStatement preparedStatement = conn.prepareStatement(queryId);
                        PreparedStatement checkIfRecordExist = conn.prepareStatement(queryCheck)
                ) {
                    //checking if record exist in database
                    checkIfRecordExist.setLong(1, e.getIdentyfikator());
                    ResultSet recordExist = checkIfRecordExist.executeQuery();
                    if (recordExist.next()) {
                        continue;
                    }
                    preparedStatement.setLong(column1, e.getIdentyfikator());
                    preparedStatement.setString(column2, e.getNazwaLeku());
                    preparedStatement.setLong(column3, e.getMin());
                    preparedStatement.addBatch();
                    preparedStatement.execute();
                    recordExist.close();
                } catch (SQLException e1) {
                    exceptionsHandler.columnMatchingError();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

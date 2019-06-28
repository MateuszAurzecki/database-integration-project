package pl.b2b.aurzecki.pharmacy.service;

import pl.b2b.aurzecki.pharmacy.errorDialog.ErrorMessageDialog;
import pl.b2b.aurzecki.pharmacy.exceptions.ConnectionExceptions;
import pl.b2b.aurzecki.pharmacy.model.ExcelDatabaseModel;
import pl.b2b.aurzecki.pharmacy.model.H2DatabaseModel;
import pl.b2b.aurzecki.pharmacy.model.MySqlDatabaseModel;

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

    private MySqlCreator mySqlCreator = new MySqlCreator();
    private H2Creator h2Creator = new H2Creator();
    private ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();


    //function adding ExcelFile to Main Database
    public String addExcelToMedicine(final List<String> columnMappingList, final String path) {

        ExcelCreator excelCreator = new ExcelCreator();

        //getting list of ExcelDatabaseModel object from excel file
        List<ExcelDatabaseModel> excelList = excelCreator.getDatabase(path);

        int column1 = 0;
        int column2 = 0;
        int column3 = 0;

        //setting table columns in custom order selected in the form
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
            Iterator<ExcelDatabaseModel> it = excelList.iterator();

            while (it.hasNext()) {
                ExcelDatabaseModel e = it.next();

                try (PreparedStatement ps = conn.prepareStatement(queryId);
                     PreparedStatement check = conn.prepareStatement(queryCheck)) {
                    //checking if record is present in main database
                    check.setLong(1, e.getLp());
                    try (ResultSet recordExist = check.executeQuery()) {
                        if (recordExist.next()) {
                            continue;
                        }
                        ps.setLong(column1, e.getLp());
                        ps.setString(column2, e.getNazwa());
                        ps.setLong(column3, e.getId_w_ministerstwie());
                        ps.addBatch();
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            errorMessageDialog.columnMatchingError();
        }
        return "Excel file succesfully added";
    }


    //function insert MySql database to main database
    public String addMySqlToMedicine(final List<String> columnMappingList, final String dbUrl, final String dbPass, final String dbLogin) {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int column1 = 0;
        int column2 = 0;
        int column3 = 0;

        //setting table columns in custom order selected in the form
        for (int i = 0; i < columnMappingList.size(); i++) {
            if (columnMappingList.get(i).contains("IDENT")) {
                column1 = i + 1;
            } else if (columnMappingList.get(i).contains("NAZWA")) {
                column2 = i + 1;
            } else if (columnMappingList.get(i).contains("MINISTERSTWO")) {
                column3 = i + 1;
            }
        }
        //getting list of MySqlDatabaseModel object from MySql database
        List<MySqlDatabaseModel> sqlList = mySqlCreator.getSqlDatabase(dbUrl, dbLogin, dbPass);


        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Iterator<MySqlDatabaseModel> it = sqlList.iterator();

            while (it.hasNext()) {
                MySqlDatabaseModel e = it.next();
                try (
                        PreparedStatement preparedStatement = conn.prepareStatement(queryId);
                        PreparedStatement checkIfRecordExist = conn.prepareStatement(queryCheck)
                ) {
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
                    errorMessageDialog.columnMatchingError();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database added";
    }

    //function adding h2 database to main database
    public String addH2ToMedicine(final List<String> columnMappingList, final String dbUrl, final String dbLogin, final String dbPass) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int column1 = 0;
        int column2 = 0;
        int column3 = 0;

        //setting table columns in custom order selected in the form
        for (int i = 0; i < columnMappingList.size(); i++) {
            if (columnMappingList.get(i).contains("IDENTYFIKATOR")) {
                column1 = i + 1;
            } else if (columnMappingList.get(i).contains("NAZWA_LEKU")) {
                column2 = i + 1;
            } else if (columnMappingList.get(i).contains("MIN")) {
                column3 = i + 1;
            }
        }
        List<H2DatabaseModel> h2List = h2Creator.getH2Database(dbUrl, dbLogin, dbPass);

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Iterator<H2DatabaseModel> it = h2List.iterator();

            while (it.hasNext()) {
                H2DatabaseModel e = it.next();
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
                    errorMessageDialog.columnMatchingError();
                }
            }
        } catch (SQLException e) {
            throw new ConnectionExceptions();
        }
        return "H2 database added to main database";
    }
}

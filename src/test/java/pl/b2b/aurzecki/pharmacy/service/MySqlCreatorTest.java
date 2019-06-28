package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.exceptions.ConnectionExceptions;
import pl.b2b.aurzecki.pharmacy.model.MySqlDatabaseModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MySqlCreatorTest {

    MySqlCreator mySqlCreator = new MySqlCreator();

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASS = "root";
    private static final String FAKE_DB_URL = "jdbc:mysql://localhost:3306/phacy";
    private static final String FAKE_DB_PASS = "r";

    @Test
    public void getSqlDatabaseTest() {
        List<MySqlDatabaseModel> mySqlDatabaseModelList = mySqlCreator.getSqlDatabase(DB_URL, DB_LOGIN, DB_PASS);
        Assert.assertEquals(mySqlDatabaseModelList.size(), 6);
    }

    @Test
    public void getSqlDatabaseFailTest() {
        assertThrows(ConnectionExceptions.class, () -> {
            mySqlCreator.getSqlDatabase(DB_URL, DB_LOGIN, FAKE_DB_PASS);
        });
    }

    @Test
    public void mySqlTableColumnNames() {
        List<String> columnNames = mySqlCreator.mySqlTableColumnNames(DB_URL, DB_LOGIN, DB_PASS);
        Assert.assertEquals("NAZWA", columnNames.get(1));
        Assert.assertEquals("IDENT", columnNames.get(0));
    }

    @Test
    public void mySqlTableColumnNamesFail1() {
        List<String> columnNames = mySqlCreator.mySqlTableColumnNames(DB_URL, DB_LOGIN, DB_PASS);
        Assert.assertNotEquals("IDNT", columnNames.get(0));
        Assert.assertNotEquals("nazwa", columnNames.get(1));
    }

    @Test
    public void mySqlTableColumnNamesFail2() {
        assertThrows(ConnectionExceptions.class, () -> {
            mySqlCreator.mySqlTableColumnNames(DB_URL, DB_LOGIN, FAKE_DB_PASS);
        });
    }
}
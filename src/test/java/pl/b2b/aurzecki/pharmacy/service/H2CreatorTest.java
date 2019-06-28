package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.exceptions.ConnectionExceptions;
import pl.b2b.aurzecki.pharmacy.model.H2DatabaseModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class H2CreatorTest {

    private H2Creator h2Creator = new H2Creator();
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/pharmacy";
    private static final String DB_LOGIN = "sa";
    private static final String DB_PASS = "";
    private static final String FAKE_DB_URL = "jdbc:h2:tcp://llhost/~/prmacy";


    @Test
    public void getH2DatabaseTest() {
        List<H2DatabaseModel> h2DatabaseModelList = h2Creator.getH2Database(DB_URL, DB_LOGIN, DB_PASS);

        Assert.assertEquals(h2DatabaseModelList.size(), 5);
    }

    @Test
    public void getH2DatabaseFailTest() {
        assertThrows(ConnectionExceptions.class, () -> {
            h2Creator.getH2Database(FAKE_DB_URL, DB_LOGIN, DB_PASS);
        });
    }


    @Test
    public void h2TableColumnNames() {
        List<String> columnNames = h2Creator.h2TableColumnNames(DB_URL, DB_LOGIN, DB_PASS);
        Assert.assertEquals("NAZWA_LEKU", columnNames.get(1));
    }

    @Test
    public void h2TableColumnNamesFail1() {
        List<String> columnNames = h2Creator.h2TableColumnNames(DB_URL, DB_LOGIN, DB_PASS);
        Assert.assertNotEquals("nazwaLeku", columnNames.get(1));
    }

    @Test
    public void h2TableColumnNamesFail2() {
        assertThrows(ConnectionExceptions.class, () -> {
            h2Creator.h2TableColumnNames(FAKE_DB_URL, DB_LOGIN, DB_PASS);
        });
    }
}
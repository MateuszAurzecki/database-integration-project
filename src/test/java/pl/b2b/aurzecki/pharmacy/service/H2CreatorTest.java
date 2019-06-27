package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.model.H2DatabaseModel;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class H2CreatorTest {

    private H2Creator h2Creator = new H2Creator();
   
    @Test
    void getH2DatabaseTest() {
        List<H2DatabaseModel> h2DatabaseModelList = new ArrayList<>();
        try {
            h2DatabaseModelList = h2Creator.getH2Database("jdbc:h2:tcp://localhost/~/pharmacy", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(h2DatabaseModelList.size(), 5);
    }

    @Test
    void getH2DatabaseFailTest() throws AWTException {
        List<H2DatabaseModel> h2DatabaseModelList = new ArrayList<>();
        try {
            h2DatabaseModelList = h2Creator.getH2Database("lalala", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertNotEquals(h2DatabaseModelList.size(), 5);


    }


    @Test
    void h2TableColumnNames() throws SQLException, ClassNotFoundException {
        List<String> columnNames = h2Creator.h2TableColumnNames("jdbc:h2:tcp://localhost/~/pharmacy", "sa", "");
        Assert.assertEquals("NAZWA_LEKU", columnNames.get(1));
    }
}
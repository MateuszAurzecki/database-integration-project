package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.model.MainDatabaseModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class MainCreatorTest {

    MainCreator mainCreator = new MainCreator();

    @Test
    void getMainDatabase() {

        List<MainDatabaseModel> mainDatabaseModelList = new ArrayList<>();
        try {
            mainDatabaseModelList = mainCreator.getMainDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(mainDatabaseModelList);
    }

    @Test
    void mainTableColumnNames() throws SQLException, ClassNotFoundException {
        List<String> columnNames = mainCreator.mainTableColumnNames();
        Assert.assertEquals("ID", columnNames.get(0));
        Assert.assertEquals("NAME", columnNames.get(1));
    }
}
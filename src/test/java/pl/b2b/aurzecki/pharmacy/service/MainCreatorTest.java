package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.model.MainDatabaseModel;

import java.util.List;

class MainCreatorTest {

    MainCreator mainCreator = new MainCreator();

    @Test
    public void getMainDatabaseTest() {

        List<MainDatabaseModel> mainDatabaseModelList;

        mainDatabaseModelList = mainCreator.getMainDatabase();
        Assert.assertNotNull(mainDatabaseModelList);
    }

    @Test
    public void mainTableColumnNamesTest() {
        List<String> columnNames = mainCreator.mainTableColumnNames();
        Assert.assertEquals("ID", columnNames.get(0));
        Assert.assertEquals("NAME", columnNames.get(1));
    }

    @Test
    public void mainTableColumnNamesTestFail() {
        List<String> columnNames = mainCreator.mainTableColumnNames();
        Assert.assertNotEquals("id", columnNames.get(0));
        Assert.assertNotEquals("names", columnNames.get(1));
    }
}
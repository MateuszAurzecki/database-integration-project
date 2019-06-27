package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.model.MySqlDatabaseModel;

import java.util.ArrayList;
import java.util.List;

class MySqlCreatorTest {

    MySqlCreator mySqlCreator = new MySqlCreator();

    @Test
    void getSqlDatabaseTest() {
        List<MySqlDatabaseModel> mySqlDatabaseModelList = new ArrayList<>();
        try {
            mySqlDatabaseModelList = mySqlCreator.getSqlDatabase("jdbc:mysql://localhost:3306/pharmacy", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(mySqlDatabaseModelList.size(), 6);
    }

    @Test
    void getSqlDatabaseFailTest() {
        List<MySqlDatabaseModel> mySqlDatabaseModelList = new ArrayList<>();
        try {
            mySqlDatabaseModelList = mySqlCreator.getSqlDatabase("jdbc:mysql://localhost:3306/pharmacy", "t", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertNotEquals(mySqlDatabaseModelList.size(), 6);
    }

    @Test
    void mySqlTableColumnNames() throws ClassNotFoundException {
        List<String> columnNames = mySqlCreator.mySqlTableColumnNames("jdbc:mysql://localhost:3306/pharmacy", "root", "root");
        Assert.assertEquals("NAZWA", columnNames.get(1));
        Assert.assertEquals("IDENT", columnNames.get(0));
    }
}
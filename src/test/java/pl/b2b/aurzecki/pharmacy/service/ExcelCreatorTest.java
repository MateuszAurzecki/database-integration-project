package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.exceptions.FilePathExceptions;
import pl.b2b.aurzecki.pharmacy.model.ExcelDatabaseModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExcelCreatorTest {

    private ExcelCreator excelCreator = new ExcelCreator();
    private static final String PATH = "src/main/resources/ExcelDatabase.xlsx";
    private static final String FAKE_PATH = "src/main/resoces/Excelabase.xlsx";

    @Test
    public void getDatabaseTest() {
        List<ExcelDatabaseModel> excelDatabaseModelList = excelCreator.getDatabase(PATH);
        Assert.assertEquals(4, excelDatabaseModelList.size());
    }


    @Test
    public void getDatabaseFailTest() {
        assertThrows(FilePathExceptions.class, () -> {
            excelCreator.getDatabase(FAKE_PATH);
        });
    }

    @Test
    public void excelTableColumnNamesTest() {
        List<String> columnNames = excelCreator.excelTableColumnNames(PATH);
        Assert.assertEquals(columnNames.get(0), "lp");
        Assert.assertEquals(columnNames.get(1), "nazwa");
    }

    @Test
    public void excelTableColumnNamesFailTest() {
        assertThrows(FilePathExceptions.class, () -> {
            excelCreator.excelTableColumnNames(FAKE_PATH);
        });
    }
}
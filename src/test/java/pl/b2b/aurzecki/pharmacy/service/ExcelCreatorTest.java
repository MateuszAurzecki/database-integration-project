package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.b2b.aurzecki.pharmacy.model.ExcelDatabaseModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExcelCreatorTest {

    List<ExcelDatabaseModel> excelDatabaseModelList;
    List<String> columnNamesList;

    @Mock
    ExcelCreator excelCreator;

    @Before
    public void setUp() {

        excelDatabaseModelList = new ArrayList<>();
        ExcelDatabaseModel excelDatabaseModel1 = new ExcelDatabaseModel();
        excelDatabaseModel1.setLp(1L);
        excelDatabaseModel1.setNazwa("Apap");
        excelDatabaseModel1.setId_w_ministerstwie(123456789L);
        excelDatabaseModelList.add(excelDatabaseModel1);
        ExcelDatabaseModel excelDatabaseModel2 = new ExcelDatabaseModel();
        excelDatabaseModel2.setLp(2L);
        excelDatabaseModel2.setNazwa("Apap");
        excelDatabaseModel2.setId_w_ministerstwie(123456789L);
        excelDatabaseModelList.add(excelDatabaseModel1);
        ExcelDatabaseModel excelDatabaseModel3 = new ExcelDatabaseModel();
        excelDatabaseModel3.setLp(3L);
        excelDatabaseModel3.setNazwa("Apap");
        excelDatabaseModel3.setId_w_ministerstwie(123456789L);
        excelDatabaseModelList.add(excelDatabaseModel1);
        ExcelDatabaseModel excelDatabaseModel4 = new ExcelDatabaseModel();
        excelDatabaseModel4.setLp(4L);
        excelDatabaseModel4.setNazwa("Apap");
        excelDatabaseModel4.setId_w_ministerstwie(123456789L);
        excelDatabaseModelList.add(excelDatabaseModel4);

        columnNamesList = new ArrayList<>();
        columnNamesList.add("lp");
        columnNamesList.add("nazwa");
        columnNamesList.add("ministerstwo");
    }

    @Test
    public void getDatabseTest() {
        String realPath = "src/main/resources/ExcelDatabaseModel.xlsx";
        when(excelCreator.getDatabase(realPath)).thenReturn(excelDatabaseModelList);

        Assert.assertEquals(excelDatabaseModelList.size(), 4);
    }

    @Test
    public void getDatabseFailTest() {
        String fakePath = "src/main/resources/ExcelDatab.xl";
        when(excelCreator.getDatabase(fakePath)).thenThrow(IOException.class);
    }

    @Test
    public void excelTableColumnNamesTest() throws IOException {
        String realPath = "src/main/resources/ExcelDatabaseModel.xlsx";
        when(excelCreator.excelTableColumnNames(realPath)).thenReturn(columnNamesList);
        Assert.assertEquals(columnNamesList.size(), 3);
    }


}

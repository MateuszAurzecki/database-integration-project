package pl.b2b.aurzecki.pharmacy.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.b2b.aurzecki.pharmacy.exceptions.ConnectionExceptions;
import pl.b2b.aurzecki.pharmacy.exceptions.FilePathExceptions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddDatabaseToMedicineTest {

    private AddDatabaseToMedicine addDatabaseToMedicine = new AddDatabaseToMedicine();


    @Test
    void addExcelToMedicine() {
        List<String> names = new ArrayList<>();
        names.add("LP");
        names.add("NAZWA");
        names.add("ID_W_MINISTERSTWIE");
        String result = addDatabaseToMedicine.addExcelToMedicine(names, "src/main/resources/ExcelDatabase.xlsx");
        Assert.assertEquals("Excel file succesfully added", result);

    }

    @Test
    void addExcelToMedicineFail() {
        List<String> names = new ArrayList<>();
        names.add("LP");
        names.add("NAZWA");
        names.add("ID_W_MINISTERSTWIE");
        assertThrows(FilePathExceptions.class, () -> {
            addDatabaseToMedicine.addExcelToMedicine(names, "src/main/resources/Eabase.xlsx");
        });
    }

    @Test
    void addMySqlToMedicine() {
        List<String> names = new ArrayList<>();
        names.add("IDENT");
        names.add("NAZWA");
        names.add("MINISTERSTWO");
        String result = addDatabaseToMedicine.addMySqlToMedicine(names, "jdbc:mysql://localhost:3306/pharmacy", "root", "root");
        Assert.assertEquals("Database added", result);
    }


    @Test
    void addMySqlToMedicineFail() {
        List<String> names = new ArrayList<>();
        names.add("IDENT");
        names.add("NAZWA");
        names.add("MINISTERSTWO");
        assertThrows(ConnectionExceptions.class, () -> {
            addDatabaseToMedicine.addMySqlToMedicine(names, "jdbc:mysql://localhost:3306/pharmacy", "rt", "root");
        });
    }

    @Test
    void addH2ToMedicine() {
        List<String> columns = new ArrayList<>();
        columns.add("IDENTYFIKATOR");
        columns.add("NAZWA_LEKU");
        columns.add("MIN");
        String result = addDatabaseToMedicine.addH2ToMedicine(columns, "jdbc:h2:tcp://localhost/~/pharmacy", "sa", "");
        Assert.assertEquals("H2 database added to main database", result);
    }

    @Test
    void addH2ToMedicineFail() {
        List<String> columns = new ArrayList<>();
        columns.add("IDENTYFIKATOR");
        columns.add("NAZWA_LEKU");
        columns.add("MIN");
        assertThrows(ConnectionExceptions.class, () -> {
            addDatabaseToMedicine.addH2ToMedicine(columns, "jdbc:h2:tcp://localhost/~/phcy", "sa", "");
        });
    }
}
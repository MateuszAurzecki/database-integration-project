package pl.b2b.aurzecki.pharmacy.mapper;

import pl.b2b.aurzecki.pharmacy.domain.ExcelDatabase;
import pl.b2b.aurzecki.pharmacy.domain.H2Database;
import pl.b2b.aurzecki.pharmacy.domain.MainDatabase;
import pl.b2b.aurzecki.pharmacy.domain.SqlDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMapper {

    public List<MainDatabase> ExcelToMedicineMapper(List<ExcelDatabase> excelDatabaseList) {
        List<MainDatabase> result = new ArrayList<>();

        for (ExcelDatabase e : excelDatabaseList) {
            MainDatabase mainDatabase = new MainDatabase();
            mainDatabase.setId(e.getLp());
            mainDatabase.setName(e.getNazwa());
            mainDatabase.setGovernmentNumber(e.getId_w_ministerstwie());
            result.add(mainDatabase);
        }
        return result;
    }

    public List<MainDatabase> H2ToMedicineMapper(List<H2Database> h2DatabaseList) {
        List<MainDatabase> result = new ArrayList<>();

        for (H2Database e : h2DatabaseList) {
            MainDatabase mainDatabase = new MainDatabase();
            mainDatabase.setId(e.getIdentyfikator());
            mainDatabase.setName(e.getNazwaLeku());
            mainDatabase.setGovernmentNumber(e.getMin());
            result.add(mainDatabase);
        }
        return result;
    }

    public List<MainDatabase> SqlToMedicineMapper(List<SqlDatabase> sqlDatabasesList) {
        List<MainDatabase> result = new ArrayList<>();

        for (SqlDatabase e : sqlDatabasesList) {
            MainDatabase mainDatabase = new MainDatabase();
            mainDatabase.setId(e.getIdent());
            mainDatabase.setName(e.getNazwa());
            mainDatabase.setGovernmentNumber(e.getMinisterstwo());
            result.add(mainDatabase);
        }
        return result;
    }

}

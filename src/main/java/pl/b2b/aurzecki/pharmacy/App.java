package pl.b2b.aurzecki.pharmacy;

import pl.b2b.aurzecki.pharmacy.gui.PharmacyGui;

import java.io.IOException;

public class App {

    public static int number;


    public static void main(String[] args)  {

        PharmacyGui pharmacyGui = new PharmacyGui();
    }


}


















    //        MySqlCreator mysqlCreator = new MySqlCreator();
//        List<SqlDatabase> list = mysqlCreator.getMainDatabase();
//
//        for (SqlDatabase l: list) {
//            System.out.println(l);
//        }
//
//        H2Creator h2Creator = new H2Creator();
//        List<H2Database> listH2 = h2Creator.getMainDatabase();
//
//        for (H2Database h: listH2) {
//            System.out.println(h);
//        }

//        ExcelCreator excelCreator = new ExcelCreator();
//        List<ExcelDatabase> excelDatabases = excelCreator.getDatabase();
//        for (ExcelDatabase e : excelDatabases) {
//            System.out.println(e);
//        }
//        AddDatabaseToMedicine addDatabaseToMedicine = new AddDatabaseToMedicine();
//        try {
//            addDatabaseToMedicine.addH2ToMedicine();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

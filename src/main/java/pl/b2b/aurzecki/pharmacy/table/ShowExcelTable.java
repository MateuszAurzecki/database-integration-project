package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.creator.ExcelCreator;
import pl.b2b.aurzecki.pharmacy.domain.ExcelDatabase;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ShowExcelTable extends JPanel {

    public ShowExcelTable(String path) throws IOException {
        super(new GridLayout(1,0));

        String[] columnNames = {"lp",
                "nazwa",
                "id_w_ministerstwie"};


        ExcelCreator excelCreator = new ExcelCreator();
        List<ExcelDatabase> excelDatabase = excelCreator.getDatabase(path);


        Object[][] database = new Object[excelDatabase.size()][3];
        for(int i = 0; i < excelDatabase.size(); i++){
               database[i][0] = excelDatabase.get(i).getLp();
               database[i][1] = excelDatabase.get(i).getNazwa();
               database[i][2] = excelDatabase.get(i).getId_w_ministerstwie();
        }



        final JTable table = new JTable(database, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);


        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }


    private static void createAndShowGUI(String path) throws IOException {
        JFrame frame = new JFrame("ShowExcelTable");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ShowExcelTable newContentPane = new ShowExcelTable(path);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void getGui(String path) throws IOException {
        createAndShowGUI(path);
    }
}
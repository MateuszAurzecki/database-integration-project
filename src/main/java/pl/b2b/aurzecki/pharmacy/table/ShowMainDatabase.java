package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.model.MainDatabaseModel;
import pl.b2b.aurzecki.pharmacy.service.MainCreator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowMainDatabase extends JPanel {

    private ShowMainDatabase() {
        super(new GridLayout(1, 0));

        String[] columnNames = {"id",
                "name",
                "government_number"};

        MainCreator mainCreator = new MainCreator();
        //getting data from database
        List<MainDatabaseModel> mainDatabaseModel = mainCreator.getMainDatabase();

        //inserting data to columns
        Object[][] database = new Object[mainDatabaseModel.size()][3];
        for (int i = 0; i < mainDatabaseModel.size(); i++) {
            database[i][0] = mainDatabaseModel.get(i).getId();
            database[i][1] = mainDatabaseModel.get(i).getName();
            database[i][2] = mainDatabaseModel.get(i).getGovernmentNumber();
        }


        final JTable table = new JTable(database, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }


    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Show Main Database");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ShowMainDatabase newContentPane = new ShowMainDatabase();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void getGui() {
        createAndShowGUI();
    }
}

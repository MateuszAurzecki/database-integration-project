package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.model.H2DatabaseModel;
import pl.b2b.aurzecki.pharmacy.service.H2Creator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowH2Table extends JPanel {

    private H2Creator h2Creator = new H2Creator();
    private List<String> columnNames;
    private List<H2DatabaseModel> h2DatabaseModel;


    private ShowH2Table(final String dbUrl, final String dbLogin, final String dbPass) {
        super(new GridLayout(1, 0));

        //getting column names for table
        columnNames = h2Creator.h2TableColumnNames(dbUrl, dbLogin, dbPass);

        //getting data from database
        h2DatabaseModel = h2Creator.getH2Database(dbUrl, dbLogin, dbPass);

        //inserting data to columns
        Object[][] database = new Object[h2DatabaseModel.size()][3];
        for (int i = 0; i < h2DatabaseModel.size(); i++) {
            database[i][0] = h2DatabaseModel.get(i).getIdentyfikator();
            database[i][1] = h2DatabaseModel.get(i).getNazwaLeku();
            database[i][2] = h2DatabaseModel.get(i).getMin();
        }


        final JTable table = new JTable(database, columnNames.toArray());
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }


    private static void createAndShowGUI(final String dbUrl, final String dbLogin, final String dbPass) {
        //Create and set up the window.
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        ShowH2Table h2Table = new ShowH2Table(dbUrl, dbLogin, dbPass);
        h2Table.setOpaque(true); //content panes must be opaque
        dialog.setContentPane(h2Table);

        //Display the window.
        dialog.pack();
        dialog.setVisible(true);
        dialog.setModal(true);

    }

    public static void getGui(final String dbUrl, final String dbLogin, final String dbPass) throws ClassNotFoundException {
        createAndShowGUI(dbUrl, dbLogin, dbPass);
    }
}


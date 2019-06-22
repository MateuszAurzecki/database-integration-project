package pl.b2b.aurzecki.pharmacy.table;

import pl.b2b.aurzecki.pharmacy.creator.H2Creator;
import pl.b2b.aurzecki.pharmacy.domain.H2Database;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowH2Table extends JPanel {


    private ShowH2Table(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        super(new GridLayout(1, 0));

        String[] columnNames = {"identyfikator",
                "nazwaLeku",
                "min"};

        H2Creator h2Creator = new H2Creator();
        List<H2Database> h2Database = h2Creator.getH2Database(dbUrl, dbLogin, dbPass);


        Object[][] database = new Object[h2Database.size()][3];
        for (int i = 0; i < h2Database.size(); i++) {
            database[i][0] = h2Database.get(i).getIdentyfikator();
            database[i][1] = h2Database.get(i).getNazwaLeku();
            database[i][2] = h2Database.get(i).getMin();
        }


        final JTable table = new JTable(database, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1280, 720));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }


    private static void createAndShowGUI(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        //Create and set up the window.
        JFrame frame = new JFrame("Show H2 Table");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        ShowH2Table newContentPane = new ShowH2Table(dbUrl, dbLogin, dbPass);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void getGui(String dbUrl, String dbLogin, String dbPass) throws ClassNotFoundException {
        createAndShowGUI(dbUrl, dbLogin, dbPass);
    }
}


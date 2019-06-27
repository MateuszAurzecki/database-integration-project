package pl.b2b.aurzecki.pharmacy.view;

import pl.b2b.aurzecki.pharmacy.service.ExcelCreator;
import pl.b2b.aurzecki.pharmacy.service.AddDatabaseToMedicine;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelMappingWindow extends JDialog {


    private JLabel title;
    private JLabel id;
    private JLabel name;
    private JLabel governmentNumber;
    private JButton confirm;
    private JButton cancel;
    private JComboBox mappingTable1;
    private JComboBox mappingTable2;
    private JComboBox mappingTable3;
    private final Font font = new Font("Monospaced", Font.BOLD, 16);
    private ExcelCreator excelCreator = new ExcelCreator();
    private AddDatabaseToMedicine addDatabaseToMedicine = new AddDatabaseToMedicine();
    private List<String> columnNames = new ArrayList<>();
    PharmacyGui pharmacyGui = new PharmacyGui();


    public ExcelMappingWindow(final String filePath) {
        setSize(1280, 720);
        setTitle("Please match data in Tables");
        setLayout(null);

        try {
            columnNames = excelCreator.excelTableColumnNames(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        title = new JLabel("Please match columns in databases");
        title.setBounds(200, 5, 500, 40);
        title.setFont(font);
        add(title);

        id = new JLabel("id");
        id.setBounds(100, 60, 300, 40);
        id.setFont(font);
        add(id);

        name = new JLabel("name");
        name.setBounds(100, 140, 300, 40);
        name.setFont(font);
        add(name);

        governmentNumber = new JLabel("government number");
        governmentNumber.setBounds(100, 220, 300, 40);
        governmentNumber.setFont(font);
        add(governmentNumber);

        mappingTable1 = new JComboBox();
        mappingTable1.setBounds(400, 60, 300, 40);
        for (int i = 0; i < columnNames.size(); i++) {
            mappingTable1.addItem(columnNames.get(i));
        }
        add(mappingTable1);

        mappingTable2 = new JComboBox();
        mappingTable2.setBounds(400, 140, 300, 40);
        for (int i = 0; i < columnNames.size(); i++) {
            mappingTable2.addItem(columnNames.get(i));
        }
        add(mappingTable2);

        mappingTable3 = new JComboBox();
        mappingTable3.setBounds(400, 220, 300, 40);
        for (int i = 0; i < columnNames.size(); i++) {
            mappingTable3.addItem(columnNames.get(i));
        }
        add(mappingTable3);


        confirm = new JButton("Confirm");
        confirm.setFont(font);
        confirm.setBounds(50, 500, 200, 40);
        add(confirm);
        confirm.addActionListener(e -> {
            List<String> matchingList = new ArrayList<>();
            matchingList.add(mappingTable1.getSelectedItem().toString());
            matchingList.add(mappingTable2.getSelectedItem().toString());
            matchingList.add(mappingTable3.getSelectedItem().toString());
            addDatabaseToMedicine.addExcelToMedicine(matchingList, filePath);
            pharmacyGui.setVisible(true);
            this.setVisible(false);
        });


        cancel = new JButton("Cancel");
        cancel.setFont(font);
        cancel.setBounds(350, 500, 200, 40);
        add(cancel);
        cancel.addActionListener(e -> {
            this.setVisible(false);
            pharmacyGui.setVisible(true);
        });
    }
}
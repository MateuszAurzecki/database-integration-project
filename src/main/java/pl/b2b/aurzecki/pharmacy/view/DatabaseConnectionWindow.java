package pl.b2b.aurzecki.pharmacy.view;

import pl.b2b.aurzecki.pharmacy.errorDialog.ErrorMessageDialog;
import pl.b2b.aurzecki.pharmacy.service.AddDatabaseToMedicine;
import pl.b2b.aurzecki.pharmacy.table.ShowExcelTable;
import pl.b2b.aurzecki.pharmacy.table.ShowH2Table;
import pl.b2b.aurzecki.pharmacy.table.ShowMySqlTable;
import pl.b2b.aurzecki.pharmacy.validator.Validator;

import javax.swing.*;
import java.awt.*;

public class DatabaseConnectionWindow extends JDialog {

    private JTextField tDbUrl;
    private JTextField tLogin;
    private JTextField tPassword;
    private JTextField tExcelFile;
    private JLabel lExcelUrl;
    private JLabel lDriver;
    private JLabel lDbUrl;
    private JLabel lLogin;
    private JLabel lPassword;
    private JButton bAddToMainDatabase;
    private JButton bShowExcelDatabase;
    private JButton bAddExcelToDatabase;
    private JButton bShowDatabase;
    private JButton bBackToMainMenu;
    private JComboBox driverSelection;
    private static final Font fontLabel = new Font("Monospaced", Font.BOLD, 16);
    private static final Font fontButton = new Font("Monospaced", Font.BOLD, 13);
    private ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
    private DatabasesMappingWindow databasesMappingWindow;
    private AddDatabaseToMedicine addDatabaseToMedicine = new AddDatabaseToMedicine();
    private PharmacyGui pharmacyGui;
    private ExcelMappingWindow excelMappingWindow;
    private Validator validator = new Validator();


    public DatabaseConnectionWindow(JFrame owner) {

        super(owner, "Please insert database details", true);
        setSize(1280, 720);
        setTitle("Pharmacy database application");
        setLayout(null);

        String[] driverOptions = {"org.h2.Driver", "com.mysql.cj.jdbc.Driver"};
        driverSelection = new JComboBox(driverOptions);
        driverSelection.setBounds(560, 55, 200, 30);
        driverSelection.setSelectedIndex(0);
        add(driverSelection);

        lDriver = new JLabel("Database Driver");
        lDriver.setFont(fontLabel);
        lDriver.setBounds(360, 50, 200, 40);
        add(lDriver);

        lDbUrl = new JLabel("Database Url");
        lDbUrl.setFont(fontLabel);
        lDbUrl.setBounds(360, 90, 200, 40);
        add(lDbUrl);

        tDbUrl = new JTextField();
        tDbUrl.setBounds(560, 95, 200, 30);
        add(tDbUrl);


        lLogin = new JLabel("Login");
        lLogin.setFont(fontLabel);
        lLogin.setBounds(360, 130, 200, 40);
        add(lLogin);

        tLogin = new JTextField();
        tLogin.setBounds(560, 135, 200, 30);
        add(tLogin);

        lPassword = new JLabel("Password");
        lPassword.setFont(fontLabel);
        lPassword.setBounds(360, 170, 200, 40);
        add(lPassword);

        tPassword = new JTextField();
        tPassword.setBounds(560, 175, 200, 30);
        add(tPassword);


        bAddToMainDatabase = new JButton("Add to Main Database");
        bAddToMainDatabase.setFont(fontButton);
        bAddToMainDatabase.setBounds(460, 290, 285, 40);
        add(bAddToMainDatabase);
        bAddToMainDatabase.addActionListener(e -> {
            String dbUrl = tDbUrl.getText().trim();
            String dbLogin = tLogin.getText().trim();
            String dbPass = tPassword.getText().trim();
            if (!validator.isDatabaseConnectionFormFilled(dbUrl, dbLogin)) {
                errorMessageDialog.wrongDatabaseDetails();
            } else {
                databasesMappingWindow = new DatabasesMappingWindow(driverSelection.getSelectedItem().toString(), dbUrl, dbLogin, dbPass);
                this.setVisible(false);
                databasesMappingWindow.setVisible(true);
            }
        });


        bShowDatabase = new JButton("Show Database");
        bShowDatabase.setFont(fontButton);
        bShowDatabase.setBounds(460, 240, 285, 40);
        add(bShowDatabase);
        bShowDatabase.addActionListener(e -> {
            String dbUrl = tDbUrl.getText().trim();
            String dbLogin = tLogin.getText().trim();
            String dbPass = tPassword.getText().trim();
            if (!validator.isDatabaseConnectionFormFilled(dbUrl, dbLogin)) {
                errorMessageDialog.wrongDatabaseDetails();
            } else {
                if (driverSelection.getSelectedItem().toString().equals("org.h2.Driver")) {
                    try {
                        ShowH2Table.getGui(dbUrl, dbLogin, dbPass);

                    } catch (ClassNotFoundException e1) {
                        errorMessageDialog.connectionError();
                    }
                } else {
                    ShowMySqlTable.getGui(dbUrl, dbLogin, dbPass);

                }
            }
        });

        bBackToMainMenu = new JButton("Back to main menu");
        bBackToMainMenu.setFont(fontButton);
        bBackToMainMenu.setBounds(460, 500, 285, 40);
        add(bBackToMainMenu);
        bBackToMainMenu.addActionListener(e -> {
            this.setVisible(false);
            pharmacyGui = new PharmacyGui();
            pharmacyGui.setVisible(true);

        });


        lExcelUrl = new JLabel("Excel Path");
        lExcelUrl.setFont(fontLabel);
        lExcelUrl.setBounds(840, 50, 150, 40);
        add(lExcelUrl);

        tExcelFile = new JTextField();
        tExcelFile.setBounds(980, 55, 200, 30);
        add(tExcelFile);

        bShowExcelDatabase = new JButton("Show Excel database");
        bShowExcelDatabase.setFont(fontButton);
        bShowExcelDatabase.setBounds(910, 100, 285, 40);
        add(bShowExcelDatabase);
        bShowExcelDatabase.addActionListener(e -> {
            String excelPath = tExcelFile.getText();
            ShowExcelTable.getGui(excelPath);
        });

        bAddExcelToDatabase = new JButton("Add Excel file to database");
        bAddExcelToDatabase.setFont(fontButton);
        bAddExcelToDatabase.setBounds(910, 150, 285, 40);
        add(bAddExcelToDatabase);
        bAddExcelToDatabase.addActionListener(e -> {
            String excelPath = tExcelFile.getText();
            excelMappingWindow = new ExcelMappingWindow(excelPath);
            this.setVisible(false);
            excelMappingWindow.setVisible(true);
        });
    }
}


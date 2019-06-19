package pl.b2b.aurzecki.pharmacy.gui;

import pl.b2b.aurzecki.pharmacy.App;
import pl.b2b.aurzecki.pharmacy.utils.MainTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

class IdWindow extends JDialog {

    private JLabel title;
    private JButton bCompanyId;
    private JButton bGovernmentId;
    private MainTable mainTable = new MainTable();
    private final Font font = new Font("Monospaced", Font.BOLD, 12);

    IdWindow(JFrame owner) {
        super(owner, "wybierz klucz glowny tabeli", true);
        setSize(620, 200);
        setLayout(null);

        bCompanyId = new JButton("By Company id");
        bCompanyId.setFont(font);
        bCompanyId.setBounds(10, 50, 200, 40);
        add(bCompanyId);
        bCompanyId.addActionListener(e -> {
            try {
                App.number = 1;
                mainTable.CreateMainTable();
                setVisible(false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });


        bGovernmentId = new JButton("By Government id");
        bGovernmentId.setFont(font);
        bGovernmentId.setBounds(350, 50, 200, 40);
        add(bGovernmentId);
        bGovernmentId.addActionListener(e -> {
            try {
                App.number = 2;
                mainTable.CreateMainTable();
                setVisible(false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }
}

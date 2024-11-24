package views.seller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateReceiptUI extends JFrame {
    private MenuSellerUI menuEmp;
    private JLabel lblMessage;

    public CreateReceiptUI(MenuSellerUI menuEmp) {
        this.menuEmp = menuEmp;

        setTitle("Create Receipt");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        lblMessage = new JLabel("Các button quản lý ở đây");
        lblMessage.setBounds(100, 50, 200, 30);

        add(lblMessage);

        addWindowListener(new WindowCloseListener());
    }

    private class WindowCloseListener extends WindowAdapter {

        @Override
        public void windowClosed(WindowEvent e) {
            menuEmp.setVisible(true);
        }
    }

}

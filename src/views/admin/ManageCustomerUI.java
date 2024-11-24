
package views.admin;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ManageCustomerUI extends JFrame {
        private MenuAdminUI menuAd;

    public ManageCustomerUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Manage Color");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblMessage = new JLabel("Welcome to Another UI!");
        lblMessage.setBounds(100, 100, 200, 50);
        add(lblMessage);

        addWindowListener(new WindowCloseListener());
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuAd.setVisible(true); 
        }
    }
}

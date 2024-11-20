package views.admin;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageColorUI extends JFrame {
    private MenuAdminUI menuAd;

    public ManageColorUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Manage Color");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setResizable(false);
        setLayout(null);

        JLabel lblMessage = new JLabel("Các button quản lý ở đây");
        lblMessage.setBounds(100, 50, 200, 30);
        add(lblMessage);

        // Sử dụng Inner Class thay cho Anonymous Class
        addWindowListener(new WindowCloseListener());
    }

    // Inner class thay thế Anonymous Class
    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuAd.setVisible(true); // Hiển thị lại giao diện chính khi đóng cửa sổ này
        }
    }
}

package views.admin;

import controllers.admin.MenuAdminController;
import javax.swing.*;

public class MenuAdminUI extends JFrame {
    private JButton btnManageColor;
    public MenuAdminUI() {
        
        setTitle("ADMIN Menu");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        btnManageColor = new JButton("Quản lý màu");
        btnManageColor.setBounds(100, 100, 200, 50);
        add(btnManageColor);
        
        MenuAdminController controller = new MenuAdminController(this);

    }

    public void addManageColorListener(java.awt.event.ActionListener manageColorButton) {
        btnManageColor.addActionListener(manageColorButton);
    }
}

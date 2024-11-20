
package views.admin;

import javax.swing.*;

public class MenuAdminUI extends JFrame {
    public MenuAdminUI(){
        setTitle("ADMIN Menu");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        JButton manageColor = new JButton("Quản lý màu");
        manageColor.setBounds(100, 100, 200, 50);
        manageColor.addActionListener(e -> {
            ManageColorUI subFrame = new ManageColorUI(this); 
            subFrame.setVisible(true);
            this.setVisible(false); 
        });

        add(manageColor);
    
    }
}

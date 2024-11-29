package views;

import Utils.SetIconUtil;
import javax.swing.*;

public class LoginUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblUsername, lblPassword, lblMessage;

    public LoginUI() {
        setTitle("Login Form");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
    
        //ImageIcon icon= new ImageIcon("src/Utils/logo.jpg") ;
        setIconImage(SetIconUtil.getIcon().getImage());
        
        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 100, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 50, 200, 25);
        add(txtUsername);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 100, 200, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 150, 100, 30);
        add(btnLogin);

        lblMessage = new JLabel("");
        lblMessage.setBounds(50, 200, 300, 25);
        add(lblMessage);
    }

    // Getters for view components
    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void setMessage(String message) {
        lblMessage.setText(message);
    }

    public void addLoginListener(java.awt.event.ActionListener loginButton) {
        btnLogin.addActionListener(loginButton);
    }
}

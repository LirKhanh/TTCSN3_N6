package controllers;

import Utils.ConnectJDBCUtil;
import views.LoginUI;
import java.sql.*;
import views.admin.MenuAdminUI;
import views.seller.MenuSellerUI;

public class LoginController {

    private LoginUI view;
    private Connection connection;

    public LoginController(LoginUI view) {
        this.view = view;

        try {
            connection = ConnectJDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.view.addLoginListener(e -> checkLogin());
        addKeyListenerForLogin();
    }

    private void checkLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        String query = "SELECT * FROM NV WHERE acc = ? AND pass = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String staffId = rs.getString("staff_id");
                String staffName = rs.getString("staff_name");
                boolean stat = rs.getBoolean("stat");

                if (stat) {
                    MenuAdminUI adminUI = new MenuAdminUI();
                    adminUI.setVisible(true);
                    view.setVisible(false);
                } else {
                    MenuSellerUI menuSellerUI = new MenuSellerUI();
                    menuSellerUI.setVisible(true);
                    view.setVisible(false);
                }
            } else {
                view.setMessage("Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            view.setMessage("Error: " + e.getMessage());
        }
    }

    private void addKeyListenerForLogin() {
        view.getTxtUsername().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });

        view.getTxtPassword().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
    }

    private void performLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        checkLogin();
    }
}

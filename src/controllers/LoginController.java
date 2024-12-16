package controllers;

import Utils.ConnectJDBCUtil;
import views.LoginUI;
import java.sql.*;
import views.admin.MenuAdminUI;
import views.seller.MenuSellerUI;

import javax.swing.*;

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

        String queryCheckEmpty = "SELECT COUNT(*) AS count FROM NV";
        String queryInsertDefault = "INSERT INTO NV (acc, pass, stat, staff_name) VALUES (?, ?, ?, ?)";
        String query = "SELECT * FROM NV WHERE acc = ? AND pass = ?";

        try {
            try (PreparedStatement stmtCheck = connection.prepareStatement(queryCheckEmpty)) {
                ResultSet rsCheck = stmtCheck.executeQuery();
                if (rsCheck.next() && rsCheck.getInt("count") == 0) {
                    try (PreparedStatement stmtInsert = connection.prepareStatement(queryInsertDefault)) {
                        stmtInsert.setString(1, username);
                        stmtInsert.setString(2, password);
                        stmtInsert.setBoolean(3, true);
                        stmtInsert.setString(4, "Default Admin");
                        stmtInsert.executeUpdate();
                        JOptionPane.showMessageDialog(view,"Danh sách tài khoản rỗng, tạo tài khoản admin thành công!\nTài khoản:"+username+"\nMật khẩu:"+password);
                    }
                }
            }

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
                    view.setMessage("Tài khoản không hợp lệ.");
                }
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

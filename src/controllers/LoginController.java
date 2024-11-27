package controllers;

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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fs?autoReconnect=true&useSSL=false",
                    "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.view.addLoginListener(e -> checkLogin());
    }

    private void checkLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        // Kiểm tra đăng nhập
        String query = "SELECT * FROM NV WHERE acc = ? AND pass = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Nếu đăng nhập thành công
                String staffId = rs.getString("staff_id");
                String staffName = rs.getString("staff_name");
                boolean stat = rs.getBoolean("stat");

                if (stat) {
                    MenuAdminUI adminUI = new MenuAdminUI();
                    adminUI.setVisible(true);
                    view.setVisible(false);
                    System.out.println("checkLogin");
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
}

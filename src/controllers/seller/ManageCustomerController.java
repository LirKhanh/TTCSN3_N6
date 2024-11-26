package controllers.seller;

import views.seller.ManageCustomerUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ManageCustomerController {

    private ManageCustomerUI view;
    private Connection connection;

    public ManageCustomerController(ManageCustomerUI view) {
        this.view = view;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fs?autoReconnect=true&useSSL=false",
                    "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.view.addCustomerButtonListener(e -> addCustomer());
        loadCustomers();
    }

    private void loadCustomers() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM KH";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString("cus_id");
                String name = rs.getString("cus_name");
                String phone = rs.getString("cus_phone");
                String address = rs.getString("address");
                String fspoint = rs.getString("fs_point");

                tableModel.addRow(new Object[]{id, name, phone, address, fspoint});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addCustomer() {
        String name = view.getTxtName().getText();
        String phone = view.getTxtPhone().getText();
        String address = view.getTxtAddress().getText();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO KH (cus_name, cus_phone, address) VALUES (?, ?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, address);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm khách hàng thành công!");
                loadCustomers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

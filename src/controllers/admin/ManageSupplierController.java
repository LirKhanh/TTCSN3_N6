package controllers.admin;

import Utils.ConnectJDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import views.admin.ManageSupplierUI;

public class ManageSupplierController {
    private ManageSupplierUI view;
    private Connection connection;

    public ManageSupplierController(ManageSupplierUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        loadSuppliers();
        this.view.addButtonListener(e -> addSupplier());
        this.view.updateButtonListener(e -> updateSupplier());
        this.view.delButtonListener(e -> deleteSupplier());
    }

    private void loadSuppliers() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM NCC ORDER BY sup_id ASC";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString("sup_id");
                String name = rs.getString("sup_name");
                String phone = rs.getString("sup_phone");

                tableModel.addRow(new Object[]{id, name, phone});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addSupplier() {
        String name = view.getTxtTenNCC().getText();
        String phone = view.getTxtSDT().getText();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO NCC (sup_name, sup_phone) VALUES (?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm nhà cung cấp thành công!");
                loadSuppliers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateSupplier() {
        String name = view.getTxtTenNCC().getText();
        String phone = view.getTxtSDT().getText();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhà cung cấp để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String supplierId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE NCC SET sup_name = ?, sup_phone = ? WHERE sup_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, supplierId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật nhà cung cấp thành công!");
                loadSuppliers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteSupplier() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhà cung cấp để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String supplierId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM NCC WHERE sup_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, supplierId);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(view, "Xóa nhà cung cấp thành công!");
                loadSuppliers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

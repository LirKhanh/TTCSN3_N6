
package controllers.admin;

import Utils.ConnectJDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import views.admin.ManageSizeUI;

public class ManageSizeController {
    private ManageSizeUI view;
    private Connection connection;

    public ManageSizeController(ManageSizeUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        loadSizes();
        this.view.addButtonListener(e -> addSize());
        this.view.updateButtonListener(e -> updateSize());
        this.view.delButtonListener(e -> deleteSize());
    }
    private void loadSizes() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM KC ORDER BY size_id ASC";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);

                tableModel.addRow(new Object[]{id, name});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách kích cỡ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addSize() {
        String name = view.getTxtName().getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO KC(size_name) VALUES (?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm kích cỡ thành công!");
                loadSizes();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm kích cỡ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateSize() {
        String name = view.getTxtName().getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn kích cỡ để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String sizeId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE KC SET size_name = ? WHERE size_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, sizeId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật kích cỡ thành công!");
                loadSizes();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật kích cỡ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteSize(){
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn kích cỡ để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String sizeId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM KC WHERE size_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, sizeId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Xoá kích cỡ thành công!");
                loadSizes();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa kích cỡ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

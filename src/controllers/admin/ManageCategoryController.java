
package controllers.admin;

import Utils.ConnectJDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import views.admin.ManageCategoryUI;

public class ManageCategoryController {
    private ManageCategoryUI view;
    private Connection connection;

    public ManageCategoryController(ManageCategoryUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        loadCategorys();
        this.view.addButtonListener(e -> addCategory());
        this.view.updateButtonListener(e -> updateCategory());
        this.view.delButtonListener(e -> deleteCategory());
    }
    private void loadCategorys() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM LH ORDER BY type_id ASC";
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
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách loài hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addCategory() {
        String name = view.getTxtName().getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO LH (type_name) VALUES (?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm loại hàng thành công!");
                loadCategorys();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm loại hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateCategory() {
        String name = view.getTxtName().getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn loại hàng để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String categoryId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE LH SET type_name = ? WHERE type_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, categoryId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật loại hàng thành công!");
                loadCategorys();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật loại hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCategory(){
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn loại hàng để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String categoryId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM LH WHERE type_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, categoryId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Xoá loại thành công!");
                loadCategorys();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa loại hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

package controllers.admin;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectJDBCUtil;
import views.admin.ManageHMSUI;

public class ManageHMSController {
    private ManageHMSUI view;
    private Connection connection;

    public ManageHMSController(ManageHMSUI view) {
        this.view = view;
        try {
            connection = ConnectJDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadComboBoxProduct();
        loadComboBoxColor();
        loadComboBoxSize();
        loadHms();
        view.addButtonListener(e -> addHmsOut());
    }

    private void loadComboBoxProduct() {
        String query = "SELECT * FROM HANG ORDER BY product_id";
        view.getJcbProduct().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString(2);
                view.setJcbProduct(value);
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy sản phẩm nào!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadComboBoxColor() {
        String query = "SELECT * FROM MAU  ORDER BY color_id";
        view.getJcbColor().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString(2);
                view.setJcbColor(value);
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy màu nào!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadComboBoxSize() {
        String query = "SELECT * FROM KC ORDER BY size_id";
        view.getJcbSize().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString(2);
                view.setJcbSize(value);
            }
            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy kích cỡ nào!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadHms() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "select HMS.hms_id, HANG.product_name,MAU.color_name,KC.size_name, HMS.quantity,HANG.price from HMS \n" +
                    "inner join HANG on HANG.product_id = HMS.product_id\n" +
                    "inner join MAU on MAU.color_id = HMS.color_id\n" +
                    "inner join KC on KC.size_id = HMS.size_id\n";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String color = rs.getString(3);
                String size = rs.getString(4);
                int quantity = Integer.parseInt(rs.getString(5));
                double price = Double.parseDouble(rs.getString(6));

                tableModel.addRow(new Object[]{id, name, color, size, quantity, price});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addHmsOut() {
        String name = view.getJcbProduct().getSelectedItem().toString();
        String color = view.getJcbColor().getSelectedItem().toString();
        String size = view.getJcbSize().getSelectedItem().toString();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(view.getJtfQuantity());
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(view, "Số lượng phải lớn hơn 0!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số lượng phải là một số hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (name.isEmpty() || color.isEmpty() || size.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String nameId, colorId, sizeId;

            String query1 = "SELECT product_id FROM HANG WHERE product_name = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    nameId = rs.getString("product_id");
                } else {
                    JOptionPane.showMessageDialog(view, "Sản phẩm không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String query2 = "SELECT color_id FROM MAU WHERE color_name = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query2)) {
                pstmt.setString(1, color);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    colorId = rs.getString("color_id");
                } else {
                    JOptionPane.showMessageDialog(view, "Màu không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String query3 = "SELECT size_id FROM KC WHERE size_name = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query3)) {
                pstmt.setString(1, size);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    sizeId = rs.getString("size_id");
                } else {
                    JOptionPane.showMessageDialog(view, "Kích cỡ không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }


            String query5 = "INSERT INTO HMS(product_id, color_id, size_id, quantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query5)) {
                pstmt.setString(1, nameId);
                pstmt.setString(2, colorId);
                pstmt.setString(3, sizeId);
                pstmt.setInt(4, quantity);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    loadHms();
                } else {
                    JOptionPane.showMessageDialog(view, "Lỗi khi thêm Hàng_Màu_Size", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm:" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        loadHms();
    }


//    private void updateSize() {
//        String name = view.getTxtName().getText();
//
//        if (name.isEmpty()) {
//            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        int selectedRow = view.getTable().getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(view, "Vui lòng chọn kích cỡ để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//        String sizeId = view.getTable().getValueAt(selectedRow, 0).toString();
//        PreparedStatement stmt = null;
//        try {
//            String query = "UPDATE KC SET size_name = ? WHERE size_id = ?";
//            stmt = connection.prepareStatement(query);
//            stmt.setString(1, name);
//            stmt.setString(2, sizeId);
//
//            int rowsUpdated = stmt.executeUpdate();
//            if (rowsUpdated > 0) {
//                JOptionPane.showMessageDialog(view, "Cập nhật kích cỡ thành công!");
//                loadSizes();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật kích cỡ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void deleteSize() {
//        int selectedRow = view.getTable().getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(view, "Vui lòng chọn kích cỡ để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//        String sizeId = view.getTable().getValueAt(selectedRow, 0).toString();
//        PreparedStatement stmt = null;
//        try {
//            String query = "DELETE FROM KC WHERE size_id = ?";
//            stmt = connection.prepareStatement(query);
//            stmt.setString(1, sizeId);
//
//            int rowsUpdated = stmt.executeUpdate();
//            if (rowsUpdated > 0) {
//                JOptionPane.showMessageDialog(view, "Xoá kích cỡ thành công!");
//                loadSizes();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(view, "Lỗi khi xóa kích cỡ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
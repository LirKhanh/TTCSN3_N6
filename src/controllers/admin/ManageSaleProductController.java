package controllers.admin;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectJDBCUtil;
import views.admin.ManageSaleProductUI;

public class ManageSaleProductController {
    private ManageSaleProductUI view;
    private Connection connection;

    public ManageSaleProductController(ManageSaleProductUI view) {
        this.view = view;
        try {
            connection = ConnectJDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadComboBoxHMS();
        loadComboBoxSale();
        loadHmsOut();
        view.addButtonListener(e -> addHmsOut());
    }

    private void loadComboBoxHMS() {
        String query = "SELECT * FROM HMS ORDER BY hms_id";
        view.getJcbHMS().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString("hms_id");
                view.setJcbHMS(value);
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy sản phẩm nào!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }


    private void loadComboBoxSale() {
        String query = "SELECT * FROM DGG ORDER BY sale_id";
        view.getJcbSale().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString(2);
                view.setJcbSale(value);
            }
            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy đợt giảm giá nào!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void loadHmsOut() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "select HMS.hms_id, HANG.product_name,MAU.color_name,KC.size_name, HMS.quantity, DGG.sale_name,HANG.price, HBR.sale_price from HMS \n" +
                    "inner join HBR on HMS.hms_id = HBR.hms_id\n" +
                    "inner join HANG on HANG.product_id = HMS.product_id\n" +
                    "inner join MAU on MAU.color_id = HMS.color_id\n" +
                    "inner join KC on KC.size_id = HMS.size_id\n" +
                    "inner join DGG on DGG.sale_id = HBR.sale_id;";
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
                String saleName = rs.getString(6);
                double price = Double.parseDouble(rs.getString(7));
                double salePrice = Double.parseDouble(rs.getString(8));

                tableModel.addRow(new Object[]{id, name, color, size, quantity, saleName, price, salePrice});
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
        String hmsId = view.getJcbHMS().getSelectedItem().toString();
        String sale = view.getJcbSale().getSelectedItem().toString();
        int quantity = 0;


        if (hmsId.isEmpty() || sale.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String saleId;

            String query4 = "SELECT sale_id FROM DGG WHERE sale_name = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query4)) {
                pstmt.setString(1, sale);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    saleId = rs.getString("sale_id");
                } else {
                    JOptionPane.showMessageDialog(view, "Đợt giảm giá không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            String query6 = "INSERT INTO HBR(hms_id, sale_id) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query6)) {
                pstmt.setString(1, hmsId);
                pstmt.setString(2, saleId);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    loadHmsOut();
                } else {
                    JOptionPane.showMessageDialog(view, "Lỗi khi thêm Hàng bán ra", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm:" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        loadHmsOut();
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
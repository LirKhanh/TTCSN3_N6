package controllers.admin;

import Utils.ConnectJDBCUtil;
import views.admin.ManageProductUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ManageProductController {
    private ManageProductUI view;
    private Connection connection;

    public ManageProductController(ManageProductUI view) {
        this.view = view;
        try {
            connection = ConnectJDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadComboBoxNCC();
        loadComboBoxLoaiHang();
        loadProductData();
        view.addButtonListener(e -> addProduct());
        view.updateButtonListener(e -> updateProduct());
        view.delButtonListener(e -> deleteProduct());
    }

    private void loadComboBoxNCC() {
        String query = "SELECT * FROM NCC ORDER BY sup_id";
        view.getJcbNCC().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String value = rs.getString("sup_name");
                view.setJcbNCC(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu nhà cung cấp: " + e.getMessage());
        }
    }

    // Load data for Mã loại hàng (Product Type) combo box
    private void loadComboBoxLoaiHang() {
        String query = "SELECT * FROM LH ORDER BY type_id";
        view.getJcbLoaiHang().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String value = rs.getString("type_name");
                view.setJcbLoaiHang(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu loại hàng: " + e.getMessage());
        }
    }

    private void loadProductData() {
        String query = "SELECT * FROM HANG";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productName = rs.getString("product_name");
                String supplierId = rs.getString("sup_id");
                String typeId = rs.getString("type_id");
                String location = rs.getString("location");
                double price = rs.getDouble("price");
                String brand = rs.getString("brand");

                tableModel.addRow(new Object[]{productId, productName, supplierId, typeId, location, price, brand});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu sản phẩm: " + e.getMessage());
        }
    }

    // Add a new product
    private void addProduct() {
        String productId = view.getTxtTenHang().getText();
        String productName = view.getTxtTenHang().getText();
        String supplierName = view.getJcbNCC().getSelectedItem().toString();
        String typeName = view.getJcbLoaiHang().getSelectedItem().toString();
        String location = view.getTxtViTri().getText();
        double price = Double.parseDouble(view.getTxtGia().getText());
        String brand = view.getTxtNhanHang().getText();
        String supplierId = "";
        String typeId = "";
        if (productId.isEmpty() || productName.isEmpty() || location.isEmpty() || price == 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String query1 = "SELECT sup_id FROM NCC WHERE sup_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
            pstmt.setString(1, supplierName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                supplierId = rs.getString("sup_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        String query2 = "SELECT type_id FROM LH WHERE type_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query2)) {
            pstmt.setString(1, typeName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                typeId = rs.getString("type_id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        String query = "INSERT INTO HANG (product_id, product_name, sup_id, type_id, location, price, brand) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, productId);
            pstmt.setString(2, productName);
            pstmt.setString(3, supplierId);
            pstmt.setString(4, typeId);
            pstmt.setString(5, location);
            pstmt.setDouble(6, price);
            pstmt.setString(7, brand);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                loadProductData();
                JOptionPane.showMessageDialog(view, "Thêm sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi khi thêm sản phẩm!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProduct() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn sản phẩm cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String productId = view.getTable().getValueAt(selectedRow, 0).toString();
        String productName = view.getTxtTenHang().getText();
        String supplierName = view.getJcbNCC().getSelectedItem().toString();
        String typeName = view.getJcbLoaiHang().getSelectedItem().toString();
        String location = view.getTxtViTri().getText();
        double price = Double.parseDouble(view.getTxtGia().getText());
        String brand = view.getTxtNhanHang().getText();
        String supplierId = "";
        String typeId = "";
        if (productId.isEmpty() || productName.isEmpty() || location.isEmpty() || price == 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String query1 = "SELECT sup_id FROM NCC WHERE sup_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
            pstmt.setString(1, supplierName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                supplierId = rs.getString("sup_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        String query2 = "SELECT type_id FROM LH WHERE type_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query2)) {
            pstmt.setString(1, typeName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                typeId = rs.getString("type_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        String query = "UPDATE HANG SET product_name = ?, sup_id = ?, type_id = ?, location = ?, price = ?, brand = ? WHERE product_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, productName);
            pstmt.setString(2, supplierId);
            pstmt.setString(3, typeId);
            pstmt.setString(4, location);
            pstmt.setDouble(5, price);
            pstmt.setString(6, brand);
            pstmt.setString(7, productId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                loadProductData();
                JOptionPane.showMessageDialog(view, "Cập nhật sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật sản phẩm!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete a product
    private void deleteProduct() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn sản phẩm cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String productId = view.getTable().getValueAt(selectedRow, 0).toString();

        String query = "DELETE FROM HANG WHERE product_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, productId);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                loadProductData();
                JOptionPane.showMessageDialog(view, "Xóa sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi khi xóa sản phẩm!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

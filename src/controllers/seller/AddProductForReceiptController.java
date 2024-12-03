package controllers.seller;

import Utils.ConnectJDBCUtil;
import views.seller.AddProductForReceiptUI;
import views.seller.CreateReceiptUI;
import views.seller.MenuSellerUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AddProductForReceiptController {
    private AddProductForReceiptUI view;
    private Connection connection;

    public AddProductForReceiptController(AddProductForReceiptUI view) {
        this.view = view;

        try {
            connection = ConnectJDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.view.addSearchListener(e -> loadSearchData());
        clickSelect();
    }

    private void clickSelect() {
        JTable table = view.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String maHMS = table.getValueAt(selectedRow, 0).toString();
                    String tenSP = table.getValueAt(selectedRow, 1).toString();
                    String mau = table.getValueAt(selectedRow, 2).toString();
                    String size = table.getValueAt(selectedRow, 3).toString();
                    String maDGG = table.getValueAt(selectedRow, 5).toString();
                    String gia = table.getValueAt(selectedRow, 6).toString();
                    String soLuong = table.getValueAt(selectedRow, 7).toString();

                    String purQuantity = JOptionPane.showInputDialog(
                            view,
                            "Mã HMS: " + maHMS + "\n" +
                                    "Tên SP: " + tenSP + "\n" +
                                    "Màu: " + mau + "\n" +
                                    "Size: " + size + "\n" +
                                    "Đợt giảm giá: " + maDGG + "\n" +
                                    "Giá: " + gia + "\n" +
                                    "Nhập số lượng muốn mua:"
                    );

                    if (purQuantity == null || purQuantity.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Hủy bỏ thao tác.");
                        return;
                    }

                    try {
                        int enteredQuantity = Integer.parseInt(purQuantity);
                        int availableQuantity = Integer.parseInt(soLuong);

                        if (enteredQuantity <= 0) {
                            JOptionPane.showMessageDialog(view, "Vui lòng nhập số lượng hợp lệ (>0).");
                        } else if (enteredQuantity > availableQuantity) {
                            JOptionPane.showMessageDialog(view, "Số lượng mua vượt quá số lượng tồn kho.");
                        } else {
                            String receiptId = "";
                            String query2 = "SELECT * FROM HD ORDER BY receipt_id DESC LIMIT 1";
                            try (PreparedStatement pstmt2 = connection.prepareStatement(query2)) {
                                ResultSet rs = pstmt2.executeQuery();
                                if (rs.next()) {
                                    receiptId = rs.getString("receipt_id");
                                }
                            }

                            String query = "INSERT INTO HBHD (hms_id, receipt_id, sale_id, pur_quantity) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                                pstmt.setString(1, maHMS);
                                pstmt.setString(2, receiptId);
                                pstmt.setString(3, maDGG);
                                pstmt.setInt(4, enteredQuantity);
                                pstmt.executeUpdate();
                                JOptionPane.showMessageDialog(view, "Thêm sản phẩm vào hóa đơn thành công!");
                                CreateReceiptUI receiptUI = view.getReceiptUI();
                                CreateReceiptController controller = new CreateReceiptController(receiptUI);
                                controller.loadTable();
                            }

                            String query3 = "UPDATE HMS " +
                                    "SET quantity = quantity - ? " +
                                    "WHERE hms_id = ?";
                            try (PreparedStatement pstmt3 = connection.prepareStatement(query3)) {
                                pstmt3.setInt(1, enteredQuantity);
                                pstmt3.setString(2, maHMS);
                                pstmt3.executeUpdate();
                                loadSearchData();
                            }

                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(view, "Vui lòng nhập số nguyên hợp lệ.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(view, "Lỗi cơ sở dữ liệu: " + ex.getMessage());
                    }
                }
            }
        });
    }


    private void loadSearchData() {
        DefaultTableModel model = view.getTableModel();
        String searchText = view.getSearchText().trim();

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }

        String query = "SELECT HMS.hms_id, "
                + "HANG.product_name, "
                + "MAU.color_name, "
                + "KC.size_name, "
                + "LH.type_name, "
                + "HBR.sale_id,"
                + "HBR.sale_price, "
                + "HMS.quantity, "
                + "NCC.sup_name, "
                + "HANG.location "
                + "FROM HMS "
                + "INNER JOIN HANG ON HMS.product_id = HANG.product_id "
                + "INNER JOIN MAU ON HMS.color_id = MAU.color_id "
                + "INNER JOIN KC ON HMS.size_id = KC.size_id "
                + "INNER JOIN LH ON HANG.type_id = LH.type_id "
                + "INNER JOIN HBR ON HMS.hms_id = HBR.hms_id "
                + "INNER JOIN NCC ON HANG.sup_id = NCC.sup_id "
                + "WHERE HANG.product_name LIKE ?"
                + "OR MAU.color_name LIKE ?"
                + "OR LH.type_name LIKE ?"
                + "OR HMS.hms_id LIKE ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + searchText + "%");
            pstmt.setString(2, "%" + searchText + "%");
            pstmt.setString(3, "%" + searchText + "%");
            pstmt.setString(4, "%" + searchText + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                model.setRowCount(0);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy sản phẩm nào!");
                    return;
                }

                while (rs.next()) {
                    Object[] rowData = {
                            rs.getString("HMS.hms_id"),
                            rs.getString("HANG.product_name"),
                            rs.getString("MAU.color_name"),
                            rs.getString("KC.size_name"),
                            rs.getString("LH.type_name"),
                            rs.getString("HBR.sale_id"),
                            rs.getDouble("HBR.sale_price"),
                            rs.getInt("HMS.quantity"),
                            rs.getString("NCC.sup_name"),
                            rs.getString("HANG.location")
                    };
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
}

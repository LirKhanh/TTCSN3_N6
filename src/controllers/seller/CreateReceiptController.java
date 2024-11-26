
package controllers.seller;

import java.sql.*;

import views.seller.AddProductForReceiptUI;
import views.seller.CreateReceiptUI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import views.seller.CreateReceiptUI;


public class CreateReceiptController {
    private CreateReceiptUI view;
    private Connection connection;

    public CreateReceiptController(CreateReceiptUI view) {
        this.view = view;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fs?autoReconnect=true&useSSL=false",
                    "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.view.createReceiptListener(e -> createNewReceipt());
        this.view.moveAddProductListener(e->moveAdd());
    }
    private void moveAdd() {
        AddProductForReceiptUI newAdd = new AddProductForReceiptUI(view);
        newAdd.setVisible(true);
    }
    public void loadTable() {
        String receiptId = "";
        String query2 = "SELECT * FROM HD ORDER BY receipt_id DESC LIMIT 1";
        try (PreparedStatement pstmt2 = connection.prepareStatement(query2)) {
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()) {
                receiptId = rs.getString("receipt_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query1 = "select HANG.product_name, MAU.color_name, KC.size_name, HBHD.pur_quantity, HBR.sale_price, HBHD.pur_quantity*HBR.sale_price as total from HBHD\n" +
                "inner join HMS on HMS.hms_id = HBHD.hms_id \n" +
                "inner join HANG on HANG.product_id = HMS.product_id\n" +
                "inner join MAU on MAU.color_id = HMS.color_id\n" +
                "inner join KC on KC.size_id = HMS.size_id\n" +
                "inner join HBR on HBR.hms_id = HBHD.hms_id AND HBR.sale_id = HBHD.sale_id\n" +
                "where receipt_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
            DefaultTableModel model = view.getTableModel();
            pstmt.setString(1,receiptId);
            try (ResultSet rs = pstmt.executeQuery()) {
                model.setRowCount(0);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy sản phẩm nào!");
                    return;
                }

                while (rs.next()) {
                    Object[] rowData = {
                            rs.getString(0),
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getDouble(5),
                    };
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
    public void loadComboBoxPTTT() {
        String query1 = "SELECT * FROM PTTT";
        try (PreparedStatement pstmt = connection.prepareStatement(query1);
             ResultSet rs = pstmt.executeQuery()) {

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString("pay_me_id");
                view.setCbBoxPTTT(value);
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy phương thức thanh toán nào!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    public void loadComboBoxStaff() {
        String query2 = "SELECT * FROM NV";
        try (PreparedStatement pstmt = connection.prepareStatement(query2);
             ResultSet rs = pstmt.executeQuery()) {

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString("staff_id");
                int stat = rs.getInt("stat");
                if (stat == 0) {
                    view.setCbBoxStaff(value);
                }
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên nào!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }

    }

    public void loadComboBoxCustomer() {
        String query3 = "SELECT * FROM KH";
        try (PreparedStatement pstmt = connection.prepareStatement(query3);
             ResultSet rs = pstmt.executeQuery()) {

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString("cus_id");
                view.setCbBoxCustomer(value);
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên nào!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    public void createNewReceipt() {
        String cusId = view.getCbBoxCustomer();
        String staffId = view.getCbBoxStaff();
        String payMeName = view.getCbBoxPTTT();
        String query1 = "INSERT INTO HD(pay_me_id,cus_id,staff_id) VALUES (?,?,?) ";
        try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
            pstmt.setString(1, payMeName);
            pstmt.setString(2,cusId);
            pstmt.setString(3, staffId);
            if (pstmt.executeUpdate() == 1) {
                String query2 = "SELECT * FROM HD ORDER BY receipt_id DESC LIMIT 1";
                try (PreparedStatement pstmt2 = connection.prepareStatement(query2)) {
                    ResultSet rs = pstmt2.executeQuery();
                    if(rs.next()) {
                        String data = rs.getString("receipt_id");
                        JOptionPane.showMessageDialog(view, "Tạo hóa đơn mới thành công:"+ data);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
}


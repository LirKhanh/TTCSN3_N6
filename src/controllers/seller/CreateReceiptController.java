
package controllers.seller;

import java.sql.*;

import Utils.ConnectJDBCUtil;
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
            connection = ConnectJDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.view.createReceiptListener(e -> createNewReceipt());
        this.view.moveAddProductListener(e -> moveAdd());
        this.view.delProductListener(e -> delProduct());
        this.view.printReceiptListener(e -> printReceipt());
    }

    private void printReceipt() {
        String cusId = view.getCbBoxCustomer().trim();
        double total = 0;
        for (int i = 0; i < view.getTable().getRowCount(); i++) {
            Object value = view.getTable().getValueAt(i, 6);
            if (value != null) {
                try {
                    total += Double.parseDouble(value.toString());
                } catch (NumberFormatException e) {
                    System.err.println("Dữ liệu không hợp lệ ở dòng " + (i + 1));
                }
            }
        }
        String query1 = "SELECT fs_point FROM KH  WHERE cus_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
            pstmt.setString(1, cusId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                total -= Double.parseDouble(rs.getString(1));
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi khi lấy FS Point");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query2 = "UPDATE KH SET fs_point = 0 WHERE cus_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query2)) {
            pstmt.setString(1, cusId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        total /= 100.0;
        String query = "UPDATE KH SET fs_point = fs_point + ? WHERE cus_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, total);
            pstmt.setString(2, cusId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.getBtnPrintReceipt().setEnabled(false);
        view.getBtnAddProduct().setEnabled(false);
        view.getBtnDelProduct().setEnabled(false);
    }

    private void delProduct() {
        int selectedRow = view.getTable().getSelectedRow();

        if (selectedRow != -1) {
            String hmsId = view.getTable().getValueAt(selectedRow, 0).toString();
            String receiptId = view.getLblReceipt().getText();
            int purQuantity = Integer.parseInt(view.getTable().getValueAt(selectedRow, 4).toString());
            String query3 = "UPDATE HMS " +
                    "SET quantity = quantity + ? " +
                    "WHERE hms_id = ?";
            try (PreparedStatement pstmt3 = connection.prepareStatement(query3)) {
                pstmt3.setInt(1, purQuantity);
                pstmt3.setString(2, hmsId);
                pstmt3.executeUpdate();

                String query = "DELETE FROM HBHD WHERE hms_id = ? AND receipt_id = ? ";
                try (PreparedStatement pstm = connection.prepareStatement(query)) {
                    pstm.setString(1, hmsId);
                    pstm.setString(2, receiptId);
                    pstm.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            loadTable();

        }
    }

    private void moveAdd() {
        if (!view.getLblReceipt().isVisible()) {
            JOptionPane.showMessageDialog(view, "Chưa tạo đơn hàng mới!");
        } else {
            AddProductForReceiptUI newAdd = new AddProductForReceiptUI(view);
            newAdd.setVisible(true);
        }
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
        if (receiptId.equals(view.getLblReceipt().getText())) {
            String query1 = "select HBHD.hms_id, HANG.product_name, MAU.color_name, KC.size_name, HBHD.pur_quantity, HBR.sale_price, HBHD.pur_quantity*HBR.sale_price as total from HBHD\n" +
                    "inner join HMS on HMS.hms_id = HBHD.hms_id \n" +
                    "inner join HANG on HANG.product_id = HMS.product_id\n" +
                    "inner join MAU on MAU.color_id = HMS.color_id\n" +
                    "inner join KC on KC.size_id = HMS.size_id\n" +
                    "inner join HBR on HBR.hms_id = HBHD.hms_id AND HBR.sale_id = HBHD.sale_id\n" +
                    "where receipt_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
                DefaultTableModel model = view.getTableModel();
                pstmt.setString(1, receiptId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    model.setRowCount(0);

                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(view, "Không tìm thấy sản phẩm nào!");
                        return;
                    }

                    while (rs.next()) {
                        Object[] rowData = {
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getDouble(7),
                        };
                        model.addRow(rowData);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    public void loadComboBoxPTTT() {
        String query1 = "SELECT * FROM PTTT ORDER BY pay_me_id";
        view.getCmbPayMethod().removeAllItems();

        try (PreparedStatement pstmt = connection.prepareStatement(query1);
             ResultSet rs = pstmt.executeQuery()) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String value = rs.getString(2);
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
        view.getCmbEmp().removeAllItems();

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
        view.getCmbCustomer().removeAllItems();

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

    private void createNewReceipt() {
        String cusId = view.getCbBoxCustomer();
        String staffId = view.getCbBoxStaff();
        String payMeName = view.getCbBoxPTTT();
        String query = "SELECT pay_me_id FROM PTTT WHERE pay_me_name = ?";
        String payMeId = null;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, payMeName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    payMeId = rs.getString("pay_me_id");
                } else {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy phương thức thanh toán với tên: " + payMeName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
        String query1 = "INSERT INTO HD(pay_me_id,cus_id,staff_id) VALUES (?,?,?) ";
        try (PreparedStatement pstmt = connection.prepareStatement(query1)) {
            pstmt.setString(1, payMeId);
            pstmt.setString(2, cusId);
            pstmt.setString(3, staffId);
            pstmt.executeUpdate();
            String query2 = "SELECT * FROM HD ORDER BY receipt_id DESC LIMIT 1";
            try (PreparedStatement pstmt2 = connection.prepareStatement(query2)) {
                ResultSet rs = pstmt2.executeQuery();
                if (rs.next()) {
                    String data = rs.getString("receipt_id");
                    JOptionPane.showMessageDialog(view, "Tạo hóa đơn mới thành công:" + data);
                    view.setLblReceiption(data);
                }
            }
            view.getBtnCreateReceipt().setVisible(false);
            loadTable();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
}


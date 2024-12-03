package controllers.seller;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectJDBCUtil;
import views.seller.CreateReceiptUI;
import views.seller.ManageCustomerUI;
import views.seller.MenuSellerUI;

public class MenuSellerController {

    private MenuSellerUI view;
    private Connection connection;

    public MenuSellerController(MenuSellerUI view) {
        this.view = view;

        try {
            connection = ConnectJDBCUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.view.addCreateReceiptListener(e -> createNewRecipt());
        this.view.addSearchListener(e -> loadSearchData());
        this.view.addManageCusListener(e -> moveManageCustomer());
    }

    private void createNewRecipt() {
        CreateReceiptUI newCreateReceipt = new CreateReceiptUI(view);
        newCreateReceipt.setVisible(true);
    }

    private void moveManageCustomer() {
        ManageCustomerUI newManageCustomer = new ManageCustomerUI(view);
        newManageCustomer.setVisible(true);
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

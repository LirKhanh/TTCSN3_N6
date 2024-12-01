
package controllers.admin;

import Utils.ConnectJDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import views.admin.ManagePaymentUI;

public class ManagePaymentController {
    private ManagePaymentUI view;
    private Connection connection;

    public ManagePaymentController(ManagePaymentUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        loadPayments();
        this.view.addButtonListener(e -> addPayment());
        this.view.updateButtonListener(e -> updatePayment());
        this.view.delButtonListener(e -> deletePayment());
    }
    private void loadPayments() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM PTTT ORDER BY pay_me_id ASC";
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
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách phương thức thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPayment() {
        String name = view.getTxtName().getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO PTTT(pay_me_name) VALUES (?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm phương thức thanh toán thành công!");
                loadPayments();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm phương thức thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePayment() {
        String name = view.getTxtName().getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

//        int rowCount = view.getTable().getRowCount();
//        for (int i=0; i< rowCount ; i++)
//        {
//            Object value=view.getTable().getValueAt(i, 1);
//
//        }

        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn phương thức thanh toán để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String paymentId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE PTTT SET pay_me_name = ? WHERE pay_me_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, paymentId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật phương thức thanh toán thành công!");
                loadPayments();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật phương thức thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePayment(){
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn phương thức thanh toán để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String paymentId = view.getTable().getValueAt(selectedRow, 0).toString();
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM PTTT WHERE pay_me_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, paymentId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Xoá phương thức thanh toán thành công!");
                loadPayments();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa phương thức thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

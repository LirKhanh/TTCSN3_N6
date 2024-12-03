package controllers.admin;

import Utils.ConnectJDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.admin.ManageStaffUI;
import views.admin.ManageCustomerUI;

public class ManageCustomerController {
    private final ManageCustomerUI view;
    private final Connection connection;

    public ManageCustomerController(ManageCustomerUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        this.view.updateCusButtonListener(e -> updateStaff());
        this.view.addCusButtonListener(e -> addStaff());
        this.view.deleteCusButtonListener(e -> deleteStaff());
        this.view.re_enterCusButtonListener(e -> re_enter());
        loadCus();
    }
    private void loadCus() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM KH";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString("cus_id");
                String name = rs.getString("cus_name");
                String phone = rs.getString("cus_phone");
                String address = rs.getString("address");
                String fspoint = rs.getString("fs_point");

                tableModel.addRow(new Object[]{id, name, phone, address, fspoint});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addStaff() {
        String name = view.getTxtName().getText();
        String id = view.getTxtID().getText();
        String ad = view.getTxtAddress().getText();
        String ph =view.getTxtPhone().getText();
        String po =view.getTxtPoint().getText();
        if (name.isEmpty() || id.isEmpty() || ph.isEmpty() || ad.isEmpty() || po.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int rowCount = view.getTable().getRowCount();
        for (int i=0; i< rowCount ; i++)
        {
            Object value=view.getTable().getValueAt(i, 2);

            if (id.equals(value.toString()))
            {
                JOptionPane.showMessageDialog(view, "Không được trùng tên ID !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO KH (cus_name,cus_phone, address, fs_point) VALUES (?,?, ?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, ph);
            stmt.setString(3, ad);
            stmt.setString(4, po);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm khách hàng thành công!");
                loadCus();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStaff() {
        String name = view.getTxtName().getText();
        String id = view.getTxtID().getText();
        String ad = view.getTxtAddress().getText();
        String ph =view.getTxtPhone().getText();
        String po =view.getTxtPoint().getText();

        if (name.isEmpty() || id.isEmpty() || ph.isEmpty() || ad.isEmpty() || po.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int rowCount = view.getTable().getRowCount();
        for (int i=0; i< rowCount ; i++)
        {
            Object value=view.getTable().getValueAt(i, 2);

            if (id.equals(value.toString()))
            {
                JOptionPane.showMessageDialog(view, "Không được trùng tên ID !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        // Lấy ID của khách hàng cần cập nhật từ bảng
        int selectedRow = view.getTable().getSelectedRow(); // Sử dụng getTable() để lấy JTable
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn khach hang để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String CusId = view.getTable().getValueAt(selectedRow, 0).toString(); // Lấy ID của nhân viên đã chọn

        PreparedStatement stmt = null;
        try {
            String query = "UPDATE KH SET cus_name = ?, cus_phone=?, address = ?, fs_point = ? WHERE cus_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, ph);
            stmt.setString(3, ad);
            stmt.setString(4, po); // Gửi ID nhân viên để xác định bản ghi cần cập nhật

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật nhân viên thành công!");
                loadCus(); // Tải lại danh sách nhân viên sau khi cập nhật
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteStaff(){
        int selectedRow=view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String cusId = view.getTable().getValueAt(selectedRow, 0).toString(); // Lấy ID của nhân viên đã chọn
        PreparedStatement stmt = null;
        try {
            String query = "Delete From KH WHERE cus_id = ?";
            stmt = connection.prepareStatement(query);

            stmt.setString(1, cusId); // Gửi ID nhân viên để xác định bản ghi cần xóa

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật nhân viên thành công!");
                loadCus(); // Tải lại danh sách nhân viên sau khi cập nhật
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void re_enter(){
        view.getTxtName().setText("");
        view.getTxtPhone().setText("");
        view.getTxtAddress().setText("");
        view.getTxtPoint().setText("");
    }
}

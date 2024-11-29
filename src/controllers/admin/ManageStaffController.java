
package controllers.admin;

import Utils.ConnectJDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.admin.ManageStaffUI;
import views.seller.ManageCustomerUI;

public class ManageStaffController {
    private ManageStaffUI view;
    private Connection connection;
    
    public ManageStaffController(ManageStaffUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        this.view.updateStaffButtonListener(e -> updateStaff());
        this.view.addStaffButtonListener(e -> addStaff());
        this.view.deleteStaffButtonListener(e -> deleteStaff());
        this.view.re_enterStaffButtonListener(e -> re_enter());
        
        loadStaffs();
    }
    private void loadStaffs() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM NV";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString("staff_id");
                String name = rs.getString("staff_name");
                String stat = rs.getString("stat");
                String account = rs.getString("acc");
                String password = rs.getString("pass");
                

                tableModel.addRow(new Object[]{id, name,stat, account, password});
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
        String account = view.getTxtAccount().getText();
        String password = view.getTxtPassword().getText();
        String stat =view.getTxtStat().getText();
        if (name.isEmpty() || account.isEmpty() || password.isEmpty() || stat.isEmpty() ) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int rowCount = view.getTable().getRowCount();
        for (int i=0; i< rowCount ; i++)
        {
            Object value=view.getTable().getValueAt(i, 2);
            
            if (account.equals(value.toString()))
            {
                JOptionPane.showMessageDialog(view, "Không được trùng tên tài khoản !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO NV (staff_name,stat, acc, pass) VALUES (?,?, ?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, stat);
            stmt.setString(3, account);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm khách hàng thành công!");
                loadStaffs();
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
        String stat=view.getTxtStat().getText();
        String account = view.getTxtAccount().getText();
        String password = view.getTxtPassword().getText();
        
        if (name.isEmpty() || account.isEmpty() || password.isEmpty() || stat.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int rowCount = view.getTable().getRowCount();
        for (int i=0; i< rowCount ; i++)
        {
            Object value=view.getTable().getValueAt(i, 2);
            
            if (account.equals(value.toString()))
            {
                JOptionPane.showMessageDialog(view, "Không được trùng tên tài khoản !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        // Lấy ID của khách hàng cần cập nhật từ bảng
        int selectedRow = view.getTable().getSelectedRow(); // Sử dụng getTable() để lấy JTable
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String staffId = view.getTable().getValueAt(selectedRow, 0).toString(); // Lấy ID của nhân viên đã chọn

        PreparedStatement stmt = null;
        try {
            String query = "UPDATE NV SET staff_name = ?, stat=?, acc = ?, pass = ? WHERE staff_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, stat);
            stmt.setString(3, account);
            stmt.setString(4, password);
            stmt.setString(5, staffId); // Gửi ID nhân viên để xác định bản ghi cần cập nhật

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật nhân viên thành công!");
                loadStaffs(); // Tải lại danh sách nhân viên sau khi cập nhật
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
        String staffId = view.getTable().getValueAt(selectedRow, 0).toString(); // Lấy ID của nhân viên đã chọn
        PreparedStatement stmt = null;
        try {
            String query = "Delete From NV WHERE staff_id = ?";
            stmt = connection.prepareStatement(query);
            
            stmt.setString(1, staffId); // Gửi ID nhân viên để xác định bản ghi cần xóa

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật nhân viên thành công!");
                loadStaffs(); // Tải lại danh sách nhân viên sau khi cập nhật
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
        view.getTxtStat().setText("");
        view.getTxtAccount().setText("");
        view.getTxtPassword().setText("");
        
        
    }
    
}

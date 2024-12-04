/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin;

import Utils.ConnectJDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.admin.ManageDiscountUI;

/**
 *
 * @author Tuananh
 */
public class ManagerDiscountController {
    private final ManageDiscountUI view;
    private final Connection connection;

    public ManagerDiscountController (ManageDiscountUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        this.view.updateDisButtonListener(e -> updateDis());
        this.view.addDisButtonListener(e -> addDis());
        this.view.deleteDisButtonListener(e -> deleteDis());
        this.view.re_enterCusButtonListener(e -> re_enter());
        loadDis();
    }
    
    private void loadDis() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM DGG";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String id = rs.getString("sale_id");
                String name = rs.getString("sale_name");
                int percent = rs.getInt("percent");

                tableModel.addRow(new Object[]{id, name, percent});
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
    
    private void addDis() {
        String name = view.getTxtName().getText();
        String perText = view.getTxtPercent().getText();
        if (name.isEmpty() || perText.isEmpty() ) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int per = Integer.parseInt(perText); 
        try {
             if (per < 0 || per > 100) {
                 JOptionPane.showMessageDialog(view, "Giá trị phần trăm phải nằm trong khoảng từ 0 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(view, "Giá trị phần trăm phải là một số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return;
        }

        PreparedStatement stmt = null;
        try {
            String query = "INSERT INTO DGG (sale_name,percent) VALUES (?,?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, per);
           
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(view, "Thêm đợt giảm giá thành công!");
                loadDis();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm đợt giảm giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDis() {
        String name = view.getTxtName().getText();
        String perText = view.getTxtPercent().getText();

        if (name.isEmpty() ||  perText.isEmpty() ) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int per = Integer.parseInt(perText); 
        try {
             if (per < 0 || per > 100) {
                 JOptionPane.showMessageDialog(view, "Giá trị phần trăm phải nằm trong khoảng từ 0 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(view, "Giá trị phần trăm phải là một số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
             return;
        }
        // Lấy ID của khách hàng cần cập nhật từ bảng
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn đợt giảm giá để cập nhật!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String disId = view.getTable().getValueAt(selectedRow, 0).toString(); // Lấy ID của nhân viên đã chọn

        PreparedStatement stmt = null;
        try {
            String query = "UPDATE DGG SET sale_name = ?, percent=? WHERE sale_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, per);
            stmt.setString(3,disId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật đợt giảm giá thành công!");
                loadDis();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật đợt giảm giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteDis(){
        int selectedRow=view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn đợt giảm giá để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String disId = view.getTable().getValueAt(selectedRow, 0).toString(); // Lấy ID của nhân viên đã chọn
        PreparedStatement stmt = null;
        try {
            String query = "Delete From DGG WHERE sale_id = ?";
            stmt = connection.prepareStatement(query);

            stmt.setString(1, disId); // Gửi ID nhân viên để xác định bản ghi cần xóa

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(view, "Cập nhật đợt giảm giá thành công!");
                loadDis(); // Tải lại danh sách nhân viên sau khi cập nhật
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa đợt giảm giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        view.getTxtPercent().setText("");
    }
}

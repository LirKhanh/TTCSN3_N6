package views.seller;

import controllers.seller.ManageCustomerController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ManageCustomerUI extends JFrame {

    private final MenuSellerUI menuEmp;
    private final JButton btnAddCustomer, btnUpdateCustomer;
    private final JLabel  lblName;
    private final JLabel lblPhone;
    private final JLabel lblAddress;
    private final JLabel lblFS_point;
    private final JTextField txtName;
    private final JTextField txtPhone;
    private final JTextField txtAddress;
    private final JTextField txtFS_point;
    private DefaultTableModel model;
    private final JScrollPane scrollPane;
    private JTable table;
    private final String dbURL = "jdbc:mysql://localhost:3306/fs";
    private final String username = "root";
    private final String password = "123456abc!";
    public ManageCustomerUI(MenuSellerUI menuEmp) {
        this.menuEmp = menuEmp;

        setTitle("Manage Color");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        btnAddCustomer = new JButton("Tạo khách hàng mới");
        btnAddCustomer.setBounds(20, 20, 140, 50);
        add(btnAddCustomer);

        btnUpdateCustomer = new JButton("Cập nhật thông tin khách hàng");
        btnUpdateCustomer.setBounds(180, 20, 140, 50);
        add(btnUpdateCustomer);

        lblName = new JLabel("Tên khách hàng:");
        lblName.setBounds(340, 60, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(460, 60, 200, 25);
        add(txtName);
        
        lblPhone = new JLabel("Số điện thoại:");
        lblPhone.setBounds(340, 100, 100, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(460, 100, 200, 25);
        add(txtPhone);
        
        lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setBounds(340, 140, 100, 25);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(460, 140, 200, 25);
        add(txtAddress);

        lblFS_point = new JLabel("Fs point:");
        lblFS_point.setBounds(340, 180, 100, 25);
        add(lblFS_point);

        txtFS_point = new JTextField();
        txtFS_point.setBounds(460, 180, 200, 25);
        add(txtFS_point);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Address");
        tableModel.addColumn("FS point");
        JTable table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 220, 750, 400);
        add(scrollPane);
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            if (conn != null) {
                String query = "SELECT * FROM kh"; // Chỉnh sửa bảng và cột
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("cus_id");
                    String name = rs.getString("cus_name");
                    String phone = rs.getString("cus_phone");
                    String address = rs.getString("address");
                    int fs_pont = rs.getInt("fs_point");
                    // Thêm dữ liệu vào bảng
                    tableModel.addRow(new Object[]{id, name, phone, address, fs_pont});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addWindowListener(new WindowCloseListener());

        ManageCustomerController controller = new ManageCustomerController(this);

        btnAddCustomer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtName.getText();
                String name = txtName.getText();
                String phone = txtPhone.getText();
                String address = txtAddress.getText();
                int fs_pont = Integer.parseInt(txtFS_point.getText());

                // Thêm khách hàng vào cơ sở dữ liệu
                try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
                    if (conn != null) {
                        String query = "INSERT INTO kh (cus_id, cus_name, cus_phone, address, fs_point) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, id);
                        stmt.setString(2, name);
                        stmt.setString(3, phone);
                        stmt.setString(4, address);
                        stmt.setInt(5, fs_pont);

                        int rowsInserted = stmt.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(table, "Thêm khách hàng thành công!");
                            tableModel.addRow(new Object[]{"KH_"+id, name, phone, address, fs_pont});
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(table, "Lỗi khi thêm khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuEmp.setVisible(true);
        }
    }
}

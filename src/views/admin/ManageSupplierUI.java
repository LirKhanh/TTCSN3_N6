
package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageStaffController;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class ManageStaffUI extends JFrame {
    private MenuAdminUI menuAd;

    private JLabel lblMaNhanVien, lblTenNhanVien, lblTaiKhoan, lblMatKhau, lblStatNhanVien;
    private JTextField jtfMaNhanVien, jtfTenNhanVien, jtfTaiKhoan, jtfMatKhau, jtfStat;
    private JButton btnAddStaff, btnUpdateStaff, btnDeleteStaff, btnRe_enter;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageStaffUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Quản lý nhân viên");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(SetIconUtil.getIcon().getImage());
        setLayout(null);

        lblTenNhanVien = new JLabel("Tên nhân viên: ");
        lblStatNhanVien = new JLabel("Vai trò: ");
        lblTaiKhoan = new JLabel("Tài khoản: ");
        lblMatKhau = new JLabel("Mật khẩu: ");

        jtfTenNhanVien = new JTextField();
        jtfStat = new JTextField();
        jtfTaiKhoan = new JTextField();
        jtfMatKhau = new JTextField();

        btnAddStaff = new JButton("Thêm");
        btnUpdateStaff = new JButton("Sửa");
        btnDeleteStaff = new JButton("Xóa");
        btnRe_enter = new JButton("Nhập lại");


        lblTenNhanVien.setBounds(50, 50, 100, 30);
        lblStatNhanVien.setBounds(50, 100, 100, 30);
        lblTaiKhoan.setBounds(50, 150, 100, 30);
        lblMatKhau.setBounds(50, 200, 100, 30);


        jtfTenNhanVien.setBounds(150, 50, 200, 30);
        jtfStat.setBounds(150, 100, 200, 30);
        jtfTaiKhoan.setBounds(150, 150, 200, 30);
        jtfMatKhau.setBounds(150, 200, 200, 30);


        btnAddStaff.setBounds(50, 250, 100, 30);
        btnUpdateStaff.setBounds(180, 250, 100, 30);
        btnDeleteStaff.setBounds(310, 250, 100, 30);
        btnRe_enter.setBounds(440, 250, 100, 30);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 300, 700, 200);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);

        String[] name = {
                "Mã nhân viên", "Tên nhân viên", "Vai trò", "Tài khoản", "Mật khẩu"
        };
        tableModel = new DefaultTableModel(name, 0);
        table.setModel(tableModel);

        // không cho phép chỉnh sửa dữ liệu trên bảng
        table.setDefaultEditor(Object.class, null);


        add(lblTenNhanVien);
        add(lblStatNhanVien);
        add(lblTaiKhoan);
        add(lblMatKhau);
        add(jtfStat);
        add(jtfTenNhanVien);
        add(jtfTaiKhoan);
        add(jtfMatKhau);
        add(btnAddStaff);
        add(btnUpdateStaff);
        add(btnDeleteStaff);
        add(btnRe_enter);
        add(scrollPane);


        addWindowListener(new WindowCloseListener());
        ManageStaffController controller = new ManageStaffController(this);
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuAd.setVisible(true);
        }
    }

    // Add listener for "Tạo nhân viên mới" button
    public void addStaffButtonListener(ActionListener addStaffButton) {
        btnAddStaff.addActionListener(addStaffButton);
    }

    //Add listener for "Cập nhật thông tin nhân viên" button
    public void updateStaffButtonListener(ActionListener updateStaffButton) {
        btnUpdateStaff.addActionListener(updateStaffButton);
    }

    public void deleteStaffButtonListener(ActionListener deleteStaffButton) {
        btnDeleteStaff.addActionListener(deleteStaffButton);
    }

    public void re_enterStaffButtonListener(ActionListener deleteStaffButton) {
        btnRe_enter.addActionListener(deleteStaffButton);
    }

    // Getters for form inputs and table model
    public JButton getBtnAddCustomer() {
        return btnAddStaff;
    }

    public JTextField getTxtName() {
        return jtfTenNhanVien;
    }

    public JTextField getTxtStat() {
        return jtfStat;
    }

    public JTextField getTxtAccount() {
        return jtfTaiKhoan;
    }

    public JTextField getTxtPassword() {
        return jtfMatKhau;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    public JTable getTable() {
        return table;  // Trả về đối tượng JTable
    }
}

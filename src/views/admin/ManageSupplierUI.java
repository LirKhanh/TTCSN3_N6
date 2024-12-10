package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageSupplierController;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class ManageSupplierUI extends JFrame {
    private MenuAdminUI menuAd;

    private JLabel lblTen, lblSDT;
    private JTextField jtfTen, jtfSDT;
    private JButton btnThem, btnSua, btnXoa, btnNhapLai;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageSupplierUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Quản lý nhà cung cấp");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(SetIconUtil.getIcon().getImage());

        lblTen = new JLabel("Tên nhà cung cấp: ");
        lblTen.setBounds(20, 30, 150, 30);

        lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setBounds(20, 80, 150, 30);

        jtfTen = new JTextField();
        jtfTen.setBounds(180, 30, 200, 30);

        jtfSDT = new JTextField();
        jtfSDT.setBounds(180, 80, 200, 30);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnNhapLai = new JButton("Nhập lại");

        btnThem.setBounds(20, 150, 100, 30);
        btnSua.setBounds(150, 150, 100, 30);
        btnXoa.setBounds(280, 150, 100, 30);
        btnNhapLai.setBounds(410, 150, 100, 30);

        btnNhapLai.addActionListener(e -> {
            jtfTen.setText("");
            jtfSDT.setText("");
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 550, 220);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);

        String[] columnNames = {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(tableModel);

        add(lblTen);
        add(lblSDT);
        add(jtfTen);
        add(jtfSDT);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(btnNhapLai);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());
        ManageSupplierController controller = new ManageSupplierController(this);
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuAd.setVisible(true);
        }
    }

    public void addButtonListener(ActionListener addButton) {
        btnThem.addActionListener(addButton);
    }

    public void updateButtonListener(ActionListener updateButton) {
        btnSua.addActionListener(updateButton);
    }

    public void delButtonListener(ActionListener delButton) {
        btnXoa.addActionListener(delButton);
    }

    public JTextField getTxtTenNCC() {
        return jtfTen;
    }

    public JTextField getTxtSDT() {
        return jtfSDT;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    public JTable getTable() {
        return table;
    }
}

package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageColorController;
import controllers.admin.ManageSizeController;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class ManageSizeUI extends JFrame {
    private MenuAdminUI menuAd;

    private JLabel lblTen;
    private JTextField jtfTen;
    private JButton btnThem, btnSua, btnXoa, btnNhapLai;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageSizeUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Quản lý kích cỡ");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(SetIconUtil.getIcon().getImage());

        lblTen = new JLabel("Tên kích cỡ: ");
        lblTen.setBounds(20, 50, 100, 30);

        jtfTen = new JTextField();
        jtfTen.setBounds(120, 50, 200, 30);

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
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 550, 220);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);

        String[] name = {
                "Mã kích cỡ", "Tên kích cỡ"
        };
        tableModel = new DefaultTableModel(name, 0);
        table.setModel(tableModel);

        add(lblTen);
        add(jtfTen);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(btnNhapLai);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());
        ManageSizeController controller = new ManageSizeController(this);
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

    public JTextField getTxtName() {
        return jtfTen;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    public JTable getTable() {
        return table;
    }
}


package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageProductController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageProductUI extends JFrame {
    private MenuAdminUI menuAd;

    private JLabel lblTenHang, lblNhaCungCap, lblLoaiHang, lblViTri, lblGia, lblNhanHang;
    private JTextField jtfTenHang, jtfViTri, jtfGia, jtfNhanHang;
    private JComboBox<String> jcbNCC, jcbLoaiHang;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageProductUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Quản lý sản phẩm");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(SetIconUtil.getIcon().getImage());

        lblTenHang = new JLabel("Tên sản phẩm: ");
        lblTenHang.setBounds(20, 50, 120, 20);
        jtfTenHang = new JTextField();
        jtfTenHang.setBounds(150, 50, 200, 20);

        lblNhaCungCap = new JLabel("Nhà cung cấp: ");
        lblNhaCungCap.setBounds(20, 80, 120, 20);
        jcbNCC = new JComboBox<>();
        jcbNCC.setBounds(150, 80, 200, 20);
        jcbNCC.setBackground(Color.WHITE);

        lblLoaiHang = new JLabel("Loại hàng: ");
        lblLoaiHang.setBounds(20, 110, 120, 20);
        jcbLoaiHang = new JComboBox<>();
        jcbLoaiHang.setBounds(150, 110, 200, 20);
        jcbLoaiHang.setBackground(Color.WHITE);

        lblViTri = new JLabel("Vị trí: ");
        lblViTri.setBounds(20, 140, 120, 20);
        jtfViTri = new JTextField();
        jtfViTri.setBounds(150, 140, 200, 20);

        lblGia = new JLabel("Giá: ");
        lblGia.setBounds(20, 170, 120, 20);
        jtfGia = new JTextField();
        jtfGia.setBounds(150, 170, 200, 20);

        lblNhanHang = new JLabel("Nhãn hàng: ");
        lblNhanHang.setBounds(20, 200, 120, 20);
        jtfNhanHang = new JTextField();
        jtfNhanHang.setBounds(150, 200, 200, 20);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        btnThem.setBounds(20, 250, 100, 30);
        btnSua.setBounds(150, 250, 100, 30);
        btnXoa.setBounds(280, 250, 100, 30);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 300, 750, 220);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setViewportView(table);

        String[] columns = {
                "Mã sản phẩm", "Tên sản phẩm", "Mã nhà cung cấp", "Mã loại hàng",
                "Vị trí", "Giá", "Nhãn hàng"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table.setModel(tableModel);

        add(lblTenHang);
        add(lblNhaCungCap);
        add(lblLoaiHang);
        add(lblViTri);
        add(lblGia);
        add(lblNhanHang);
        add(jtfTenHang);
        add(jcbNCC);
        add(jcbLoaiHang);
        add(jtfViTri);
        add(jtfGia);
        add(jtfNhanHang);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());

        ManageProductController controller = new ManageProductController(this);
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

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    public JTable getTable() {
        return table;
    }

    public void setJcbNCC(String item) {
        jcbNCC.addItem(item);
    }

    public void setJcbLoaiHang(String item) {
        jcbLoaiHang.addItem(item);
    }

    public JComboBox<String> getJcbNCC() {
        return jcbNCC;
    }

    public JComboBox<String> getJcbLoaiHang() {
        return jcbLoaiHang;
    }

    public JTextField getTxtTenHang() {
        return jtfTenHang;
    }

    public JTextField getTxtViTri() {
        return jtfViTri;
    }

    public JTextField getTxtGia() {
        return jtfGia;
    }

    public JTextField getTxtNhanHang() {
        return jtfNhanHang;
    }
}

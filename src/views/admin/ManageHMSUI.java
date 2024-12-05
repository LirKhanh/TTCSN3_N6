package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageHMSController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageHMSUI extends JFrame {
    private MenuAdminUI menuAd;

    private JLabel lblProduct, lblColor, lblSize, lblQuantity;
    private JTextField jtfQuantity;
    private JComboBox jcbProduct, jcbColor, jcbSize;
    private JButton btnThem, btnSua, btnXoa, btnNhapLai;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageHMSUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Quản lý hàng màu size");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(SetIconUtil.getIcon().getImage());

        lblProduct = new JLabel("Tên hàng: ");
        lblProduct.setBounds(20, 20, 100, 20);
        jcbProduct = new JComboBox<String>();
        jcbProduct.setBounds(120, 20, 100, 20);
        jcbProduct.setBackground(Color.white);

        lblColor = new JLabel("Màu: ");
        lblColor.setBounds(260, 20, 100, 20);
        jcbColor = new JComboBox<String>();
        jcbColor.setBounds(360, 20, 100, 20);
        jcbColor.setBackground(Color.white);

        lblSize = new JLabel("Kích cỡ: ");
        lblSize.setBounds(20, 50, 100, 20);
        jcbSize = new JComboBox<String>();
        jcbSize.setBounds(120, 50, 100, 20);
        jcbSize.setBackground(Color.white);

        lblQuantity = new JLabel("Số lượng:");
        lblQuantity.setBounds(20, 80, 100, 20);
        jtfQuantity = new JTextField();
        jtfQuantity.setBounds(120, 80, 100, 20);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnNhapLai = new JButton("Nhập lại");


        btnThem.setBounds(20, 150, 100, 30);
        btnSua.setBounds(150, 150, 100, 30);
        btnXoa.setBounds(280, 150, 100, 30);
        btnNhapLai.setBounds(410, 150, 100, 30);
        btnNhapLai.addActionListener(e -> {
            jtfQuantity.setText("");
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 550, 220);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setViewportView(table);

        String[] name = {
                "Mã HMS", "Tên hàng", "Màu", "Size",
                "Số lượng","Giá gốc"
        };
        tableModel = new DefaultTableModel(name, 0);
        table.setModel(tableModel);

        add(lblProduct);
        add(lblColor);
        add(lblSize);
        add(lblQuantity);
        add(jtfQuantity);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(btnNhapLai);
        add(scrollPane);
        add(jcbColor);
        add(jcbSize);
        add(jcbProduct);
        addWindowListener(new WindowCloseListener());

        ManageHMSController controller = new ManageHMSController(this);
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
    
    public void setJcbProduct(String jcbProductItem) {
        jcbProduct.addItem(jcbProductItem);
    }

    public void setJcbColor(String jcbColorItem) {
        jcbColor.addItem(jcbColorItem);
    }

    public void setJcbSize(String jcbSizeItem) {
        jcbSize.addItem(jcbSizeItem);
    }

    public JComboBox<String> getJcbProduct() {
        return jcbProduct;
    }

    public JComboBox<String> getJcbColor() {
        return jcbColor;
    }

    public JComboBox<String> getJcbSize() {
        return jcbSize;
    }


    public String getJtfQuantity() {
        return jtfQuantity.getText();
    }
}
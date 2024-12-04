package views.admin;

import Utils.SetIconUtil;
import controllers.admin.ManageHMSController;
import controllers.admin.ManageSaleProductController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageSaleProductUI extends JFrame {
    private MenuAdminUI menuAd;

    private JLabel lblHMS, lblSale;
    private JComboBox jcbHMS, jcbSale;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageSaleProductUI(MenuAdminUI menuAd) {
        this.menuAd = menuAd;

        setTitle("Quản lý hàng bán ra");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(SetIconUtil.getIcon().getImage());

        lblHMS = new JLabel("Mã HMS: ");
        lblHMS.setBounds(20, 20, 100, 20);
        jcbHMS = new JComboBox<String>();
        jcbHMS.setBounds(120, 20, 200, 20);
        jcbHMS.setBackground(Color.white);

        lblSale = new JLabel("Đợt giảm giá: ");
        lblSale.setBounds(20, 50, 100, 20);
        jcbSale = new JComboBox<String>();
        jcbSale.setBounds(120, 50, 150, 20);
        jcbSale.setBackground(Color.white);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        btnThem.setBounds(20, 150, 100, 30);
        btnSua.setBounds(150, 150, 100, 30);
        btnXoa.setBounds(280, 150, 100, 30);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 550, 220);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setViewportView(table);

        String[] name = {
                "Mã HMS", "Tên hàng", "Màu", "Size",
                "Số lượng","Tên đợt giảm giá","Giá gốc", "Giá giảm"
        };
        tableModel = new DefaultTableModel(name, 0);
        table.setModel(tableModel);

        add(jcbHMS);
        add(jcbSale);
        add(lblHMS);
        add(lblSale);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(scrollPane);
        add(jcbSale);
        addWindowListener(new WindowCloseListener());

        ManageSaleProductController controller = new ManageSaleProductController(this);
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

    public void setJcbHMS(String jcbHMSItem) {
        jcbHMS.addItem(jcbHMSItem);
    }


    public void setJcbSale(String jcbSaleItem) {
        jcbSale.addItem(jcbSaleItem);
    }

    public JComboBox<String> getJcbHMS() {
        return jcbHMS;
    }

    public JComboBox<String> getJcbSale() {
        return jcbSale;
    }


}
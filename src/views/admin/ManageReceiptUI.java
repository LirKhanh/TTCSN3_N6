package views.admin;

import Utils.SetIconUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageReceiptUI extends JFrame{
    private MenuAdminUI menuAd;

    private JLabel lblProduct, lblColor,lblSize,lblSale,lblQuantity;
    private JTextField jtfQuantity;
    private JComboBox jcbProduct, jcbColor, jcbSize;
    private JButton btnThem, btnSua, btnXoa, btnNhapLai;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ManageReceiptUI(MenuAdminUI menuAd){
        this.menuAd = menuAd;

        setTitle("Quản lý hóa đơn");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(SetIconUtil.getIcon().getImage());

        lblProduct = new JLabel("Tên hàng: ");
        lblProduct.setBounds(20, 20, 100, 30);
        jcbProduct = new JComboBox<String>();
        jcbProduct.setBounds(120,20,100,30);

        lblColor = new JLabel("Màu: ");
        lblColor.setBounds(240, 20, 100, 30);
        jcbColor = new JComboBox<String>();
        jcbColor.setBounds(340,20,100,30);

        lblSize = new JLabel("Kích cỡ: ");
        lblSize.setBounds(460, 20, 100, 30);
        jcbSize = new JComboBox<String>();
        jcbSize.setBounds(560,20,100,30);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnNhapLai = new JButton("Nhập lại");


        btnThem.setBounds(20, 150, 100, 30);
        btnSua.setBounds(150, 150, 100, 30);
        btnXoa.setBounds(280, 150, 100, 30);
        btnNhapLai.setBounds(410, 150, 100, 30);
        btnNhapLai.addActionListener(e -> {
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 550, 220);
        table = new JTable();
        scrollPane.add(table);
        scrollPane.setViewportView(table);

        String[] name = {
                "Mã HMS", "Tên hàng", "Màu", "Size","Số lượng "
        };
        tableModel = new DefaultTableModel(name, 0);
        table.setModel(tableModel);

        add(lblProduct);
        add(btnThem);
        add(btnSua);
        add(btnXoa);
        add(btnNhapLai);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());
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
}
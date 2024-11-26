package views.seller;

import controllers.seller.CreateReceiptController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class CreateReceiptUI extends JFrame {

    private MenuSellerUI menuEmp;
    private JButton btnAddProduct, btnDelProduct, btnCreateReceipt, btnPrintReceipt;
    private JLabel lblEmp, lblPayMethod, lblCustomer;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;
    private JComboBox<String> cmbEmp, cmbPayMethod, cmbCustomer;

    public CreateReceiptUI(MenuSellerUI menuEmp) {
        this.menuEmp = menuEmp;

        setTitle("Tạo đơn hàng");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        btnAddProduct = new JButton("Thêm hàng");
        btnAddProduct.setBounds(20, 20, 100, 40);
        add(btnAddProduct);

        btnDelProduct = new JButton("Xóa hàng");
        btnDelProduct.setBounds(140, 20, 100, 40);
        add(btnDelProduct);

        String[] columnNames = {
                "Tên hàng", "Kích cỡ", "Màu", "Giá tiền", "Số lượng", "Tổng tiền"
        };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 340, 300);
        add(scrollPane);

        lblEmp = new JLabel("Nhân viên:");
        lblEmp.setBounds(140, 400, 100, 20);
        add(lblEmp);

        lblPayMethod = new JLabel("PTTT:");
        lblPayMethod.setBounds(20, 400, 100, 20);
        add(lblPayMethod);

        lblCustomer = new JLabel("Khách hàng:");
        lblCustomer.setBounds(260, 400, 100, 20);
        add(lblCustomer);

        cmbPayMethod = new JComboBox<String>();
        cmbPayMethod.setBounds(20, 430, 100, 20);
        add(cmbPayMethod);

        cmbEmp = new JComboBox<String>();
        cmbEmp.setBounds(140, 430, 100, 20);
        add(cmbEmp);

        cmbCustomer = new JComboBox<String>();
        cmbCustomer.setBounds(260, 430, 100, 20);
        add(cmbCustomer);

        btnCreateReceipt = new JButton("Tạo đơn hàng");
        btnCreateReceipt.setBounds(100, 500, 120, 40);
        add(btnCreateReceipt);

        btnPrintReceipt = new JButton("In hóa đơn");
        btnPrintReceipt.setBounds(240, 500, 120, 40);
        add(btnPrintReceipt);

        addWindowListener(new WindowCloseListener());

        CreateReceiptController controller = new CreateReceiptController(this);
        controller.loadComboBoxPTTT();
        controller.loadComboBoxStaff();
        controller.loadComboBoxCustomer();
    }


    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            menuEmp.setVisible(true);
        }
    }

    public void createReceiptListener(java.awt.event.ActionListener createReceiptButton) {
        btnCreateReceipt.addActionListener(createReceiptButton);
    }
    public void moveAddProductListener(java.awt.event.ActionListener addProductButton) {
        btnAddProduct.addActionListener(addProductButton);
    }

    public void printReceiptListener(java.awt.event.ActionListener printReceiptButton) {
        btnPrintReceipt.addActionListener(printReceiptButton);
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public String getCbBoxPTTT() {
        return cmbPayMethod.getSelectedItem().toString();
    }

    public String getCbBoxStaff() {
        return cmbEmp.getSelectedItem().toString();
    }

    public String getCbBoxCustomer() {
        return cmbCustomer.getSelectedItem().toString();
    }

    public void setCbBoxPTTT(String PTTT) {
        cmbPayMethod.addItem(PTTT);
    }

    public void setCbBoxStaff(String Staff) {
        cmbEmp.addItem(Staff);
    }

    public void setCbBoxCustomer(String Customer) {
        cmbCustomer.addItem(Customer);
    }
}

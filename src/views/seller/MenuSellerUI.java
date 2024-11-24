package views.seller;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controllers.seller.MenuSellerController;

public class MenuSellerUI extends JFrame {

    private JButton btnCreateReceipt, btnSearchProduct, btnManageCustomer;
    private JLabel lblSearch, lblMessage;
    private JTextField txtSearch;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;

    public MenuSellerUI() {        
        setTitle("Seller Menu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        lblSearch = new JLabel("Từ khóa:");
        lblSearch.setBounds(20, 90, 70, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(100, 90, 200, 25);
        add(txtSearch);

        btnCreateReceipt = new JButton("Tạo đơn hàng");
        btnCreateReceipt.setBounds(20, 20, 140, 50);
        add(btnCreateReceipt);

        btnSearchProduct = new JButton("Tìm kiếm sản phẩm");
        btnSearchProduct.setBounds(180, 20, 140, 50);
        add(btnSearchProduct);

        btnManageCustomer = new JButton("Quản lý khách hàng");
        btnManageCustomer.setBounds(340, 20, 140, 50);
        add(btnManageCustomer);

        String[] columnNames = {
            "Mã HMS", "Tên sản phẩm", "Màu", "Size", "Loại",
            "Giá", "Số lượng", "Nhà cung cấp", "Vị trí"
        };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 140, 750, 400);
        add(scrollPane);


        MenuSellerController controller = new MenuSellerController(this);

    }

    public void addCreateReceiptListener(java.awt.event.ActionListener createButton) {
        btnCreateReceipt.addActionListener(createButton);
    }

    public void addSearchListener(java.awt.event.ActionListener searchButton) {
        btnSearchProduct.addActionListener(searchButton);
    }
    
    public void addManageCusListener(java.awt.event.ActionListener manageCusButton) {
        btnManageCustomer.addActionListener(manageCusButton);
    }

    public String getSearchText() {
        return txtSearch.getText();
    }

    public DefaultTableModel getTableModel() {
        return model;
    }


}

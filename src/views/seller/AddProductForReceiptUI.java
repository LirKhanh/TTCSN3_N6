package views.seller;

import controllers.seller.AddProductForReceiptController;
import views.admin.ManageCustomerUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddProductForReceiptUI extends JFrame {
    private CreateReceiptUI receiptUI;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton btnSearchProduct;
    private JLabel lblSearch;
    private JTextField txtSearch;

    public AddProductForReceiptUI(CreateReceiptUI receiptUI) {
        this.receiptUI = receiptUI;

        setTitle("Thêm hàng vào hóa đơn");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        lblSearch = new JLabel("Từ khóa:");
        lblSearch.setBounds(20, 20, 70, 25);
        add(lblSearch);

        btnSearchProduct = new JButton("Tìm kiếm sản phẩm");
        btnSearchProduct.setBounds(320, 20, 150, 25);
        add(btnSearchProduct);

        txtSearch = new JTextField();
        txtSearch.setBounds(100, 20, 200, 25);
        add(txtSearch);

        String[] columnNames = {
                "Mã HMS", "Tên sản phẩm", "Màu", "Size", "Loại","Đợt giảm giá",
                "Giá", "Số lượng", "Nhà cung cấp", "Vị trí"
        };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 90, 600, 200);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());
        AddProductForReceiptController controller = new AddProductForReceiptController(this);
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            receiptUI.setVisible(true);
        }
    }

    public void addSearchListener(java.awt.event.ActionListener searchButton) {
        btnSearchProduct.addActionListener(searchButton);
    }

    public String getSearchText() {
        return txtSearch.getText();
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }


}


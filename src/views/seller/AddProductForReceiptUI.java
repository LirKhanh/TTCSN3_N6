package views.seller;

import Utils.SetIconUtil;
import controllers.seller.AddProductForReceiptController;
import controllers.seller.CreateReceiptController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
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
        setIconImage(SetIconUtil.getIcon().getImage());
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
                "Mã HMS", "Tên sản phẩm", "Màu", "Size", "Loại", "Đợt giảm giá",
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

    public void addSearchListener(ActionListener searchButton) {
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

    public CreateReceiptUI getReceiptUI() {return receiptUI;}
}


package views.seller;

import controllers.seller.ManageCustomerController;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class ManageCustomerUI extends JFrame {

    private MenuSellerUI menuEmp;
    private JButton btnAddCustomer, btnUpdateCustomer;
    private JLabel lblName, lblPhone, lblAddress;
    private JTextField txtName, txtPhone, txtAddress;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;

    public ManageCustomerUI(MenuSellerUI menuEmp) {
        this.menuEmp = menuEmp;

        setTitle("Manage Color");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        btnAddCustomer = new JButton("Tạo khách hàng mới");
        btnAddCustomer.setBounds(20, 20, 140, 50);
        add(btnAddCustomer);

        btnUpdateCustomer = new JButton("Cập nhật thông tin khách hàng");
        btnUpdateCustomer.setBounds(180, 20, 140, 50);
        add(btnUpdateCustomer);

        lblName = new JLabel("Tên khách hàng:");
        lblName.setBounds(340, 20, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(460, 20, 200, 25);
        add(txtName);
        
        lblPhone = new JLabel("Số điện thoại:");
        lblPhone.setBounds(340, 60, 100, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(460, 60, 200, 25);
        add(txtPhone);
        
        lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setBounds(340, 100, 100, 25);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(460, 100, 200, 25);
        add(txtAddress);
        
        String[] columnNames = {
            "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "FS Point"
        };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 140, 750, 400);
        add(scrollPane);

        addWindowListener(new WindowCloseListener());

        ManageCustomerController controller = new ManageCustomerController(this);
    }

    private class WindowCloseListener extends WindowAdapter {

        @Override
        public void windowClosed(WindowEvent e) {
            menuEmp.setVisible(true);
        }
    }
}

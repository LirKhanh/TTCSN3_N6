package views.seller;

import controllers.seller.ManageCustomerController;

import javax.swing.*;
import java.awt.event.ActionListener;
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

        setTitle("Quản lý khách hàng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Buttons and Labels for customer details
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
        lblPhone.setBounds(340, 65, 100, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(460, 65, 200, 25);
        add(txtPhone);

        lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setBounds(340, 110, 100, 25);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(460, 110, 200, 25);
        add(txtAddress);

        // Table to display customers
        String[] columnNames = {"ID", "Name", "Phone", "Address", "FS point"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 160, 750, 380);
        add(scrollPane);

        // Handle window close event
        addWindowListener(new WindowCloseListener());
        ManageCustomerController controller = new ManageCustomerController(this);
    }

    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            if (menuEmp != null) {
                menuEmp.setVisible(true);
            }
        }
    }

    // Add listener for "Tạo khách hàng mới" button
    public void addCustomerButtonListener(ActionListener addCustomerButton) {
        btnAddCustomer.addActionListener(addCustomerButton);
    }

    // Getters for form inputs and table model
    public JButton getBtnAddCustomer() {
        return btnAddCustomer;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtPhone() {
        return txtPhone;
    }

    public JTextField getTxtAddress() {
        return txtAddress;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }
}

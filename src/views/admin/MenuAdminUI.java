package views.admin;

import controllers.admin.MenuAdminController;
import javax.swing.*;

public class MenuAdminUI extends JFrame {
    private JButton btnManageColor, btnManageSize,btnManageCustomer,btnManageCategory,btnManagePayment,
            btnManageStaff,btnManageDiscount,btnManageSupplier;
    public MenuAdminUI() {
        
        setTitle("ADMIN Menu");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        

        btnManageColor = new JButton("Quản lý màu");
        btnManageColor.setBounds(100, 100, 230, 30);
        btnManageSize = new JButton("Quản lý kích cỡ");
        btnManageSize.setBounds(100, 150, 230, 30);
        btnManageCustomer = new JButton("Quản lý khách hàng");
        btnManageCustomer.setBounds(100, 200, 230, 30);
        btnManageCategory = new JButton("Quản lý loại hàng");
        btnManageCategory.setBounds(100, 250, 230, 30);
        btnManagePayment = new JButton("Quản lý phương thức thanh toán");
        btnManagePayment.setBounds(100, 300, 230, 30);
        btnManageStaff = new JButton("Quản lý nhân viên");
        btnManageStaff.setBounds(100, 350, 230, 30);
        btnManageDiscount = new JButton("Quản lý đợt giảm giá");
        btnManageDiscount.setBounds(100, 400, 230, 30);
        btnManageSupplier = new JButton("Quản lý nhà cung cấp");
        btnManageSupplier.setBounds(100, 450, 230, 30);
        
        
        add(btnManageColor);
        add(btnManageSize);
        add(btnManageCustomer);
        add(btnManageCategory);
        add(btnManagePayment);
        add(btnManageStaff);
        add(btnManageDiscount);
        add(btnManageSupplier);
        
        
        MenuAdminController controller = new MenuAdminController(this);

    }

    
    public void addManageColorListener(java.awt.event.ActionListener manageColorButton) {
        btnManageColor.addActionListener(manageColorButton);
    }
    public void addManageCustomerListener(java.awt.event.ActionListener manageCustomerButton) {
        btnManageCustomer.addActionListener(manageCustomerButton);
    }
    public void addManageSizeListener(java.awt.event.ActionListener manageSizeButton) {
        btnManageSize.addActionListener(manageSizeButton);
    }
    public void addManageCategoryListener(java.awt.event.ActionListener manageCategoryButton) {
        btnManageCategory.addActionListener(manageCategoryButton);
    }
    public void addManageStaffListener(java.awt.event.ActionListener manageStaffButton) {
        btnManageStaff.addActionListener(manageStaffButton);
    }
    public void addManagePaymentListener(java.awt.event.ActionListener managePaymentButton) {
        btnManagePayment.addActionListener(managePaymentButton);
    }
    public void addManageDiscountListener(java.awt.event.ActionListener manageDiscountButton) {
        btnManageDiscount.addActionListener(manageDiscountButton);
    }
    public void addManageSupplierListener(java.awt.event.ActionListener manageSupplierButton) {
        btnManageSupplier.addActionListener(manageSupplierButton);
    }
    
    
}

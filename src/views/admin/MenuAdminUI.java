package views.admin;
import java.awt.Color;

import Utils.SetIconUtil;
import controllers.admin.MenuAdminController;

import javax.swing.*;

public class MenuAdminUI extends JFrame {
    private JButton btnManageColor, btnManageSize, btnManageCustomer, btnManageCategory, btnManagePayment,
            btnManageStaff, btnManageDiscount, btnManageSupplier, btnManageProduct, btnManageHms, btnManageSaleProduct,btnManageReceipt;

    public MenuAdminUI() {
        setTitle("Menu ADMIN");
        setSize(440, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(SetIconUtil.getIcon().getImage());
        setLayout(null);
        this.setBackground(new Color(191,189,191));

        // Nhóm 1
        btnManageCategory = new JButton("Quản lý loại hàng");
        btnManageCategory.setBounds(100, 20, 230, 30);
        btnManageCategory.setBackground(new Color(217,61,89));
        btnManageCategory.setForeground(Color.WHITE);

        btnManageSupplier = new JButton("Quản lý nhà cung cấp");
        btnManageSupplier.setBounds(100, 60, 230, 30);
        btnManageSupplier.setBackground(new Color(217,61,89));
        btnManageSupplier.setForeground(Color.WHITE);

        btnManageProduct = new JButton("Quản lý hàng");
        btnManageProduct.setBounds(100, 100, 230, 30);
        btnManageProduct.setBackground(new Color(217,61,89));
        btnManageProduct.setForeground(Color.WHITE);

        // Nhóm 2
        btnManageColor = new JButton("Quản lý màu");
        btnManageColor.setBounds(100, 140, 230, 30);
        btnManageColor.setBackground(new Color(217,7,60));
        btnManageColor.setForeground(Color.WHITE);

        btnManageSize = new JButton("Quản lý kích cỡ");
        btnManageSize.setBounds(100, 180, 230, 30);
        btnManageSize.setBackground(new Color(217,7,60));
        btnManageSize.setForeground(Color.WHITE);

        btnManageHms = new JButton("Quản lý hàng_màu_size");
        btnManageHms.setBounds(100, 220, 230, 30);
        btnManageHms.setBackground(new Color(217,7,60));
        btnManageHms.setForeground(Color.WHITE);

        // Nhóm 3
        btnManageDiscount = new JButton("Quản lý đợt giảm giá");
        btnManageDiscount.setBounds(100, 260, 230, 30);
        btnManageDiscount.setBackground(new Color(220,4,21));
        btnManageDiscount.setForeground(Color.WHITE);

        btnManageSaleProduct = new JButton("Quản lý hàng bán ra");
        btnManageSaleProduct.setBounds(100, 300, 230, 30);
        btnManageSaleProduct.setBackground(new Color(220,4,21));
        btnManageSaleProduct.setForeground(Color.WHITE);

        // Nhóm 4
        btnManageCustomer = new JButton("Quản lý khách hàng");
        btnManageCustomer.setBounds(100, 340, 230, 30);
        btnManageCustomer.setBackground(new Color(140,3,14));
        btnManageCustomer.setForeground(Color.WHITE);

        btnManagePayment = new JButton("Quản lý phương thức thanh toán");
        btnManagePayment.setBounds(100, 380, 230, 30);
        btnManagePayment.setBackground(new Color(140,3,14));
        btnManagePayment.setForeground(Color.WHITE);

        btnManageStaff = new JButton("Quản lý nhân viên");
        btnManageStaff.setBounds(100, 420, 230, 30);
        btnManageStaff.setBackground(new Color(140,3,14));
        btnManageStaff.setForeground(Color.WHITE);

        btnManageReceipt = new JButton("Quản lý hóa đơn");
        btnManageReceipt.setBounds(100, 460, 230, 30);
        btnManageReceipt.setBackground(new Color(140,3,14));
        btnManageReceipt.setForeground(Color.WHITE);

        add(btnManageCategory);
        add(btnManageSupplier);
        add(btnManageProduct);
        add(btnManageColor);
        add(btnManageSize);
        add(btnManageHms);
        add(btnManageDiscount);
        add(btnManageSaleProduct);
        add(btnManageCustomer);
        add(btnManagePayment);
        add(btnManageStaff);
        add(btnManageReceipt);

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

    public void addManageProductListener(java.awt.event.ActionListener manageProductButton) {
        btnManageProduct.addActionListener(manageProductButton);
    }

    public void addManageHms(java.awt.event.ActionListener manageHmsButton) {
        btnManageHms.addActionListener(manageHmsButton);
    }

    public void addManageSaleProduct(java.awt.event.ActionListener manageSaleProductButton) {
        btnManageSaleProduct.addActionListener(manageSaleProductButton);
    }

    public void addManageReceipt(java.awt.event.ActionListener manageReceiptButton) {
        btnManageReceipt.addActionListener(manageReceiptButton);
    }

}

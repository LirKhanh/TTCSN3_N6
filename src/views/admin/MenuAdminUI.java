package views.admin;

import Utils.SetIconUtil;
import controllers.admin.MenuAdminController;

import javax.swing.*;
import java.awt.*;

public class MenuAdminUI extends JFrame {
    private JButton btnManageColor, btnManageSize, btnManageCustomer, btnManageCategory, btnManagePayment,
            btnManageStaff, btnManageDiscount, btnManageSupplier, btnManageHms, btnManageHmsOut, btnManageReceipt;

    public MenuAdminUI() {

        setTitle("Menu ADMIN");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(SetIconUtil.getIcon().getImage());
        setLayout(null);

        btnManageColor = new JButton("Quản lý màu");
        btnManageColor.setBounds(100, 20, 230, 30);
        btnManageSize = new JButton("Quản lý kích cỡ");
        btnManageSize.setBounds(100, 70, 230, 30);
        btnManageCustomer = new JButton("Quản lý khách hàng");
        btnManageCustomer.setBounds(100, 120, 230, 30);
        btnManageCategory = new JButton("Quản lý loại hàng");
        btnManageCategory.setBounds(100, 170, 230, 30);
        btnManagePayment = new JButton("Quản lý phương thức thanh toán");
        btnManagePayment.setBounds(100, 220, 230, 30);
        btnManageStaff = new JButton("Quản lý nhân viên");
        btnManageStaff.setBounds(100, 270, 230, 30);
        btnManageDiscount = new JButton("Quản lý đợt giảm giá");
        btnManageDiscount.setBounds(100, 320, 230, 30);
        btnManageSupplier = new JButton("Quản lý nhà cung cấp");
        btnManageSupplier.setBounds(100, 370, 230, 30);
        btnManageHms = new JButton("Quản lý chi tiết hàng");
        btnManageHms.setBounds(100, 420, 230, 30);
        btnManageHmsOut = new JButton("Quản lý hàng bán ra");
        btnManageHmsOut.setBounds(100, 470, 230, 30);
        btnManageReceipt = new JButton("Quản lý hóa đơn");
        btnManageReceipt.setBounds(100, 520, 230, 30);

        add(btnManageColor);
        add(btnManageSize);
        add(btnManageCustomer);
        add(btnManageCategory);
        add(btnManagePayment);
        add(btnManageStaff);
        add(btnManageDiscount);
        add(btnManageSupplier);
        add(btnManageHms);
        add(btnManageHmsOut);
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

    public void addManageHmsListener(java.awt.event.ActionListener manageHmsButton) {
        btnManageHms.addActionListener(manageHmsButton);
    }

    public void addManageHmsOutListener(java.awt.event.ActionListener manageHmsOutButton) {
        btnManageHmsOut.addActionListener(manageHmsOutButton);
    }
    public void addManageReceiptListener(java.awt.event.ActionListener manageReceiptButton) {
        btnManageReceipt.addActionListener(manageReceiptButton);
    }
}

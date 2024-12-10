package controllers.admin;

import Utils.ConnectJDBCUtil;
import java.sql.*;

import views.admin.*;

public class MenuAdminController {

    private MenuAdminUI view;
    private Connection connection;

    public MenuAdminController(MenuAdminUI view) {
        this.view = view;
        connection = ConnectJDBCUtil.getConnection();
        
        this.view.addManageColorListener(e -> moveManageColor());
        this.view.addManageCustomerListener(e -> moveManageCustomer());
        this.view.addManageSizeListener(e -> moveManageSize());
        this.view.addManageCategoryListener(e -> moveManageCategory());
        this.view.addManageStaffListener(e -> moveManageStaff());
        this.view.addManagePaymentListener(e -> moveManagePayment());
        this.view.addManageDiscountListener(e -> moveManageDiscount());
        this.view.addManageSupplierListener(e -> moveManageSupplier());
        this.view.addManageProductListener(e -> moveManageProduct());
        this.view.addManageHms(e -> moveManageHms());
        this.view.addManageSaleProduct(e -> moveManageSaleProduct());
        this.view.addManageReceipt(e -> moveManageReceipt());
    }

    private void moveManageHms(){
        ManageHMSUI manageHMSUI = new ManageHMSUI(view);
        manageHMSUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageSaleProduct(){
        ManageSaleProductUI manageSaleProductUI = new ManageSaleProductUI(view);
        manageSaleProductUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageReceipt(){
        ManageReceiptUI manageReceiptUI = new ManageReceiptUI(view);
        manageReceiptUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageColor() {
        ManageColorUI colorUI = new ManageColorUI(view);
        colorUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageCustomer() {
        ManageCustomerUI customerUI = new ManageCustomerUI(view);
        customerUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageSize() {
        ManageSizeUI sizeUI = new ManageSizeUI(view);
        sizeUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageCategory() {
        ManageCategoryUI categoryUI = new ManageCategoryUI(view);
        categoryUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageStaff() {
        ManageStaffUI staffUI = new ManageStaffUI(view);
        staffUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManagePayment() {
        ManagePaymentUI paymentUI = new ManagePaymentUI(view);
        paymentUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageDiscount() {
        ManageDiscountUI discountUI = new ManageDiscountUI(view);
        discountUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageSupplier() {
        ManageSupplierUI supplierUI = new ManageSupplierUI(view);
        supplierUI.setVisible(true);
        view.setVisible(false);
    }

    private void moveManageProduct() {
        ManageProductUI productUI = new ManageProductUI(view);
        productUI.setVisible(true);
        view.setVisible(false);
    }
}

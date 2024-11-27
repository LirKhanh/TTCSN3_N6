package controllers.admin;

import java.sql.*;
import views.admin.ManageCategoryUI;
import views.admin.ManageColorUI;
import views.admin.ManageCustomerUI;
import views.admin.ManageDiscountUI;
import views.admin.ManagePaymentUI;
import views.admin.ManageSizeUI;
import views.admin.ManageStaffUI;
import views.admin.ManageSupplierUI;
import views.admin.MenuAdminUI;

public class MenuAdminController {

    private MenuAdminUI view;

    public MenuAdminController(MenuAdminUI view) {
        this.view = view;
        this.view.addManageColorListener(e -> moveManageColor());
        this.view.addManageCustomerListener(e -> moveManageCustomer());
        this.view.addManageSizeListener(e -> moveManageSize());
        this.view.addManageCategoryListener(e -> moveManageCategory());
        this.view.addManageStaffListener(e -> moveManageStaff());
        this.view.addManagePaymentListener(e -> moveManagePayment());
        this.view.addManageDiscountListener(e -> moveManageDiscount());
        this.view.addManageSupplierListener(e -> moveManageSupplier());

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
}

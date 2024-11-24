package controllers.admin;

import java.sql.*;
import views.admin.ManageColorUI;
import views.admin.MenuAdminUI;

public class MenuAdminController {

    private MenuAdminUI view;

    public MenuAdminController(MenuAdminUI view) {
        this.view = view;
        this.view.addManageColorListener(e -> moveManageColor());
    }

    private void moveManageColor() {
        ManageColorUI colorUI = new ManageColorUI(view);
        colorUI.setVisible(true);
        view.setVisible(false);
        System.out.println("checkMove");
    }
}

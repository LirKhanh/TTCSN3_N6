package main;

import views.LoginUI;
import controllers.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginUI loginView = new LoginUI();
        LoginController loginController = new LoginController(loginView);
        loginView.setVisible(true);
    }
}

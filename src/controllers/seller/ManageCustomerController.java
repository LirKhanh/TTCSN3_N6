package controllers.seller;

import java.sql.*;

import views.seller.ManageCustomerUI;
import views.seller.MenuSellerUI;

public class ManageCustomerController {

    private ManageCustomerUI view;
    private Connection connection;

    public ManageCustomerController(ManageCustomerUI view) {
        this.view = view;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fs?autoReconnect=true&useSSL=false",
                    "root", "123456abc!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        this.view.add...
        
    }
//private...
}

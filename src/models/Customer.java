/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admnin
 */
public class Customer {
    
    private String cus_id;
    private String cus_name;
    private String cus_phone;
    private String address;
    private int fs_point;
    
    public Customer() {  
    }    

    public Customer(String cus_id, String cus_name, String cus_phone, String address, int fs_point) {
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.cus_phone = cus_phone;
        this.address = address;
        this.fs_point = fs_point;
    }
    
    

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFs_point() {
        return fs_point;
    }

    public void setFs_point(int fs_point) {
        this.fs_point = fs_point;
    }
    
    
    
}

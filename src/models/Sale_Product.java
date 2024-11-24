/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADMIN
 */
public class Sale_Product {
    private String hmsId;
    private String saleId;
    private double salePrice;

    public Sale_Product() {
    }

    public Sale_Product(String hmsId, String saleId, double salePrice) {
        this.hmsId = hmsId;
        this.saleId = saleId;
        this.salePrice = salePrice;
    }

    public String getHmsId() {
        return hmsId;
    }

    public void setHmsId(String hmsId) {
        this.hmsId = hmsId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    
}

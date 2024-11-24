/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADMIN
 */
public class Color_Size_Product {
    private String sizeId;
    private String colorId;
    private String productId;
    private String hmsId;
    private int quantity;

    public Color_Size_Product() {
    }

    public Color_Size_Product(String sizeId, String colorId, String productId, String hmsId, int quantity) {
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.productId = productId;
        this.hmsId = hmsId;
        this.quantity = quantity;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHmsId() {
        return hmsId;
    }

    public void setHmsId(String hmsId) {
        this.hmsId = hmsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

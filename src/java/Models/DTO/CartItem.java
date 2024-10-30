/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DTO;

/**
 *
 * @author hoangnn
 */
public class CartItem {

    //kết hợp product, order detail
    private String orderID; //UNIQUE
    private String productID; //UNIQUE
    private String productName; //UNIQUE
    private String productImageUrl;
    private int quantity;
    private String unitPrice;

    public CartItem() {
    }

    public CartItem(String orderID, String productID, String productName, String productImageUrl, int quantity, String unitPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.productImageUrl = productImageUrl;
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getSubTotal() {
        return quantity * Long.parseLong(unitPrice);
    }
}

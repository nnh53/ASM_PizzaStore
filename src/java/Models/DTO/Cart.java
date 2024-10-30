/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DTO;

import java.util.ArrayList;

/**
 *
 * @author hoangnn
 */
public class Cart extends ArrayList<CartItem> {

    public Cart() {
    }

    public CartItem getOrderDetailByProductID(String productIDToSearch) {
        for (CartItem item : this) {
            if (item.getProductID().equals(productIDToSearch)) {
                return item;
            }
        }
        return null;
    }

    public void removeItemByProductID(String productID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equals(productID)) {
                this.remove(i);
            }
        }
    }

    public void updateQuantityByProductID(String productID, String quantity) {
        int quantityInt = Integer.parseInt(quantity);
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equals(productID)) {
                this.get(i).setQuantity(quantityInt);
            }
        }
    }
}

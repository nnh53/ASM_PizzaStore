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
public class ProductError {

    private String productNameError; //UNIQUE
    private String supplierIDError;
    private String categoryIDError;
    private String unitPriceError;
    private String productImageUrlError;

    public ProductError() {
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getSupplierIDError() {
        return supplierIDError;
    }

    public void setSupplierIDError(String supplierIDError) {
        this.supplierIDError = supplierIDError;
    }

    public String getCategoryIDError() {
        return categoryIDError;
    }

    public void setCategoryIDError(String categoryIDError) {
        this.categoryIDError = categoryIDError;
    }

    public String getUnitPriceError() {
        return unitPriceError;
    }

    public void setUnitPriceError(String unitPriceError) {
        this.unitPriceError = unitPriceError;
    }

    public String getProductImageUrlError() {
        return productImageUrlError;
    }

    public void setProductImageUrlError(String productImageUrlError) {
        this.productImageUrlError = productImageUrlError;
    }

}

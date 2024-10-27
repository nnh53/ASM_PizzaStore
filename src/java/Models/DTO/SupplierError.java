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
public class SupplierError {

    private String companyNameError; //UNIQUE
    private String addressError;
    private String phoneError;

    public SupplierError() {
    }

    public String getCompanyNameError() {
        return companyNameError;
    }

    public void setCompanyNameError(String companyNameError) {
        this.companyNameError = companyNameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

}

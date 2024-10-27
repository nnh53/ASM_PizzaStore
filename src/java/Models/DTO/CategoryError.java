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
public class CategoryError {

    private String categoryNameError; //UNIQUE
    private String descriptionError;

    public CategoryError() {
    }

    public String getCategoryNameError() {
        return categoryNameError;
    }

    public void setCategoryNameError(String categoryNameError) {
        this.categoryNameError = categoryNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

}

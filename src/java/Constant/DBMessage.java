package Constant;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hoangnn
 */
public enum DBMessage {
    ACTIVE("active"),
    //disable
    DISSABLED("disabled"),
    USERNAME_ALREADY_EXISTS("UserName already existed"),
    USERNAME_NOT_EXISTS("UserName not existed"),
    USERNAME_OR_PASSWORD_INCORRECT("UserName or Password is incorrect"),
    INPUT_INVALID("Input not valid"),
    SOMETHING_WRONG("Something wrong happened, sorry for inconvenience");

    private final String message;

    //PHẢI CÓ CONSTRUCTOR để tạo obj MỚI CÓ THỂ gọi đc hàm toString
    DBMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

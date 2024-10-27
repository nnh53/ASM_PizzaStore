/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultil;

/**
 *
 * @author hoangnn
 */
public class DaoUltil {

    // Convert từ số thành ID dạng Uxxxx
    public static String parseNumberToID(int number, String identifier) {
        String paddedNumber = String.format("%04d", number); //format 4 số
        return identifier + paddedNumber; //---> vd U0003
    }
}

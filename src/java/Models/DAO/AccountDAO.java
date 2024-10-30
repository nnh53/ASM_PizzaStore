/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DTO.Account;
import Ultil.DaoUltil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * hàm nào cũng quăng lỗi hết quăng hết ra ngoài cho Error Handler tổng nhận ***
 *
 * @author hoangnn
 */
public class AccountDAO {

    public String generateID() throws Exception {
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT COUNT(accountID) FROM Account";
        rs = DBConnection.getResultSetFromQuery(cn, sql); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        int count = 0;
        String id = null;
        if (rs != null && rs.next()) {
            count = rs.getInt(1); //theo lấy theo đúng sql
            rs.close();
        }
        id = DaoUltil.parseNumberToID(count + 1, "A");
        cn.close();
        return id;
    }

    public Account getAccountByAccountID(String searchID) throws Exception {
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT AccountID,UserName,Password,FullName,Type,Status FROM dbo.Account WHERE [AccountID]=? AND [Status] != 'disabled'";
        rs = DBConnection.getResultSetFromQuery(cn, sql, searchID); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Account accountRS = null;
        if (rs != null && rs.next()) {
            String accountID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String userName = rs.getString(2);
            String password = rs.getString(3);
            String fullName = rs.getString(4);
            String type = rs.getString(5);
            String status = rs.getString(6);
            accountRS = new Account(accountID, userName, password, fullName, type, status);
            rs.close();
        }
        cn.close();
        if (accountRS == null) {
            throw new Exception(ErrorMessage.ACCOUNT_NOT_EXISTS.toString());
        }
        return accountRS;
    }

    public Account getAccountByUserName(String searchUserName) throws Exception {
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT AccountID,UserName,Password,FullName,Type,Status FROM dbo.Account WHERE [UserName]=? AND [Status] != 'disabled'";
        rs = DBConnection.getResultSetFromQuery(cn, sql, searchUserName); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Account accountRS = null;
        if (rs != null && rs.next()) {
            String accountID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String userName = rs.getString(2);
            String password = rs.getString(3);
            String fullName = rs.getString(4);
            String type = rs.getString(5);
            String status = rs.getString(6);
            accountRS = new Account(accountID, userName, password, fullName, type, status);
            rs.close();
        }
        cn.close();
        if (accountRS == null) {
            throw new Exception(ErrorMessage.ACCOUNT_NOT_EXISTS.toString());
        }
        return accountRS;
    }

    //add thành công trả lại thằng vừa add không thì null hoặc thrown lỗi
    public Account addAccount(Account accountToAdd) throws Exception {
        //1.kiểm tra userName có tồn tại chưa
        Account tmpAccount = null;
        try {
            tmpAccount = this.getAccountByUserName(accountToAdd.getUserName()); //thật ra có thể viết sql để tránh 2 lần connection gọi
        } catch (Exception e) {
            e = null;
        }
        if (tmpAccount != null) {
            throw new Exception(ErrorMessage.ACCOUNT_ALREADY_EXISTS.toString());
        }
        //fix cứng ID tự tăng
        accountToAdd.setAccountID(this.generateID());
        //2.add vô db
        Connection cn = DBConnection.getConnection();
        int af = 0;
        String sql = "INSERT dbo.Account(AccountID, UserName, Password, FullName, Type, Status)" + "VALUES (\n"
                + "? ," //-- AccountID - nvarchar(30)\n"
                + "? ," //-- UserName - nvarchar(30)\n"
                + "? ," //-- Password - nvarchar(50)\n"
                + "? ," //-- FullName - nvarchar(50)\n"
                + "? ," //-- Type - nvarchar(50)\n"
                + "?" //-- Status - nvarchar(30)\n"
                + ");";
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, accountToAdd.getAccountID(), accountToAdd.getUserName(), accountToAdd.getPassword(), accountToAdd.getFullName(), accountToAdd.getType(), accountToAdd.getStatus()); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? accountToAdd : null; // thành công trả chính nó, ko thì null
    }

    //update thành công trả lại thằng vừa update không thì null hoặc thrown lỗi
    public Account updateAcccount(Account accountToUpdate) throws Exception {
        Connection cn = DBConnection.getConnection();
        //1.kiểm tra có tồn tại chưa
        Account tmpAccount = this.getAccountByAccountID(accountToUpdate.getAccountID()); //thật ra có thể viết sql để tránh 2 lần connection gọi
        if (tmpAccount != null) {
            throw new Exception(ErrorMessage.ACCOUNT_NOT_EXISTS.toString());
        }
        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI

        //2.update vô db
        int af = 0;
        String sql = "UPDATE dbo.Account SET [AccountID]=?,[UserName]=?,[Password]=?,[FullName]=?,[Type]=?,[Status]=? WHERE AccountID=?"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, accountToUpdate.getAccountID(), accountToUpdate.getUserName(), accountToUpdate.getPassword(), accountToUpdate.getFullName(), accountToUpdate.getType(), accountToUpdate.getStatus(), accountToUpdate.getAccountID()); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? accountToUpdate : null; // thành công trả chính nó, ko thì null
    }

    //delete thành công trả lại thằng vừa delete không thì null hoặc thrown lỗi
    public Account deleteAccountByUserName(String userNameToDelete) throws Exception {
        Connection cn = DBConnection.getConnection();
        //1.kiểm tra có tồn tại chưa
        Account tmpAccount = this.getAccountByUserName(userNameToDelete); //thật ra có thể viết sql để tránh 2 lần connection gọi
        // if (tmpAccount == null) {
        //     throw new Exception(ErrorMessage.ACCOUNT_NOT_EXISTS.toString());
        // }
        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI

        //2.update vô db
        int af = 0;
        String statusMessageDisable = DBMessage.DISSABLED.toString();
        String sql = "UPDATE dbo.Account SET [AccountID]=?,[UserName]=?,[Password]=?,[FullName]=?,[Type]=?,[Status]=? WHERE UserName=?"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, tmpAccount.getAccountID(), tmpAccount.getUserName(), tmpAccount.getPassword(), tmpAccount.getFullName(), tmpAccount.getType(), statusMessageDisable, userNameToDelete); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? tmpAccount : null; // thành công trả chính nó, ko thì null
    }

    public Account login(String userNameInp, String passwordInp) throws Exception {
        //0.kiểm tra userName có tồn tại chưa
        Account tmpAccount = this.getAccountByUserName(userNameInp);
        // if (tmpAccount == null) {
        //     throw new Exception(ErrorMessage.ACCOUNT_NOT_EXISTS.toString()); //-hàm getUserByUserName quăng rồi
        // }
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT AccountID,UserName,Password,FullName,Type,Status FROM dbo.Account WHERE [UserName]=? AND [Password]=? AND [Status] != 'disabled'";
        rs = DBConnection.getResultSetFromQuery(cn, sql, userNameInp, passwordInp); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Account accountRS = null;
        if (rs != null && rs.next()) {
            String accountID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String userName = rs.getString(2);
            String password = rs.getString(3);
            String fullName = rs.getString(4);
            String type = rs.getString(5);
            String status = rs.getString(6);
            accountRS = new Account(accountID, userName, password, fullName, type, status);
            rs.close();
        }
        cn.close();
        if (accountRS == null) {
            throw new Exception(ErrorMessage.ACCOUNT_OR_PASSWORD_INCORRECT.toString());
        }
        return accountRS;
    }

    public ArrayList<Account> searchAccountFullName(String searchValue) throws Exception {
        //data
        ArrayList<Account> accountListHasFound = new ArrayList<>();

        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT AccountID,UserName,Password,FullName,Type,Status FROM dbo.Account WHERE [FullName] LIKE ? AND [Status] != 'disabled'";
        String search = "%" + searchValue + "%";
        rs = DBConnection.getResultSetFromQuery(cn, sql, search); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Account accountRS = null;
        while (rs != null && rs.next()) {
            String accountID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String userName = rs.getString(2);
            String password = rs.getString(3);
            String fullName = rs.getString(4);
            String type = rs.getString(5);
            String status = rs.getString(6);
            accountRS = new Account(accountID, userName, password, fullName, type, status);
            accountListHasFound.add(accountRS);
        }
        rs.close();
        cn.close();
        return (accountListHasFound.isEmpty() == true) ? null : accountListHasFound;
    }

}

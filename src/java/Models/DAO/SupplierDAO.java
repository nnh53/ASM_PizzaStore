/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DTO.Supplier;
import Ultil.DaoUltil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * hàm nào cũng quăng lỗi hết quăng hết ra ngoài cho Error Handler tổng nhận ***
 *
 * @author hoangnn
 */
public class SupplierDAO {

    public String generateID() throws Exception {
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT COUNT(SupplierID) FROM dbo.Supplier";
        rs = DBConnection.getResultSetFromQuery(cn, sql); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        int count = 0;
        String id = null;
        if (rs != null && rs.next()) {
            count = rs.getInt(1); //theo lấy theo đúng sql
            rs.close();
        }
        id = DaoUltil.parseNumberToID(count + 1, "S");
        cn.close();
        return id;
    }

    public Supplier getSupplierBySupplierID(String searchID) throws Exception {
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT SupplierID,CompanyName,Address,Phone,Status FROM dbo.Supplier WHERE SupplierID=?";
        rs = DBConnection.getResultSetFromQuery(cn, sql, searchID); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Supplier supplierRS = null;
        if (rs != null && rs.next()) {
            String supplierID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String companyName = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            String status = rs.getString(5);
            supplierRS = new Supplier(supplierID, companyName, address, phone, status);
            rs.close();
        }
        cn.close();
        if (supplierRS == null) {
            throw new Exception(ErrorMessage.SUPPLIER_NOT_EXISTS.toString());
        }
        return supplierRS;
    }

    public Supplier getSupplierByCompanyName(String searchCompanyName) throws Exception {
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT SupplierID,CompanyName,Address,Phone,Status FROM dbo.Supplier WHERE [CompanyName]=? AND [Status] != 'disabled'";
        rs = DBConnection.getResultSetFromQuery(cn, sql, searchCompanyName); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Supplier supplierRS = null;
        if (rs != null && rs.next()) {
            String supplierID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String companyName = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            String status = rs.getString(5);
            supplierRS = new Supplier(supplierID, companyName, address, phone, status);
            rs.close();
        }
        cn.close();
        if (supplierRS == null) {
            throw new Exception(ErrorMessage.SUPPLIER_NOT_EXISTS.toString());
        }
        return supplierRS;
    }

    //add thành công trả lại thằng vừa add không thì null hoặc thrown lỗi
    public Supplier addSupplier(Supplier supplierToAdd) throws Exception {
        //1.kiểm tra companyname có tồn tại chưa
        Supplier tmpSupplier = null;
        try {
            tmpSupplier = this.getSupplierByCompanyName(supplierToAdd.getCompanyName()); //thật ra có thể viết sql để tránh 2 lần connection gọi
        } catch (Exception e) {
            e = null;
        }
        if (tmpSupplier != null) {
            throw new Exception(ErrorMessage.SUPPLIER_ALREADY_EXISTS.toString());
        }
        //fix cứng ID tự tăng
        supplierToAdd.setSupplierID(this.generateID());
        //2.add vô db
        Connection cn = DBConnection.getConnection();
        int af = 0;
        String sql = "INSERT dbo.Supplier(SupplierID, CompanyName, Address, Phone, Status)" + "VALUES (\n"
                + "? ," //-- SupplierID - nvarchar(30)\n"
                + "? ," //-- CompanyName - nvarchar(30)\n"
                + "? ," //-- Address - nvarchar(50)\n"
                + "? ," //-- Phone - nvarchar(50)\n"
                + "?" //-- Status - nvarchar(50)\n"
                + ");";
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, supplierToAdd.getSupplierID(), supplierToAdd.getCompanyName(), supplierToAdd.getAddress(), supplierToAdd.getPhone(), supplierToAdd.getStatus()); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? supplierToAdd : null; // thành công trả chính nó, ko thì null
    }

    //update thành công trả lại thằng vừa update không thì null hoặc thrown lỗi
    public Supplier updateSupplier(Supplier supplierToUpdate) throws Exception {
        Connection cn = DBConnection.getConnection();
        //1.kiểm tra có tồn tại chưa
        Supplier tmpSupplier = this.getSupplierBySupplierID(supplierToUpdate.getSupplierID()); //thật ra có thể viết sql để tránh 2 lần connection gọi

        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI
        //2.update vô db
        int af = 0;
        String sql = "UPDATE dbo.Supplier SET [SupplierID]=?,[CompanyName]=?,[Address]=?,[Phone]=?,[Status]=? WHERE SupplierID=? AND [Status] != 'disabled'"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, supplierToUpdate.getSupplierID(), supplierToUpdate.getCompanyName(), supplierToUpdate.getAddress(), supplierToUpdate.getPhone(), supplierToUpdate.getStatus(), supplierToUpdate.getSupplierID()); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? supplierToUpdate : null; // thành công trả chính nó, ko thì null
    }

    //delete thành công trả lại thằng vừa delete không thì null hoặc thrown lỗi
    public Supplier deleteSupplierByCompanyName(String nameCompany) throws Exception {
        Connection cn = DBConnection.getConnection();
        //1.kiểm tra có tồn tại chưa
        Supplier tmpSupplier = this.getSupplierByCompanyName(nameCompany); //thật ra có thể viết sql để tránh 2 lần connection gọi

        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI
        //2.update vô db
        int af = 0;
        String statusMessageDisable = DBMessage.DISSABLED.toString();
        String sql = "UPDATE dbo.Supplier SET [SupplierID]=?,[CompanyName]=?,[Address]=?,[Phone]=?,[Status]=? WHERE CompanyName=? AND [Status] != 'disabled'"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, tmpSupplier.getSupplierID(), tmpSupplier.getCompanyName(), tmpSupplier.getAddress(), tmpSupplier.getPhone(), statusMessageDisable, nameCompany); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? tmpSupplier : null; // thành công trả chính nó, ko thì null
    }

    public ArrayList<Supplier> getAll() throws Exception {
        //data
        ArrayList<Supplier> list = new ArrayList<>();

        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT SupplierID,CompanyName,Address,Phone,Status FROM dbo.Supplier WHERE [Status] != 'disabled'";
        rs = DBConnection.getResultSetFromQuery(cn, sql); //truyền đúng tham số theo sql ko là đi
        Supplier supplierRS = null;
        while (rs != null && rs.next()) {
            String supplierID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String companyName = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            String status = rs.getString(5);
            supplierRS = new Supplier(supplierID, companyName, address, phone, status);
            list.add(supplierRS);
        }
        rs.close();
        cn.close();
        return (list.isEmpty() == true) ? null : list;
    }
}
//  public ArrayList<Supplier> searchSupplierByCompanyName(String searchValue) throws Exception {
//        //data
//        ArrayList<Supplier> supplierListHasFound = new ArrayList<>();
//
//        //1.lấy data từ db
//        Connection cn = DBConnection.getConnection();
//        ResultSet rs = null;
//        String sql = "SELECT SupplierID,CompanyName,Address,Phone,Status FROM dbo.Supplier WHERE [CompanyName] LIKE ?";
//        rs = DBConnection.getResultSetFromQuery(cn, sql, searchValue); //truyền đúng tham số theo sql ko là đi
//
//        //2.parse/map result
//        Supplier supplierRS = null;
//        if (rs != null && rs.next()) {
//            String supplierID = rs.getString(1); //theo lấy 1 2 theo đúng sql
//            String companyName = rs.getString(2);
//            String address = rs.getString(3);
//            String phone = rs.getString(4);
//            String status = rs.getString(5);
//            supplierRS = new Supplier(supplierID, companyName, address, phone, status);
//            rs.close();
//        }
//        cn.close();
//        supplierListHasFound.add(supplierRS);
//        return (supplierListHasFound.isEmpty() == true) ? null : supplierListHasFound;
//    }

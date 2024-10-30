/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Constant.DBMessage;
import Constant.ErrorMessage;
import Models.DTO.Product;
import Ultil.DaoUltil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * hàm nào cũng quăng lỗi hết quăng hết ra ngoài cho Error Handler tổng nhận ***
 *
 * @author hoangnn
 */
public class ProductDAO {

    public String generateID() throws Exception {
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT COUNT(ProductID) FROM dbo.Product";
        rs = DBConnection.getResultSetFromQuery(cn, sql); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        int count = 0;
        String id = null;
        if (rs != null && rs.next()) {
            count = rs.getInt(1); //theo lấy theo đúng sql
            rs.close();
        }
        id = DaoUltil.parseNumberToID(count + 1, "P");
        cn.close();
        return id;
    }

    public Product getProductByProductID(String searchID) throws Exception {
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT ProductID, ProductName, SupplierID, CategoryID, UnitPrice, ProductImageUrl, Status FROM dbo.Product WHERE ProductID=?";
        rs = DBConnection.getResultSetFromQuery(cn, sql, searchID); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Product productRS = null;
        if (rs != null && rs.next()) {
            String productID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String productName = rs.getString(2);
            String supplierID = rs.getString(3);
            String categoryID = rs.getString(4);
            String unitPrice = rs.getString(5);
            String productImageUrl = rs.getString(6);
            String status = rs.getString(7);
            productRS = new Product(productID, productName, supplierID, categoryID, unitPrice, productImageUrl, status);
            rs.close();
        }
        cn.close();
        if (productRS == null) {
            throw new Exception(ErrorMessage.PRODUCT_NOT_EXISTS.toString());
        }
        return productRS;
    }

    public Product getProductByProductName(String searchProductName) throws Exception {
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT ProductID, ProductName, SupplierID, CategoryID, UnitPrice, ProductImageUrl, Status FROM dbo.Product WHERE ProductName=? AND [Status] != 'disabled'";
        rs = DBConnection.getResultSetFromQuery(cn, sql, searchProductName); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Product productRS = null;
        if (rs != null && rs.next()) {
            String productID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String productName = rs.getString(2);
            String supplierID = rs.getString(3);
            String categoryID = rs.getString(4);
            String unitPrice = rs.getString(5);
            String productImageUrl = rs.getString(6);
            String status = rs.getString(7);
            productRS = new Product(productID, productName, supplierID, categoryID, unitPrice, productImageUrl, status);
            rs.close();
        }
        cn.close();
        if (productRS == null) {
            throw new Exception(ErrorMessage.PRODUCT_NOT_EXISTS.toString());
        }
        return productRS;
    }

    //add thành công trả lại thằng vừa add không thì null hoặc thrown lỗi
    public Product addProduct(Product productToAdd) throws Exception {
        //1.kiểm tra name có tồn tại chưa
        Product productTmp = null;
        try {
            productTmp = this.getProductByProductName(productToAdd.getProductName());
        } catch (Exception e) {
            e = null;
        }
        if (productTmp != null) {
            throw new Exception(ErrorMessage.PRODUCT_ALREADY_EXISTS.toString());
        }
        //fix cứng ID tự tăng
        productToAdd.setProductID(this.generateID());
        //2.add vô db
        Connection cn = DBConnection.getConnection();
        int af = 0;
        String sql = "INSERT dbo.Product(ProductID, ProductName, SupplierID, CategoryID, UnitPrice, ProductImageUrl, Status)" + "VALUES (\n"
                + "? ," //-- ProductID - nvarchar(30)\n"
                + "? ," //-- ProductName - nvarchar(30)\n"
                + "? ," //-- SupplierID - nvarchar(30)\n"
                + "? ," //-- CategoryID - nvarchar(30)\n"
                + "? ," //-- UnitPrice - nvarchar(50)\n"
                + "? ," //-- ProductImageUrl - nvarchar(50)\n"
                + "?" //-- Status - nvarchar(50)\n"
                + ");";
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, productToAdd.getProductID(), productToAdd.getProductName(), productToAdd.getSupplierID(), productToAdd.getCategoryID(), productToAdd.getUnitPrice(), productToAdd.getProductImageUrl(), productToAdd.getStatus()); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? productToAdd : null; // thành công trả chính nó, ko thì null
    }

    //update thành công trả lại thằng vừa update không thì null hoặc thrown lỗi
    public Product updateProduct(Product productToUpdate) throws Exception {
        Connection cn = DBConnection.getConnection();
        //1.kiểm tra có tồn tại chưa
        Product tmpProduct = this.getProductByProductID(productToUpdate.getProductID()); //thật ra có thể viết sql để tránh 2 lần connection gọi

        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI
        //2.update vô db
        int af = 0;
        String sql = "UPDATE dbo.Product SET [ProductID]=?,[ProductName]=?,[SupplierID]=?, [CategoryID]=?, [UnitPrice]=?,[ProductImageUrl]=?,[Status]=?  WHERE ProductID=?"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, productToUpdate.getProductID(), productToUpdate.getProductName(), productToUpdate.getSupplierID(), productToUpdate.getCategoryID(), productToUpdate.getUnitPrice(),
                productToUpdate.getProductImageUrl(),
                productToUpdate.getStatus(),
                productToUpdate.getProductID()
        ); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? productToUpdate : null; // thành công trả chính nó, ko thì null
    }

    //delete thành công trả lại thằng vừa delete không thì null hoặc thrown lỗi
    public Product deleteProductByProductName(String name) throws Exception {
        Connection cn = DBConnection.getConnection();
        //1.kiểm tra có tồn tại chưa
        Product tmpProduct = this.getProductByProductName(name); //thật ra có thể viết sql để tránh 2 lần connection gọi

        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI
        //2.update vô db
        int af = 0;
        String statusMessageDisable = DBMessage.DISSABLED.toString();
        String sql = "UPDATE dbo.Product SET [ProductID]=?,[ProductName]=?,[SupplierID]=?, [CategoryID]=?, [UnitPrice]=?,[ProductImageUrl]=?,[Status]=?  WHERE ProductID=?"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, tmpProduct.getProductID(), tmpProduct.getProductName(), tmpProduct.getSupplierID(), tmpProduct.getCategoryID(), tmpProduct.getUnitPrice(),
                tmpProduct.getProductImageUrl(),
                statusMessageDisable,
                tmpProduct.getProductID()
        ); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? tmpProduct : null; // thành công trả chính nó, ko thì null
    }

    public ArrayList<Product> getAll() throws Exception {
        //data
        ArrayList<Product> list = new ArrayList<>();

        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT ProductID, ProductName, SupplierID, CategoryID, UnitPrice, ProductImageUrl, Status FROM dbo.Product WHERE [Status] != 'disabled'";
        rs = DBConnection.getResultSetFromQuery(cn, sql); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        Product productRS = null;
        while (rs != null && rs.next()) {
            String productID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String productName = rs.getString(2);
            String supplierID = rs.getString(3);
            String categoryID = rs.getString(4);
            String unitPrice = rs.getString(5);
            String productImageUrl = rs.getString(6);
            String status = rs.getString(7);
            productRS = new Product(productID, productName, supplierID, categoryID, unitPrice, productImageUrl, status);
            list.add(productRS);
        }
        rs.close();
        cn.close();
        return (list.isEmpty() == true) ? null : list;
    }

}
//    public ArrayList<Product> searchProductByProductName(String searchValue) throws Exception {
//        //data
//        ArrayList<Product> productListHasFound = new ArrayList<>();
//
//        //1.lấy data từ db
//        Connection cn = DBConnection.getConnection();
//        ResultSet rs = null;
//        String sql = "SELECT ProductID,ProductName,Description,Status FROM dbo.Category WHERE ProductName=? LIKE ?";
//        rs = DBConnection.getResultSetFromQuery(cn, sql, searchValue); //truyền đúng tham số theo sql ko là đi
//
//        //2.parse/map result
//        Product productRS = null;
//        if (rs != null && rs.next()) {
//            String productID = rs.getString(1); //theo lấy 1 2 theo đúng sql
//            String productName = rs.getString(2);
//            String description = rs.getString(3);
//            String status = rs.getString(4);
//            productRS = new Product(productID, productName, productID, productID, status, productName, status)
//            rs.close();
//        }
//        cn.close();
//        productListHasFound.add(productRS);
//        return (productListHasFound.isEmpty() == true) ? null : productListHasFound;

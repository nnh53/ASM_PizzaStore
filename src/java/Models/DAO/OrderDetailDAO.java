/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Constant.ErrorMessage;
import Models.DTO.Order;
import Models.DTO.OrderDetail;
import Models.DTO.Product;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * hàm nào cũng quăng lỗi hết quăng hết ra ngoài cho Error Handler tổng nhận ***
 *
 * @author hoangnn
 */
public class OrderDetailDAO {

    //add thành công trả lại thằng vừa add không thì null hoặc thrown lỗi
    public OrderDetail addOrderDetail(OrderDetail orderDetailToAdd) throws Exception {
        //1.kiểm tra OrderID có tồn tại chưa
        OrderDAO orderDao = new OrderDAO();
        Order supTmp = orderDao.getOrderByOrderID(orderDetailToAdd.getOrderID());

        //2.kiểm tra productID có tồn tại chưa
        ProductDAO proDao = new ProductDAO();
        Product proTmp = proDao.getProductByProductID(orderDetailToAdd.getProductID());

        //2.add vô db
        Connection cn = DBConnection.getConnection();
        int af = 0;
        String sql = "INSERT dbo.OrderDetail(OrderID, ProductID, Quantity, UnitPrice)" + "VALUES (\n"
                + "? ," //-- OrderID - nvarchar(30)\n"
                + "? ," //-- ProductID - nvarchar(30)\n"
                + "? ," //-- Quantity - int"
                + "?" //-- UnitPrice - nvarchar(50)\n"
                + ");";
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, orderDetailToAdd.getOrderID(), orderDetailToAdd.getProductID(), orderDetailToAdd.getQuantity(), orderDetailToAdd.getUnitPrice()); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? orderDetailToAdd : null; // thành công trả chính nó, ko thì null
    }

    //update thành công trả lại thằng vừa update không thì null hoặc thrown lỗi
    public OrderDetail updateOrderDetail(OrderDetail orderDetailToUpdate) throws Exception {
        //1.kiểm tra OrderID có tồn tại chưa
        OrderDAO orderDao = new OrderDAO();
        Order supTmp = orderDao.getOrderByOrderID(orderDetailToUpdate.getOrderID());

        //2.kiểm tra productID có tồn tại chưa
        ProductDAO proDao = new ProductDAO();
        Product proTmp = proDao.getProductByProductID(orderDetailToUpdate.getProductID());

        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI
        //2.update vô db
        Connection cn = DBConnection.getConnection();
        int af = 0;
        String sql = "UPDATE dbo.OrderDetail SET [OrderID]=?,[ProductID]=?,[Quantity]=?,[UnitPrice]=? WHERE OrderID=? AND ProductID=?"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, orderDetailToUpdate.getOrderID(), orderDetailToUpdate.getProductID(), orderDetailToUpdate.getQuantity(), orderDetailToUpdate.getUnitPrice(), orderDetailToUpdate.getOrderID(), orderDetailToUpdate.getProductID()); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? orderDetailToUpdate : null; // thành công trả chính nó, ko thì null
    }

    //delete thành công trả lại thằng vừa delete không thì null hoặc thrown lỗi
    public OrderDetail deleteOrderDetailByOrderIDAndProductID(String orderID, String productID) throws Exception {
        //1.kiểm tra OrderID có tồn tại chưa
        OrderDAO orderDao = new OrderDAO();
        Order supTmp = orderDao.getOrderByOrderID(orderID);

        //2.kiểm tra productID có tồn tại chưa
        ProductDAO proDao = new ProductDAO();
        Product proTmp = proDao.getProductByProductID(productID);

        //3.kiểm tra OrderDetail có tồn tại chưa
        OrderDetail tmpOrdDetail = this.getOrderDetailByOrderIDAndProductID(orderID, productID);
        if (tmpOrdDetail != null) {
            throw new Exception(ErrorMessage.ORDER_DETAIL_NOT_EXISTS.toString());
        }

        //GIỮ LẠI THUỘC TÍNH GÌ CŨ THÌ LẤY LẠI TMP USER XÀI
        //2.update vô db
        Connection cn = DBConnection.getConnection();
        int af = 0;
        String sql = "DELETE FROM dbo.OrderDetail WHERE OrderID=? AND ProductID=?"; //CHÚ Ý CÁI WHERE LÀ CÁI CUỐI
        af = DBConnection.getAffectedRowsFromUpdate(cn, sql, orderID, productID); //truyền đúng tham số theo sql ko là đi
        cn.close();
        return (af > 0) ? tmpOrdDetail : null; // thành công trả chính nó, ko thì null
    }

    public OrderDetail getOrderDetailByOrderIDAndProductID(String orderIDInp, String productIDInp) throws Exception {
        //1.lấy data từ db
        Connection cn = DBConnection.getConnection();
        ResultSet rs = null;
        String sql = "SELECT OrderID,ProductID,Quantity,UnitPrice FROM dbo.OrderDetail WHERE OrderID=? AND ProductID=?";
        rs = DBConnection.getResultSetFromQuery(cn, sql, orderIDInp, productIDInp); //truyền đúng tham số theo sql ko là đi
        //2.parse/map result
        OrderDetail categoryRS = null;
        if (rs != null && rs.next()) {
            String orderID = rs.getString(1); //theo lấy 1 2 theo đúng sql
            String productID = rs.getString(2);
            int quantity = rs.getInt(3);
            String unitPrice = rs.getString(4);
            categoryRS = new OrderDetail(orderID, productID, quantity, unitPrice);
            rs.close();
        }
        cn.close();
        if (categoryRS == null) {
            throw new Exception(ErrorMessage.CATEGORY_NOT_EXISTS.toString());
        }
        return categoryRS;
    }
}

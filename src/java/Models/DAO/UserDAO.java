/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.DTO.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // <editor-fold defaultstate="collapsed" desc="getConnection Method">
    public static Connection getConnection() throws Exception {
        Connection cnn;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;database=SampleDB";
            // SQL Server Authentication
            cnn = DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="login Method">
    public User login(String userName, String password) throws Exception {
        User user = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String lastName;
        boolean isAdmin;
        try {
            cnn = getConnection();
            String sql = "select LastName, isAdmin from Registration where [UserName]=? and [Password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            while (rs.next()) {
                lastName = rs.getString(1);
                isAdmin = rs.getBoolean(2);
                user = new User(userName, password, lastName, isAdmin);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return user;
    }

    // </editor-fold>
    public User getUserByUserName(String userName) throws Exception {
        User user = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String lastName, password;
        boolean isAdmin;
        try {
            cnn = getConnection();
            String sql = "select Password, LastName, isAdmin from Registration where [UserName]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            rs = preStm.executeQuery();
            while (rs.next()) {
                password = rs.getString(1);
                lastName = rs.getString(2);
                isAdmin = rs.getBoolean(3);
                user = new User(userName, password, lastName, isAdmin);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return user;
    }

    public List<User> searchUserByLastName(String searchValue) throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "select UserName, Password, LastName, isAdmin from Registration "
                    + "where LastName like ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                userName = rs.getString(1);
                password = rs.getString(2);
                lastName = rs.getString(3);
                isAdmin = rs.getBoolean(4);
                User user = new User(userName, password, lastName, isAdmin);
                userList.add(user);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        if (userList.isEmpty()) {
            return null;
        }
        return userList;
    }

    public boolean addUser(User user) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Insert Registration values(?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getUserName());
            preStm.setString(2, user.getPassword());
            preStm.setString(3, user.getLastName());
            preStm.setBoolean(4, user.isIsAdmin());
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "delete Registration Where UserName=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean updateUser(User user) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update Registration Set Password=?, LastName=?, isAdmin=? Where UserName=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getPassword());
            preStm.setString(2, user.getLastName());
            preStm.setBoolean(3, user.isIsAdmin());
            preStm.setString(4, user.getUserName());
            return preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

}

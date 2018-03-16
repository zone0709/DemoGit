/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phat.utils.DBUtils;

/**
 *
 * @author zone
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String role = "0";
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_account Where accountID = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getString("isAdmin");
                    if (role.equals("1")) {
                        return true;
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    private List<RegistrationDTO> listResult;

    public List<RegistrationDTO> getListResult() {
        return listResult;
    }

    public void searchProduct(String FromDate, String ToDate)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        if(FromDate == null || ToDate == null){
            return;
        }
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT im.productid pr.productName, pr.quantity , im.expiredDate"
                        + " FROM  tbl_product pr,tbl_import im  "
                        + " WHERE pr.productID = im.productid"
                        + " AND im.expiredDate between ? and ?";
//                String sql = "Select  pr.productName,pr.quantity,im.expiredDate from"
//                        + " tbl_product pr,tbl_import im where pr.productID = im.productid AND im.expiredDate BETWEEN ? AND ?";
                stm = con.prepareStatement(sql);
                stm.setString(1,FromDate);
                stm.setString(2,ToDate);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String quantity = rs.getString("quantity");
                    String expiredDate = rs.getString("expiredDate");
                    String productid = rs.getString("productid");
                    RegistrationDTO dto = new RegistrationDTO(productName, quantity, expiredDate, productid);
                    if (this.listResult == null) {
                        listResult = new ArrayList<RegistrationDTO>();
                    }
                    listResult.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public String checkDate(String date) {
        int count = 0;
        int i;

        int index = date.indexOf("/");
        String Value1;
        String Value2;
        String Value3;
        if (index == -1) {
            char check = '-';
            for (i = 0; i < date.length(); i++) {
                if (date.charAt(i) == check) {
                    count++;
                }
            }
            if (count < 2) {
                return null;
            }
            index = date.indexOf("-");
            if (index == 4) {
                return date;
            } else {
                Value3 = date.substring(0, index);
                Value2 = date.substring(index + 1, date.indexOf("-", index + 1));
                Value1 = date.substring(date.indexOf("-", index + 1) + 1);
                String result = "";
                result = Value1 + "-" + Value2 + "-" + Value3;
                return result;
            }
        } else if (index == 4) {
            char check = '/';
            for (i = 0; i < date.length(); i++) {
                if (date.charAt(i) == check) {
                    count++;
                }
            }
            if (count != 2) {
                return null;
            }
            return date;
        } else {
            char check = '/';
            for (i = 0; i < date.length(); i++) {
                if (date.charAt(i) == check) {
                    count++;
                }
            }
            if (count != 2) {
                return null;
            }
            Value3 = date.substring(0, index);
            Value2 = date.substring(index + 1, date.indexOf("/", index + 1));
            Value1 = date.substring(date.indexOf("/", index + 1) + 1);
            String result = "";
            result = Value1 + "/" + Value2 + "/" + Value3;
            return result;
        }
    }

    public boolean deleteProduct(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Delete From tbl_import Where productid = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updatePassRole(String username, String password, boolean role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update  Registration Set password=? ,isAdmin=?  Where username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createNewAccount(String username, String password, String lastname, boolean role) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert Into Registration(username,password,lastname,isAdmin) "
                        + " Values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}

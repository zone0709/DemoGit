package phat.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class DBUtils implements Serializable{
    public static Connection makeConnection() throws NamingException, SQLException{
        Context context = new InitialContext(); 
        Context webcontext = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) webcontext.lookup("phat");
        Connection con = ds.getConnection();
        return con;
                
    }
}

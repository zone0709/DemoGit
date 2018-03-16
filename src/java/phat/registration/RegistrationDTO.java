/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.registration;

import java.io.Serializable;

/**
 *
 * @author zone
 */
public class RegistrationDTO implements Serializable{
    
    private String productName ;
    private String  quantity ;
    private String expiredDate ;
    private String productid;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String productName, String quantity, String expiredDate, String productid) {
        this.productName = productName;
        this.quantity = quantity;
        this.expiredDate = expiredDate;
        this.productid = productid;
    }

    

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    

    
    
}

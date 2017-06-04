/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers;

//import Customer.*;

/**
 *
 * @author king
 */
public class Customer {
    String code;
    String cus_name;
    String phone;
    

    public Customer(String ccode, String cus_name, String phone) {
        this.code = ccode;
        this.cus_name = cus_name;
        this.phone = phone;
        
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}

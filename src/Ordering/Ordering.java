/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordering;

import java.io.Serializable;



/**
 *
 * @author king
 */
public class Ordering implements Serializable{
    String pcode;
    String ccode;
    int quantity;
    public Ordering left, right, next;
    
    public Ordering(){
        
    }

    public Ordering(String pcode, String ccode, int quantity) {
        this.pcode = pcode;
        this.ccode = ccode;
        this.quantity = quantity;
        
        this.left = null;
        this.right = null;
        this.next = null;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}

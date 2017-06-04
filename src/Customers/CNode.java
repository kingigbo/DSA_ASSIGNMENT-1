/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers;

/**
 *
 * @author king
 */
public class CNode {
    public Customer info;
    public CNode next;

    public CNode(Customer info, CNode next) {
        this.info = info;
        this.next = next;
    }

    public CNode(Customer info) {
        this.info = info;
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordering;



/**
 *
 * @author king
 */
public class OrderingList {
    public Ordering root;

    public OrderingList() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    
    public void addOrder(Ordering order) {
        if (isEmpty()) {
            root = order;
            return;
        }
        Ordering temp = root;
        Ordering prev = root;
        while (temp != null) {
            if (order.pcode.equals(temp.pcode) && order.ccode.equals(temp.ccode)) {
                System.out.println("pcode or ccode already exists!");
                return;
            }
            prev = temp;
            if (order.pcode.compareTo(temp.pcode) > 0 && order.ccode.compareTo(temp.ccode) > 0) {
                temp = temp.right; 
            } else {
                temp = temp.left; 
            }
        }

        if (order.pcode.compareTo(prev.pcode) > 0 && order.ccode.compareTo(prev.ccode) > 0) {
            prev.right = order;
        } else {
            prev.left = order;
        }

    }
    
    public void display(Ordering o) {
        System.out.println( o.pcode + "----" + o.ccode + "----" + o.quantity);
    }

   
    public void inOrder(Ordering order) {
        if (order == null) {
            return;
        }
        inOrder(order.left);
        display(order);
        inOrder(order.right);
    }
    
    public void sortByoCode( Ordering order) {
        if (order == null) {
            return;
        }
        
        sortByoCode(order.left);
        display(order);
        sortByoCode(order.right);
    }
}

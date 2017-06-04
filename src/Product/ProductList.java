/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Ordering.Ordering;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author king
 */
public class ProductList {
    Product head, tail;
    
    public ProductList(){
        head = tail = null;
    }
    
    public boolean isEmpty(){
        return(head == null);
    }
    
    public void pAddFirst(Product n){
        if(isEmpty()){
            head = tail = n;
        }else{
            n.next = head;
            head.prev = n;
            head = n;
        }
    }
    
    public String getPath(String fname){
        File f = new File(fname);
        return f.getAbsolutePath();
    }    
    
    public void pAddLast(Product p){
        if (head == null) {
            head = tail = p;
        }else{
            tail.next = p;
            tail = p;
        }        
    }
    
    public void DisplayAll(){
        Product temp = head;
        while (temp != null) {
            System.out.println(temp.pcode + "\t|\t" + temp.pro_name + "\t|\t" + temp.quantity + "\t|\t" + temp.price + "\t|\t" + temp.saled + "\t|\t" + temp.quantity*temp.price);
            temp = temp.next;
        }
    }
    
    public Product pSearchByCode(String prcode){
        Product temp = head;
        while (temp != null) {            
            if (temp.pcode.trim().equals(prcode)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    
    
    public void DeleteFirst(){
        head = head.next;
    }

public void DeleteAfter(Product temp){
        temp.next = temp.next.next;
    }
    
    public void DeleteByPcode(String pcode){        
        Product temp = head;
        if (pcode == temp.pcode) {
            DeleteFirst();       
            return;
        }
	DeleteAfter(temp);
    }
    public void DeleteAt(int i){
        if (i==0) {
            DeleteFirst();
            return;
        }
    }

public void loadDataFromFile(String fname) throws Exception{
       
        
        String s = "";
        String s1,s2,s3,s4,s5;
        StringTokenizer sk;
        
        RandomAccessFile f = new RandomAccessFile(getPath(fname), "rw");
            while((s = f.readLine()) != null ){
                sk = new StringTokenizer(s,"|");
                s1 = sk.nextToken();
                s2 = sk.nextToken();
                s3 = sk.nextToken();
                s4 = sk.nextToken();
                s5 = sk.nextToken();
                int quantity = Integer.parseInt(s3.trim());
                int saled = Integer.parseInt(s4.trim());
                double price = Double.parseDouble(s5.trim());
                pAddLast(new Product(s1, s2, quantity, saled, price));
            }
            f.close();    
    }
    
    public void writeDataToFile() {
        RandomAccessFile f;
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter file name: ");
            String fname = s.nextLine();
            Product temp = head;
            String pcode, pro_name;
            int quantity, saled;
            double price;
            f = new RandomAccessFile(fname+".txt", "rw");
            while (temp != null) {
                pcode = temp.pcode;
                pro_name = temp.pro_name;
                quantity = temp.quantity;
                saled = temp.saled;
                price = temp.price;
                
                temp = temp.next;
                f.writeBytes(pcode + "\t|\t" + pro_name + "\t|\t" + quantity + "\t|\t" + saled + "\t|\t" + price + "\n");
            }
            System.out.println("Save sucessfull");
            f.close();
        } catch (Exception e) {
            System.out.println("save fail");
        }
    }


    public void sortBypCode() {
        if (!isEmpty()) {
            boolean swapped = true;
            while (swapped) {
                swapped = false;
                Product current = head;
                Product prev = null;

                while (current != null && current.next != null) {
                    Product nextNode = current.next;
                    if (current.getPcode().compareTo(nextNode.getPcode()) > 0) {
                        if (prev != null) {
                            prev.next = nextNode;
                        } else {
                            head = nextNode;
                        }

                        current.next = nextNode.next;
                        nextNode.next = current;

                        if (current.next == null) {
                            tail = current;
                        }
                        swapped = true;
                    }
                    prev = current;
                    current = current.next;
                }
            }
        }
    }
}

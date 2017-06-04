/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers;

import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author king
 */
public class CustomerList {
    protected CNode head,tail,present,prev;
    
    public CustomerList(){
        head = tail = null;
    }
    public boolean isEmpty(){
        return head == null;
    }
    public void enqueue(Customer cus){
        if(isEmpty()){
            head = tail = new CNode(cus);
        }else{
            tail.next = new CNode(cus);
            tail = tail.next;
        }
    }
    public Customer front() throws Exception{
        if(isEmpty()){
            throw new Exception();
        }
        return head.info;
    }
    public Customer dequeue() throws Exception{
        if(isEmpty()){
            throw new Exception();
        }
        Customer cus = head.info;
        head = head.next;
        if(head==null){
            tail = null;
        }
        return cus;
    }
    
    public void display() {
        CNode n = head;
        int i = 1;
        while (n != null) {
            Customer c = n.info;
            String code = c.code;
            
            String name = c.cus_name;
            String phone = c.phone;
            
            if (true) {
                System.out.println("Customer "+i+": " + code + "------" + name + "------" + phone+ "\n");
                n = n.next;
                i++;
            }

        }
    }
    
    public String saveCustomer(){
        CNode n = head;
        String list = "";
        int i = 1;
        while (n != null) {
            Customer c = n.info;
            String code = c.code;
            String name = c.cus_name;
            String phone = c.phone;
            
            
            if (true) {
                list +=("Customer "+i+": " + code + "------" + name + "------" + phone+ "\n");
                n = n.next;
                i++;
            }
        }
        return list;
    }

    public Customer searchCustomer(String code) {
        if (head == null) {
            System.out.println("Customer not not found");
        } else {
            present = head;
            while (present != null && !present.info.code.equals(code)) {
                present = present.next;
            }
        }
        if (present == head && present.info.code.equals(code)) {
            return present.info;
        } else if (present != null) {
            return present.info;
        } 
        return null;
    }

    public void delete(String code) {
        if (head == null) {
            System.out.println("No Customer is found!");
        } else {
            present = head;
            while (present != null && !present.info.code.equals(code)) {
                prev = present;
                present = present.next;
            }
        }
        if (present == head && present.info.code.equals(code)) {
            head = head.next;
            System.out.println("The Customer "+present.info.cus_name+" has been deleted!");
        } else if (present != null) {
            prev.next = present.next;
            System.out.println("The Customer "+present.info.cus_name+" has been deleted!");
        } else {
            System.out.println(" The Customer not found!");
        }
    }
    
    public void writeDataToFile() {
        RandomAccessFile f;
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter file name: ");
            String fname = s.nextLine();
            CNode temp = head;
            int i = 1;
            String ccode, name,phone;
            f = new RandomAccessFile(fname+".txt", "rw");
            while (temp != null) {
                while (temp != null) {
                Customer c = temp.info;
                ccode = c.code;
                name = c.cus_name;
                phone = c.phone;
                
                temp = temp.next;
                f.writeBytes(ccode + "\t|\t" + name + "\t|\t" + phone + "\n");
                }
            }
            System.out.println("Save sucessfull");
            f.close();
        } catch (Exception e) {
            System.out.println("save fail");
        }
    }
}

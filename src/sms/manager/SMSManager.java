/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.manager;

//import Customer.Customer;


import Customers.Customer;
import Customers.CustomerList;
import Ordering.Ordering;
import Ordering.OrderingList;
import Product.Product;
import Product.ProductList;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author king
 */
public class SMSManager {

    /**
     * @param args the command line arguments
     */
    ProductList plist = new ProductList();
    //CustomerList clist = new CustomerList();
    CustomerList clist = new CustomerList();
    OrderingList olist = new OrderingList();

    public static void main(String[] args) {
        SMSManager manager = new SMSManager();
        manager.SwitchMainMenu(manager);
    }

    private int MainMenu() {
        System.out.println("========== Sales Management System (SMS)=============");
        System.out.println("1. Product list.");
        System.out.println("2. Customer list.");
        System.out.println("3. Ordering list.");
        System.out.println("0. Exit.");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        return choice;
    }

    private String ProductMenu() {
        System.out.println("==========1. Product LIST==========");
        System.out.println("1. Load data from file.");
        System.out.println("2. Input & add new item.");
        System.out.println("3. Display data.");
        System.out.println("4. Save product list to file.");
        System.out.println("5. Search by product pcode.");
        System.out.println("6. Delete by pcode.");
        System.out.println("7. Sort by product pcode.");
        System.out.println("8. Delete the node after the node.");
        System.out.println("9. Back to main menu.");
        System.out.println("0. Exit.");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        return choice;
    }

    private int CustomerMenu() {
        System.out.println("==========2. CUSTOMER LIST==========");
        System.out.println("1. Load data from file.");
        System.out.println("2. Input & add new item.");
        System.out.println("3. Display data.");
        System.out.println("4. Save customer list to file.");
        System.out.println("5. Search by customer code.");
        System.out.println("6. Delete by customer code.");
        System.out.println("7. Back to main menu.");
        System.out.println("0. Exit.");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        return choice;
    }

    private int OrderingMenu() {
        System.out.println("==========3. ORDERING LIST==========");
        System.out.println("1. Input data.");
        System.out.println("2. Display data.");
        System.out.println("3. Sort by Product code + Customer code.");
        System.out.println("4. Back to main menu.");
        System.out.println("0. Exit.");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        return choice;
    }
    
    private void SwitchMainMenu(SMSManager manager) {
        int choice = manager.MainMenu();
        switch (choice) {
            case 1:
                manager.SwitchProductMenu(manager);
                break;
            case 2:
                manager.SwitchCustomerMenu(manager);
                break;
            case 3:
                manager.SwitchOrderingMenu(manager);
                break;
            case 0:
                System.exit(0);
                break;
        }
    }
    
    private void SwitchProductMenu(SMSManager sms) {
        String choice = sms.ProductMenu();
        switch (choice) {
            case "1": {
                try {
                    if (plist != null) {
                        plist.loadDataFromFile("product.txt");
                        System.out.println("Load sucessfully!!!");
                    }
                    
                    sms.SwitchProductMenu(sms);
                } catch (Exception ex) {
                    System.out.println("Load fail");
                    sms.SwitchProductMenu(sms);
                }
                break;
            }
            case "2":{
                try {
                    Scanner s = new Scanner(System.in);

                    System.out.println("Input product code:");
                    String pcode = s.nextLine();

                    System.out.println("Input product name:");
                    String name = s.nextLine();

                    System.out.println("Input product quantity:");
                    int quantity = Integer.parseInt(s.nextLine().trim());

                    System.out.println("Input product saled:");
                    int saled = Integer.parseInt(s.nextLine().trim());

                    System.out.println("Input product price:");
                    double price = Double.parseDouble(s.nextLine().trim());

                    if (saled > quantity) {
                        System.out.println("\nWarning: \n\t\tAdd fail!!! (saled ≤ quantity)");
                    } else if (plist.pSearchByCode(pcode) != null) {
                        System.out.println("\nWarning: \n\t\tAdd fail!!! product code is already");
                    } else {
                        Product p = new Product(pcode, name, quantity, saled, price);
                        plist.pAddLast(p);
                        System.out.println("Add product sucessfully!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Add fail. Please try again!!!");
                }
                sms.SwitchProductMenu(sms);
                break;
        }
            case "3": {
                if (plist.isEmpty()) {
                    System.out.println("Empty!!");
                } else {
                    plist.DisplayAll();
                }
                sms.SwitchProductMenu(sms);
                break;
            }
            
            case "4":
                plist.writeDataToFile();
                sms.SwitchProductMenu(sms);
                break;
                
            case "5": {
                System.out.println("Input product code:");
                Scanner s = new Scanner(System.in);

                String pcode = s.nextLine();
                if (plist.pSearchByCode(pcode) == null) {
                    System.out.println("Cannot found Product!!!!");
                } else {
                    System.out.println("Infomation of product's: code " + plist.pSearchByCode(pcode).getPcode() + ":" + "\nProduct Name: \t" + plist.pSearchByCode(pcode).getPro_name()+ "\nQuantity: \t" + plist.pSearchByCode(pcode).getQuantity() + "\nSaled: \t" + plist.pSearchByCode(pcode).getSaled() + "\nPrice: \t" + plist.pSearchByCode(pcode).getPrice());
                }
                sms.SwitchProductMenu(sms);
                break;
            }
            case "6": {
                System.out.println("Input Product code:");
                Scanner s = new Scanner(System.in);

                String pcode = s.nextLine();
                if (plist.pSearchByCode(pcode) == null) {
                    System.out.println("No product have ID like this!!");
                } else {
                    plist.DeleteByPcode(pcode);
                    System.out.println("Delete sucessfully!!!!");
                }
                sms.SwitchProductMenu(sms);
                break;
            }
            case "7": {
                plist.sortBypCode();
                System.out.println("Sorted successfuly!");
                plist.DisplayAll();
                sms.SwitchProductMenu(sms);
                break;
            }
            
            case "8": {
                System.out.println("Input position of product(Start with 0):");
                Scanner s = new Scanner(System.in);
                int position = s.nextInt();
                try {
                    plist.DeleteAt(position);
                    System.out.println("Delete sucessfully!!!!");
                } catch (Exception e) {
                    System.out.println("No Product like this");
                }

                sms.SwitchProductMenu(sms);
                break;
            }
            default: {
                System.out.println("Incorrect number. Please try again!!!");
                sms.SwitchProductMenu(sms);
                break;
            }
            case "9":
                sms.SwitchMainMenu(sms);
                break;
            case "0":
                System.exit(0);
                break;
        }
    }
    
    private void SwitchCustomerMenu(SMSManager sms) {
        int choice = sms.CustomerMenu();
        switch (choice) {
            case 1: 
               System.out.println("Please input file's name");
                Scanner scn = new Scanner(System.in);
                String file = scn.nextLine();
                //Reader.readeFile(file); 
                
                sms.SwitchCustomerMenu(sms);
                break;
            case 2:
                
                System.out.println("Enter Customer's code:");
                Scanner s = new Scanner(System.in);
                String ccode = s.nextLine();
                //String code = scn.nextLine();
                System.out.println("Enter Customer's name:");
                String name = s.nextLine();
                System.out.println("Enter Customer's phone No:");
                String phone = s.nextLine();
                
                if (isNumber(phone)) {
                    clist.enqueue(new Customer(ccode, name, phone));
                    System.out.println("Customer has been successfully added !!");
                } else {
                    System.out.println("Invalid Phone number !!");
                }
                sms.SwitchCustomerMenu(sms);
                break;
            case 3: 
                if (clist.isEmpty()) {
                    System.out.println("Empty!!!");
                } else {
                    clist.display();
                }
                sms.SwitchCustomerMenu(sms);
                break;
            
            case 4:

                clist.writeDataToFile();
                sms.SwitchProductMenu(sms);
                break;
            case 5: 
                s = new Scanner(System.in);
                System.out.println("Enter Customer's code :");
                
                String code = s.nextLine();
                Customers.Customer c = clist.searchCustomer(code);
                if(c!=null){
                    System.out.println("Customer : Code: "+c.getCode()+" Name: "+c.getCode()+" PhoneNo: "+c.getPhone());
                }else{
                    System.out.println("Invalid input code!");
                }
                    sms.SwitchCustomerMenu(sms);
                        break;
            case 6: 
                s = new Scanner(System.in);
                 System.out.println("Enter the Customer's code to be deleted");
                 String cde = s.nextLine();
                
                clist.delete(cde);
            
                sms.SwitchCustomerMenu(sms);
                break;
                
            case 7:
                sms.SwitchMainMenu(sms);
                break;
            
            case 0:
                System.exit(0);
                break;
            default: {
                System.out.println("Incorrect number. Please try again!!!");
                sms.SwitchCustomerMenu(sms);
                break;
            }
        }
    }
        private void SwitchOrderingMenu(SMSManager sms){
            int choice = sms.OrderingMenu();
        switch (choice){
            case 1:
                try{
                    Scanner s = new Scanner(System.in);

                    System.out.println("Input product code:");
                    String pcode = s.nextLine();
                    
                    System.out.println("Input Customer code:");
                    String ccode = s.nextLine();

                    System.out.println("Input product quantity:");
                    int quantity = Integer.parseInt(s.nextLine().trim());

                        
                        Ordering p = new Ordering(pcode, ccode, quantity);
                        olist.addOrder(p);
                        System.out.println("Add product sucessfully!!!");
                    
                } catch (Exception e) {
                    System.out.println("Add fail. Please try again!!!");
                }
                sms.SwitchOrderingMenu(sms);
                break;
                
            case 2:
                System.out.println("===In-Order Display===");
                System.out.println("\n");
                olist.inOrder(olist.root);                        
                        
                sms.SwitchOrderingMenu(sms);
                break;
            case 3:
                System.out.println("===Display===");
                System.out.println("\n");
                olist.sortByoCode(olist.root);
                
                case 4:
                sms.SwitchMainMenu(sms);
                break;
                    
                case 0:
                System.exit(0);
                break;
            default: {
                System.out.println("Incorrect number. Please try again!!!");
                sms.SwitchOrderingMenu(sms);
                break;
            }
        }
    }
        
        
        public static boolean isNumber(String s) {
        try {
            int num = Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

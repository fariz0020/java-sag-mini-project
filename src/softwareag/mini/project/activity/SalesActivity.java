/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.activity;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import softwareag.mini.project.interfaces.SalesActivityInterface;
import softwareag.mini.project.util.DatabaseUtil;
import softwareag.mini.project.util.Helper;

/**
 *
 * @author fariz.nurzam
 */
public class SalesActivity implements SalesActivityInterface {
    
    private Scanner scanner = null;
    private DatabaseUtil db = null;
    private Helper helper = null;
    private static final Logger LOGGER = Logger.getLogger( SalesActivity.class.getName() );
    
    public SalesActivity() {
        scanner = new Scanner(System.in);
        db = new DatabaseUtil();
        helper = new Helper();
        this.welcomeSalesActivity();
    }

    @Override
    public void welcomeSalesActivity() {
        boolean loop = false;
        System.out.println(helper.textH1("Manage Sales"));
        System.out.println("Please choose the option to manage sales");
        while (loop == false) {
            System.out.println("1. Show All Sales");
            System.out.println("2. Add Sale");
            System.out.println("3. Advance Search Sale");
            System.out.println("4. Generate Report Sales");
            System.out.println("0. Back");
            System.out.print("Write : ");
            String option = scanner.next();
            if (option.equals("1")) {
                this.showSales();
                break;
            } else if (option.equals("2")) {
            	this.addSale();
                break;
            } else if (option.equals("3")) {
            	this.searchSale();
                break;
            } else if (option.equals("4")) {
            	this.generateReport();
                break;
            } else if (option.equals("0")) {
                new MainActivity();
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
        new MainActivity();
    }

    @Override
    public void showSales() {
        try {
            db.getAllPurchases();
            helper.endSection();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }

    @Override
    public void addSale() {
        System.out.println(helper.textH2("Add Sale"));
        boolean option = false;
        int idSale = 0;
        
        try {
            idSale = db.addSale();
            System.out.println("Your sales ID : "+idSale);
        
            while(!option) {
                System.out.print("How many product do you want to sale ? ");

                String manyProduct = scanner.next();
                if (manyProduct.matches("[0-9]+")) {
                    for(int i = 0; i < Integer.valueOf(manyProduct); i++) {
                        this.addProductOfSale(idSale, i+1);
                    }
                    break;
                } else {
                    System.out.println("Please write right number");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PurchaseActivity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Sales #"+idSale+" successed");
            helper.endSection();      
        }
    }

    @Override
    public void addProductOfSale(int idSale, int i) {
        try {
            db.readDataBase();
            System.out.println("Product #"+i);
            System.out.print("Please choose product by ID : ");
            int idProduct = scanner.nextInt();
            System.out.print("Please write it quantity : ");
            int quantity = scanner.nextInt();
            
            db.addProductSale(idProduct, idSale, quantity);
        } catch (Exception ex) {
            Logger.getLogger(PurchaseActivity.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void searchSale() {
        System.out.println(helper.textH2("Advance Search Sale"));
        boolean loop = false;
        
        System.out.println("Please choose the option to advance search sales");
        while (loop == false) {
            System.out.println("1. Search by 1 date");
            System.out.println("2. Search between 2 dates");
            
            System.out.println("0. Back");
            System.out.print("Write : ");
            String option = scanner.next();
            
            System.out.println("Order by :");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            System.out.print("Write : ");
            String order = scanner.next();
            if (option.equals("1")) {
                this.advanceSearchSale(option, order);
                break;
            } else if (option.equals("2")) {
                this.advanceSearchSale(option, order);
                break;
            } else if (option.equals("0")) {
                new PurchaseActivity();
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
        new MainActivity();
    }

    @Override
    public void advanceSearchSale(String option, String order) {
        String date, date2;
        System.out.println("Input date with format yyyy-mm-dd");
        date = scanner.next();
        String orderOption = (order.equals("1")) ? "ASC" : "DESC";
        
        if (option.equals("1")) {
            try {
                db.getSaleBySearch(date, "", orderOption);
            } catch (Exception ex) {
                Logger.getLogger(PurchaseActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {    
            System.out.println("Input last date with format yyyy-mm-dd");
            date2 = scanner.next();
            try {
                db.getSaleBySearch(date, date2, orderOption);
            } catch (Exception ex) {
                Logger.getLogger(PurchaseActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        helper.endSection();
    }
    
}

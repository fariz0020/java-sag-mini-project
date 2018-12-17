/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.activity;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import softwareag.mini.project.interfaces.PurchaseActivityInterface;
import softwareag.mini.project.util.DatabaseUtil;
import softwareag.mini.project.util.Helper;

/**
 *
 * @author fariz.nurzam
 */
public class PurchaseActivity implements PurchaseActivityInterface{
    
    private Scanner scanner = null;
    private DatabaseUtil db = null;
    private Helper helper = null;
    private static final Logger LOGGER = Logger.getLogger( PurchaseActivity.class.getName() );
    
    public PurchaseActivity() {
        scanner = new Scanner(System.in);
        db = new DatabaseUtil();
        helper = new Helper();
        this.welcomePurchaseActivity();
    }
    
    @Override
    public void welcomePurchaseActivity() {
        boolean loop = false;
        System.out.println(helper.textH1("Manage Purchase"));
        System.out.println("Please choose the option to manage purchase");
        while (loop == false) {
            System.out.println("1. Show All Purchases");
            System.out.println("2. Add Product");
            System.out.println("3. Search Product");
            System.out.println("0. Back");
            System.out.println("Write : ");
            String option = scanner.next();
            if (option.equals("1")) {
                this.showPurchases();
                break;
            } else if (option.equals("2")) {
            	this.addPurchase();
                break;
            } else if (option.equals("3")) {
                
            } else if (option.equals("0")) {
                new MainActivity();
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
        new MainActivity();
    }

    @Override
    public void showPurchases() {
        try {
            db.getAllPurchases();
            helper.endSection();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }

    @Override
    public void addPurchase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void searchPurchase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import softwareag.mini.project.util.DatabaseUtil;

/**
 *
 * @author fariz.nurzam
 */
public class ProductActivity {
    
    private Scanner scanner = null;
    private DatabaseUtil db = null;
    
    public ProductActivity() {
        scanner = new Scanner(System.in);
        db = new DatabaseUtil();
        this.welcomeProductActivity();
    }
    
    public void welcomeProductActivity() {
        boolean loop = false;
        System.out.println("===== Manage Product ======");
        System.out.println("Please choose the option to manage product");
        while (loop == false) {
            System.out.println("1. Show All Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("0. Back");
            System.out.println("Write : ");
            String option = scanner.nextLine();
            if (option.equals("1")) {
                this.showProduct();
                break;
            } else if (option.equals("2")) {
            	this.addProduct();
                break;
            } else if (option.equals("3")) {
                
            } else if (option.equals("4")) {
                
            } else if (option.equals("0")) {
                new MainActivity();
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
        new MainActivity();
    }

	private void addProduct() {
		// TODO Auto-generated method stub
		
	}

	public void showProduct() {
        try {
            db.readDataBase();

        } catch (Exception ex) {
            Logger.getLogger(ProductActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
}

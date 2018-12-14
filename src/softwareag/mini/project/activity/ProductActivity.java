/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.activity;

import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import softwareag.mini.project.model.Product;
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
        System.out.println("== Manage Product ==");
        System.out.println("Please choose the option to manage product");
        while (loop == false) {
            System.out.println("1. Show All Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("0. Back");
            System.out.println("Write : ");
            String option = scanner.next();
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
    
    public void addProduct() {
        Product product = new Product();
        System.out.println("==== Add Product =====");
        System.out.println("Product name : ");
        String name = scanner.next();
        product.setName(name);
        System.out.println("product name : "+product.getName());
        System.out.println("Category list : ");
        //TOOD:5show category list
        System.out.println("Category ID : ");
        product.setIdCategory(scanner.nextInt());
        System.out.println("New Product : ");
        System.out.println("- name : "+product.getName());
        System.out.println("- category ID : "+product.getIdCategory());
        
        boolean loop = false;
        while(loop == false) {  
            System.out.println("Are you sure to insert new product ? (true/false)");
            String answer = scanner.next();
            if (answer.equals("true")) {
                try {
                    db.addProduct(product);
                    this.welcomeProductActivity();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(ProductActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (answer.equals("false")) {
                this.welcomeProductActivity();
                break;
            }
        }
    }   
        
    public void showProduct() {
        try {
            db.readDataBase();

        } catch (Exception ex) {
            Logger.getLogger(ProductActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

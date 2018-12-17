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
import softwareag.mini.project.interfaces.ProductActivityInterface;
import softwareag.mini.project.model.Product;
import softwareag.mini.project.util.DatabaseUtil;
import softwareag.mini.project.util.Helper;

/**
 *
 * @author fariz.nurzam
 */
public class ProductActivity implements ProductActivityInterface {
    
    private Scanner scanner = null;
    private DatabaseUtil db = null;
    private Helper helper = null;
    private static final Logger LOGGER = Logger.getLogger( ProductActivity.class.getName() );
    
    public ProductActivity() {
        scanner = new Scanner(System.in);
        db = new DatabaseUtil();
        helper = new Helper();
        this.welcomeProductActivity();
    }
    
    public void welcomeProductActivity() {
        boolean loop = false;
        System.out.println(helper.textH1("Manage Product"));
        System.out.println("Please choose the option to manage product");
        while (loop == false) {
            System.out.println("1. Show All Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product");
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
                
            } else if (option.equals("5")) {
                this.searchProduct();
                break;
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

    public void searchProduct() {
        boolean loop = false;
        System.out.println(helper.textH2("Search Product"));
        System.out.println("Please choose the option to search product");
        System.out.println("1. Search product by name");
        System.out.println("3. Search product by quantity");
        System.out.println("2. Search product by category");
        System.out.println("0. Back");
        System.out.print("Write : ");
        String option = scanner.next();
        while (loop == false) {
            if (option.equals("1")) {
                this.searchProductByFilter("name");
                break;
            } else if (option.equals("2")) {
            	this.searchProductByFilter("quantity");
                break;
            } else if (option.equals("3")) {
                this.searchProductByFilter("category");
                break;
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
    }
    
    public void searchProductByFilter(String filter) {
        System.out.println(helper.textH3("Search Product By " + helper.capitailizeWord(filter)));
        System.out.print("Please input the product " + filter + ": ");
            LOGGER.log( Level.INFO, "// filter : "+filter );
        try {
            String keyword = scanner.next();
            LOGGER.log( Level.INFO, "// keyword : "+keyword );
            db.getProductBySearch(keyword, filter);
        } catch (Exception ex) {
            Logger.getLogger(ProductActivity.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            helper.endSection();
        }
    } 
}

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

import softwareag.mini.project.model.Category;
import softwareag.mini.project.util.DatabaseUtil;

/**
 *
 * @author fariz.nurzam
 */
public class CategoryActivity {
    
    private Scanner scanner = null;
    private DatabaseUtil db = null;
    
    public CategoryActivity() {
    	scanner = new Scanner(System.in);
        db = new DatabaseUtil();
        this.welcomeCategoryActivity();
    }

    private void welcomeCategoryActivity() {
        // TODO Auto-generated method stub
        boolean loop = false;
        System.out.println("===== Manage Product ======");
        System.out.println("Please choose the option to manage product");
        while (loop == false) {
            System.out.println("1. Show All Categories");
            System.out.println("2. Add Category");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Search Category");
            System.out.println("0. Back");
            System.out.println("Write : ");
            String option = scanner.nextLine();
            if (option.equals("1")) {
                this.showCategory();
                break;
            } else if (option.equals("2")) {
            	this.addCategory();
                break;
            } else if (option.equals("3")) {
            	this.updateCategory();
                break;
            } else if (option.equals("4")) {
            	this.deleteCategory();
                break;
            } else if (option.equals("4")) {
            	this.searchCategory();
                break;
            } else if (option.equals("0")) {
                new MainActivity();
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
        new MainActivity();
    }

	private void showCategory() {
            //  TODO Auto-generated method stub
        try {
            db.getCategories();
        } catch (Exception ex) {
            Logger.getLogger(CategoryActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCategory() {
		Category category = new Category();
        System.out.println("==== Add Category =====");
        System.out.println("Category name : ");
        String name = scanner.next();
        category.setName(name);
        System.out.println("Category name : "+category.getName());
//        System.out.println("Category list : ");
//        //TOOD:5show category list
//        System.out.println("Category ID : ");
//        category.setIdCategory(scanner.nextInt());
//        System.out.println("New Product : ");
//        System.out.println("- name : "+category.getName());
//        System.out.println("- category ID : "+category.getIdCategory());
        
        boolean loop = false;
        while(loop == false) {  
            System.out.println("Are you sure to insert new product ? (true/false)");
            String answer = scanner.next();
            if (answer.equals("true")) {
                try {
                    db.addCategory(category);
                    this.welcomeCategoryActivity();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(CategoryActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (answer.equals("false")) {
                this.welcomeCategoryActivity();
                break;
            }
        }
    }
    
    private void updateCategory() {
		// TODO Auto-generated method stub
		
	}
    
    private void deleteCategory() {
		// TODO Auto-generated method stub
		
	}

    private void searchCategory() {
		// TODO Auto-generated method stub
		
	}
}

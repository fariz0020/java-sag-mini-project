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
import softwareag.mini.project.model.Product;
import softwareag.mini.project.util.DatabaseUtil;
import softwareag.mini.project.util.Helper;

/**
 *
 * @author fariz.nurzam
 */
public class CategoryActivity {
    
    private Scanner scanner = null;
    private DatabaseUtil db = null;
    private Helper helper = null;
    private static final Logger LOGGER = Logger.getLogger( CategoryActivity.class.getName() );
    
    public CategoryActivity() {
    	scanner = new Scanner(System.in);
        db = new DatabaseUtil();
        helper = new Helper();
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
            } else if (option.equals("5")) {
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

        boolean loop = false;
        while(loop == false) {  
            System.out.println("Are you sure to insert new category ? (Yes/No)");
            String answer = scanner.next();
            if (answer.equals("Yes")) {
                try {
                    db.addCategory(category);
                    this.welcomeCategoryActivity();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(CategoryActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (answer.equals("No")) {
                this.welcomeCategoryActivity();
                break;
            }
        }
    }
    
    private void updateCategory() {
		// TODO Auto-generated method stub
        try {
            db.getCategories();
        } catch (Exception ex) {
            Logger.getLogger(CategoryActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    	Category category = new Category();
        System.out.println("==== Update Category =====");
        System.out.println("Choose Category ID to update : ");
        int id = scanner.nextInt();
        category.setId(id);
        System.out.println("- Category ID : "+category.getId());
        
        String name = scanner.next();
        category.setName(name);
        System.out.println("- Category Name : "+category.getName());
        
        boolean loop = false;
        while(loop == false) {  
            System.out.println("Are you sure to update category? (Yes/No)");
            String answer = scanner.next();
            if (answer.equals("Yes")) {
                try {
                    db.updateCategory(category);
                    this.welcomeCategoryActivity();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(CategoryActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (answer.equals("No")) {
                this.welcomeCategoryActivity();
                break;
            }
        }
	}
    
    private void deleteCategory(){
		// TODO Auto-generated method stub
		try {
            db.getCategories();
        } catch (Exception ex) {
            Logger.getLogger(CategoryActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    	Category category = new Category();
        System.out.println("==== Delete Category =====");
        System.out.println("Choose Category ID to delete : ");
        int id = scanner.nextInt();
        category.setId(id);
        System.out.println("- Category ID : "+category.getId());
        
        boolean loop = false;
        while(loop == false) {  
            System.out.println("Are you sure to delete product? (Yes/No)");
            String answer = scanner.next();
            if (answer.equals("Yes")) {
                try {
                    db.deleteCategory(category);
                    this.welcomeCategoryActivity();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(ProductActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (answer.equals("No")) {
                this.welcomeCategoryActivity();
                break;
            }
        }
    	
	}

    public void searchCategory() {
        boolean loop = false;
        System.out.println(helper.textH2("Search Category"));
        System.out.println("Please choose the option to search category");
        System.out.println("1. Search category by name");
        System.out.println("0. Back");
        System.out.print("Write : ");
        String option = scanner.next();
        while (loop == false) {
            if (option.equals("1")) {
                this.searchCategoryByFilter("name");
                break;
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
    }
    
    public void searchCategoryByFilter(String filter) {
        System.out.println(helper.textH3("Search Category By " + helper.capitailizeWord(filter)));
        System.out.print("Please input the product " + filter + ": ");
            LOGGER.log( Level.INFO, "// filter : "+filter );
        try {
            String keyword = scanner.next();
            LOGGER.log( Level.INFO, "// keyword : "+keyword );
            db.getCategoryBySearch(keyword, filter);
        } catch (Exception ex) {
            Logger.getLogger(CategoryActivity.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            helper.endSection();
        }
    } 
}

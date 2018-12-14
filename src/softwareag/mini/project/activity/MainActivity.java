/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.activity;

import java.util.Scanner;

/**
 *
 * @author fariz.nurzam
 */
public class MainActivity {
    
    Scanner scanner;

    public MainActivity() {
        scanner = new Scanner(System.in);
        this.welcomeActivity();
    }
    
    public void welcomeActivity() {
        boolean loop = false;
        System.out.println("Welcome to IMan APP - Inventory Management Application");
        System.out.println("Please choose the option");
        while (loop == false) {
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Categories");
            System.out.println("3. Manage Purchases");
            System.out.println("4. Manage Sales");
            System.out.println("0. Exit Application");
            System.out.println("Write : ");
            String option = scanner.nextLine();
            if (option.equals("1")) {
                new ProductActivity();
                break;
            } else if (option.equals("2")) {
            	new CategoryActivity();
                break;
            } else if (option.equals("3")) {
            	new PurchaseActivity();
                break;
            } else if (option.equals("4")) {
            	new SalesActivity();
                break;
            } else if (option.equals("0")) {
                System.out.println("Thank you for using IMan APP - Inventory Management Application");
                System.exit(0);
            } else {
                System.out.println("Wrong option, please choose the right option below :");
            }
        }
        
    }
}

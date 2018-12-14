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
        System.out.println("Welcome to IMan APP - Inventory Management Application");
        System.out.println("Please choose the option");
        System.out.println("1. Manage Products");
        System.out.println("2. Manage Categories");
        System.out.println("3. Manage Purchases");
        System.out.println("4. Manage Sales");
        
        int option = scanner.nextInt();
        System.out.println("You choose " + option);
    }
    
}

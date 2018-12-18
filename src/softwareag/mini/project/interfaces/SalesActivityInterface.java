/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.interfaces;

/**
 *
 * @author fariz.nurzam
 */
public interface SalesActivityInterface {
    
    void welcomeSalesActivity();
    
    void showSales();
    
    void addSale();
    
    void addProductOfSale(int idSale, int i);
    
    void searchSale();
    
    void advanceSearchSale(String option, String order);
    
    void generateReport();
    
}

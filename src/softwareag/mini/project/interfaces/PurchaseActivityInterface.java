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
public interface PurchaseActivityInterface {
    
    void welcomePurchaseActivity();
    
    void showPurchases();
    
    void addPurchase();
    
    void addProductOfPurchase(int idPurchase, int i);
    
    void searchPurchase();
    
    void advanceSearchPurchase(String option, String order);
}

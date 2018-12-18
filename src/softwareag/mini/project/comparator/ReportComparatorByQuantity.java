/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.comparator;

import java.util.Comparator;
import softwareag.mini.project.model.Product;

/**
 *
 * @author fariz.nurzam
 */
public class ReportComparatorByQuantity implements Comparator<Product>{

    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getQuantity() > o2.getQuantity()) {
            return 1;
        } else if (o1.getQuantity() < o2.getQuantity()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public Comparator<Product> reversed() {
        return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

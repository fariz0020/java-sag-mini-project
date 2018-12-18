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
public class ReportComparatorByDate implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }

    @Override
    public Comparator<Product> reversed() {
        return Comparator.super.reversed(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

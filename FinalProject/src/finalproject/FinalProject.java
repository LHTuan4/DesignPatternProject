/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import Comparison.*;
import ConnectionAdapter.mySQLAdapter;
import Criteria.CriteriaBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import myAnnotation.COLUMN;
import myAnnotation.TABLE;

/**
 *
 * @author DangTrinh
 */
public class FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, SQLException, NoSuchFieldException {
        // TODO code application logic here
            test t = new test("jeremy","TPHCM",4);
            test t1 = new test("jeremy1","TPHCM",6);
            test t2 = new test("jeremy2","TPHCM",3);
            test t3 = new test("jeremy3","TPHCM",7);
            test t4 = new test("jeremy4","TPHCM",2);
            
            mySQLAdapter a = new mySQLAdapter();
            a.createConnection("localhost:3306/Employee", "root", "123456");
            
            
            CriteriaBuilder c = new CriteriaBuilder(t);
            Comparison ax = new GreaterThan();
            Comparison ax1 = new LessThan();
            c.GreaterThan("diem", ax, 5).LessThan("diem", ax1, 8);      
            a.select(c);  
             
            
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import ConnectionAdapter.mySQLAdapter;
import java.lang.annotation.Annotation;
import myAnnotation.TABLE;

/**
 *
 * @author DangTrinh
 */
public class FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            test t = new test("Luong qaTuan HAHasdA","Bao Loc",10);
            mySQLAdapter a = new mySQLAdapter();
            a.createConnection("localhost:3306/Employee", "root", "123456");
            a.insert(t);       
    }
    
}

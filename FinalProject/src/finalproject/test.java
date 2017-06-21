/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import myAnnotation.COLUMN;
import myAnnotation.KEY;
import myAnnotation.TABLE;

/**
 *
 * @author DangTrinh
 */

@TABLE(name="TEST")
public class test {
    @COLUMN(name="Ten")
    @KEY
    public String name;
    @COLUMN(name="DiaChi")
    public String diachi;
    @COLUMN(name="Diem")
    public int diem;
    
    public int test=20;
    
   
    @Override
    public String toString() {
        return "'"+name+"',"+ "'"+diachi+"',"+diem;
    }

    public test(String name, String diachi, int diem) {
        this.name = name;
        this.diachi = diachi;
        this.diem = diem;
    }

    public String getDiachi() {
        return diachi;
    }

    public int getDiem() {
        return diem;
    }

    public String getName() {
        return name;
    }
    
    
       
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import myAnnotation.COLUMN;
import myAnnotation.KEY;
import myAnnotation.TABLE;

/**
 *
 * @author DangTrinh
 */
public class TableManager {

    public String getName(Object table) {
        Class obj = table.getClass();
        if (obj.isAnnotationPresent(TABLE.class)) {
            Annotation annotation = obj.getAnnotation(TABLE.class);
            TABLE temp = (TABLE) annotation;
            System.out.println(temp.name());
            return temp.name();
        }
        return null;
    }

    public String getKeyName(Object table) {
        for (Field f : table.getClass().getFields()) {
            KEY key = f.getAnnotation(KEY.class);
            if (key != null) {
                COLUMN column = f.getAnnotation(COLUMN.class);
                if (column != null) {
                    return column.name();
                }
            }
        }
        return null;
    }
    
    public Object getKeyValue(Object table) {
        for (Field f : table.getClass().getFields()) {
            KEY key = f.getAnnotation(KEY.class);
            if (key != null) {
                try {
                    return f.get(table);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(TableManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

}

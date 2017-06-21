/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Criteria;

import Comparison.Comparison;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import myAnnotation.COLUMN;

/**
 *
 * @author DangTrinh
 */
public class CriteriaBuilder implements Criteria {

    public Object targetTable;
    public StringBuilder criteria = new StringBuilder("");

    public CriteriaBuilder(Object target) {
        targetTable = target;
    }

    @Override
    public Criteria GreaterThan(String field, Comparison comparier, Object value) {
        
        if (!criteria.toString().equals("")) criteria.append(" AND ");
        Class<?> someClass = targetTable.getClass();
        COLUMN column;
        Field someField;
        try {
            someField = someClass.getField(field);
            column= someField.getAnnotation(COLUMN.class);
            criteria.append(column.name());
            criteria.append(comparier.operator());
            criteria.append(value);
            System.out.println(column.name());

        } catch (NoSuchFieldException ex) {
            Logger.getLogger(CriteriaBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(CriteriaBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(criteria);
        return this;
    }

    @Override
    public Criteria LessThan(String field, Comparison comparier, Object value) {
        if (criteria != null) criteria.append(" AND ");
        Class<?> someClass = targetTable.getClass();
        COLUMN column;
        Field someField;
        try {
            someField = someClass.getField(field);
            column= someField.getAnnotation(COLUMN.class);
            criteria.append(column.name());
            criteria.append(comparier.operator());
            criteria.append(value);
            System.out.println(column.name());

        } catch (NoSuchFieldException ex) {
            Logger.getLogger(CriteriaBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(CriteriaBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(criteria);
        return this;
    }

    @Override
    public Object getTargetTable() {
        return targetTable;
    }

    @Override
    public StringBuilder getQuery() {
        return criteria;
    }

}

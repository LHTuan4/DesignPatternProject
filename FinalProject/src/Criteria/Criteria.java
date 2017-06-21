/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Criteria;

import Comparison.Comparison;

/**
 *
 * @author DangTrinh
 */
public interface Criteria<T> {
    
    public Criteria GreaterThan(String field, Comparison comparier, T value);
    public Criteria LessThan(String field, Comparison comparier, T value);
    public abstract T getTargetTable();
    public abstract StringBuilder getQuery();
    public Criteria GroupBy(String field);
   
    
}

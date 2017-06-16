/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author DangTrinhn
 */
@Retention(RetentionPolicy.RUNTIME)
 //can use in method only.
public @interface TABLE {
     String name() default "Mkyong";
}



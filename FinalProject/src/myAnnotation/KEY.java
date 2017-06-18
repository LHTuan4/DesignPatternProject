/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author DangTrinh
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface KEY {
    String name() default "";
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionAdapter;

import finalproject.test;
import java.lang.annotation.Annotation;
import static java.lang.annotation.RetentionPolicy.values;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import myAnnotation.COLUMN;
import myAnnotation.TABLE;

/**
 *
 * @author DangTrinh
 */
public class mySQLAdapter implements ConnectionInterface  {
    
    Connection connection = null;
    
    @Override
    public boolean createConnection(String url, String user, String password) {
        try {
		connection = DriverManager.getConnection("jdbc:mysql://"+url,user, password);

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return false;
	}

	if (connection != null) {
            return true;
	} else {
		return false;
	}
    }


    @Override
    public boolean insert(Object values)  {
        Class obj = values.getClass();
        if(obj.isAnnotationPresent(TABLE.class)){
            Annotation annotation = obj.getAnnotation(TABLE.class);
            TABLE table = (TABLE) annotation;
            System.out.println(table.name());	
            
            
            Statement statement = null;
            try {
                statement = connection.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                statement.executeUpdate("INSERT INTO " +table.name()+ " VALUES ("+values.toString()+")"); 
            } catch (SQLException ex) {
                Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        String columnsName="";    
            
        Class  aClass = values.getClass();

        Field[] fields = aClass.getFields();
        for (int i=0;i<fields.length;i++){
            if (fields[i].isAnnotationPresent(COLUMN.class))
                {
                     COLUMN column = fields[i].getAnnotation(COLUMN.class);
                        if (column != null)
                        {
                            columnsName += column.name() +",";
                            System.out.println(column.name());
                        }
                    System.out.println(fields[i].getType());
                   
                }
        }
        columnsName = columnsName.substring(0, columnsName.length() - 1);
            
            String query = " insert into " +table.name()+ " ("+columnsName+")"
        + " values (?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = null;
            try {
                int i=1;
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setObject (1, "Barney");
                preparedStmt.setObject (2, "Rubble");             
                preparedStmt.setObject(3, 300);
                
            } catch (SQLException ex) {
                Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
      

            try {
                // execute the preparedstatement
                preparedStmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else System.out.print("ABC");
        return true;
    

    }

    @Override
    public boolean update(Object values) {
        Class  aClass = values.getClass();
        String columnsName="";  
        Field[] fields = aClass.getFields();
        for (int i=0;i<fields.length;i++){
            if (fields[i].isAnnotationPresent(COLUMN.class))
                {
                     COLUMN column = fields[i].getAnnotation(COLUMN.class);
                        if (column != null)
                            columnsName += column.name() +",";
                try {
                    System.out.println(fields[i].get(values));
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
                }
        }
        
        
        columnsName = columnsName.substring(0, columnsName.length() - 1);

      System.out.println(columnsName);
            

        
        return true;
    }
    
    
}
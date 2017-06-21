/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionAdapter;

import Criteria.Criteria;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import myAnnotation.COLUMN;
import myAnnotation.KEY;
import myAnnotation.TABLE;

/**
 *
 * @author DangTrinh
 */
public class mySQLAdapter implements ConnectionInterface {

    Connection connection = null;

    @Override
    public boolean createConnection(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + url, user, password);

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
    public boolean insert(Object values) throws IllegalArgumentException, IllegalAccessException, SQLException {
        Class obj = values.getClass();
        if (obj.isAnnotationPresent(TABLE.class)) {
            Annotation annotation = obj.getAnnotation(TABLE.class);
            TABLE table = (TABLE) annotation;
            System.out.println(table.name());

            String columnsName = "";

            Class aClass = values.getClass();
            Field[] fields = aClass.getFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(COLUMN.class)) {
                    COLUMN column = fields[i].getAnnotation(COLUMN.class);
                    if (column != null) {

                        columnsName += column.name() + ",";
                        System.out.println(column.name());
                    }
                    System.out.println(fields[i].getType());

                }
            }
            columnsName = columnsName.substring(0, columnsName.length() - 1);
            System.out.print(columnsName);

            String query = " insert into " + table.name() + " (" + columnsName + ")"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = null;

            preparedStmt = connection.prepareStatement(query);

            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(COLUMN.class)) {

                    preparedStmt.setObject(i + 1, fields[i].get(values));

                }
            }

            preparedStmt.execute();

        } else {
            System.out.print("ABC");
        }
        return true;

    }

    //update by KEY of table.
    @Override
    public boolean update(Object values) throws IllegalArgumentException, IllegalAccessException, SQLException {
        // Get table name    
        Class obj = values.getClass();
        if (obj.isAnnotationPresent(TABLE.class)) {
            Annotation annotation = obj.getAnnotation(TABLE.class);
            TABLE table = (TABLE) annotation;
            System.out.println("Name of the table: " + table.name());

            String query = "UPDATE " + table.name() + " SET ";
            //Get colums        
            Class columns = values.getClass();
            Field[] fields = columns.getFields();

            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(COLUMN.class)) {
                    COLUMN column = fields[i].getAnnotation(COLUMN.class);
                    if (column != null) {
                        System.out.println(column.name());
                    }
                    query += column.name() + " = ?,";

                    //System.out.println(fields[i].getType());
                    System.out.println(fields[i].get(values));

                }
            }

            query = query.substring(0, query.length() - 1);

            //Get keys
            for (Field f : values.getClass().getFields()) {
                KEY key = f.getAnnotation(KEY.class);
                if (key != null) {
                    COLUMN column = f.getAnnotation(COLUMN.class);
                    if (column != null) {
                        System.out.println(column.name());
                    }

                    query += " WHERE " + column.name() + " = ? ";
                }
            }
            System.out.println(query);

            PreparedStatement preparedStmt = null;

            preparedStmt = connection.prepareStatement(query);
            int count = 0;

            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(COLUMN.class)) {
                    preparedStmt.setObject(i + 1, fields[i].get(values));
                    count++;
                }
            }
            System.out.println(count);
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(KEY.class)) {
                    preparedStmt.setObject(count + 1, fields[i].get(values));

                }
            }
            preparedStmt.execute();
            return true;

        }
        return false;
    }

    @Override
    public boolean delete(Object values) throws IllegalArgumentException, IllegalAccessException, SQLException {

        Class obj = values.getClass();
        if (obj.isAnnotationPresent(TABLE.class)) {
            Annotation annotation = obj.getAnnotation(TABLE.class);
            TABLE table = (TABLE) annotation;
            System.out.println(table.name());
            String columnsName = "";
            String query = "DELETE FROM " + table.name();

            PreparedStatement preparedStmt = null;

            for (Field f : values.getClass().getFields()) {
                KEY key = f.getAnnotation(KEY.class);
                if (key != null) {
                    COLUMN column = f.getAnnotation(COLUMN.class);
                    if (column != null) {
                        System.out.println(column.name());
                    }

                    query += " WHERE " + column.name() + " = ? ";
                    preparedStmt = connection.prepareStatement(query);

                    System.out.println(query + " " + f.get(values));
                    preparedStmt.setObject(1, f.get(values));

                    System.out.println(query);
                    preparedStmt.execute();
                }
            }

        }
        return false;
    }

    public boolean select(Criteria crit) {
        Class obj = crit.getTargetTable().getClass();
        if (obj.isAnnotationPresent(TABLE.class)) {
            try {
                Annotation annotation = obj.getAnnotation(TABLE.class);
                TABLE table = (TABLE) annotation;
                System.out.println(table.name());
                String query = "SELECT * FROM " + table.name() + " WHERE " + crit.getQuery();
                System.out.println(query);
                Statement stmt = null;

                try {
                    stmt = connection.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
                }

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    //Retrieve by column name
                    String ten = rs.getString(1);
                    String daichi = rs.getString(2);
                    int diem = rs.getInt(3);
                    

                    //Display values
                    System.out.print("Ten: " + ten);
                    System.out.print(", Diachi: " + daichi);
                    System.out.print(", diem: " + diem);
                   
                }
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(mySQLAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return true;
    }

}

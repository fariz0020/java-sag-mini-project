/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import softwareag.mini.project.model.Product;

/**
 *
 * @author fariz.nurzam
 */
public class DatabaseUtil {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public void readDataBase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from products");
            writeResultSet(resultSet);

//            // PreparedStatements can use variables and are more efficient
//            preparedStatement = connect
//                    .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
//            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
//            // Parameters start with 1
//            preparedStatement.setString(1, "Test");
//            preparedStatement.setString(2, "TestEmail");
//            preparedStatement.setString(3, "TestWebpage");
//            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
//            preparedStatement.setString(5, "TestSummary");
//            preparedStatement.setString(6, "TestComment");
//            preparedStatement.executeUpdate();
//
//            preparedStatement = connect
//                    .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
//            resultSet = preparedStatement.executeQuery();
//            writeResultSet(resultSet);
//
//            // Remove again the insert comment
//            preparedStatement = connect
//            .prepareStatement("delete from feedback.comments where myuser= ? ; ");
//            preparedStatement.setString(1, "Test");
//            preparedStatement.executeUpdate();
//
//            resultSet = statement
//            .executeQuery("select * from feedback.comments");
//            writeMetaData(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    public void addProduct(Product product) throws Exception {        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);
            
            preparedStatement = connect
                    .prepareStatement("insert into  products(name, id_category) values (?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getIdCategory());
            preparedStatement.executeUpdate();
            
            System.out.println("New product has been added");
            
        } catch (Exception e ) {
            throw e;
        } finally {
            close();
        }
    }
    
    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }
    
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("id");
            String website = resultSet.getString("name");
            String summary = resultSet.getString("quantity");
            System.out.println("ID: " + user);
            System.out.println("Nama: " + website);
            System.out.println("Quantity: " + summary);
        }
    }
    
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}

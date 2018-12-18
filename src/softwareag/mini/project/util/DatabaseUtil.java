
package softwareag.mini.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import softwareag.mini.project.model.Product;
import softwareag.mini.project.model.Category;

/**
 *
 * @author fariz.nurzam
 */
public class DatabaseUtil {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger LOGGER = Logger.getLogger( DatabaseUtil.class.getName() );
    
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
            writeResultSetProduct(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    public void getProducts() throws Exception {
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
                    .executeQuery("select * from products where deleted_at is null");
            
            while (resultSet.next()) {
                String user = resultSet.getString("id");
                String website = resultSet.getString("name");
             
                System.out.println("ID: " + user);
                System.out.println("Category Name: " + website);
                System.out.println("");
            }
            System.out.println("========");
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    public void getCategories() throws Exception {
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
                    .executeQuery("select * from categories where deleted_at is null");

            while (resultSet.next()) {
                String user = resultSet.getString("id");
                String website = resultSet.getString("name");
             
                System.out.println("ID: " + user);
                System.out.println("Category Name: " + website);
                System.out.println("");
            }
            System.out.println("========");
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    private void executeQuery(String string) {
		// TODO Auto-generated method stub
		
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
    
    public void addCategory(Category category) throws Exception {        
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
                    .prepareStatement("insert into  categories(name) values (?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, category.getName());
            //preparedStatement.setInt(2, product.getIdCategory());
            preparedStatement.executeUpdate();
            
            System.out.println("New category has been added");
            
        } catch (Exception e ) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void updateProduct(Product product) throws Exception{  
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);
           String sql = String.format("update products set name='%s' where id=%d", product.getName(), product.getId());
            preparedStatement = connect
                    .prepareStatement(sql);
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1          
            System.out.println(preparedStatement);            
            preparedStatement.executeUpdate();
            
            System.out.println("New Product has been updated");
            
        } catch (Exception e ) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void updateCategory(Category category) throws Exception{  
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);
           String sql = String.format("update categories set name='%s' where id=%d", category.getName(), category.getId());
            preparedStatement = connect
                    .prepareStatement(sql);
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1          
            System.out.println(preparedStatement);            
            preparedStatement.executeUpdate();
            
            System.out.println("New Category has been updated");
            
        } catch (Exception e ) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void deleteProduct(Product product) throws Exception{  
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);
           String sql = String.format("UPDATE products SET deleted_at = NOW() where id='%d'", product.getId());
           System.out.println(sql);
            preparedStatement = connect
                    .prepareStatement(sql);
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1          
            System.out.println(preparedStatement);            
            preparedStatement.executeUpdate();
            
            System.out.println("Product has been deleted");
            
        } catch (Exception e ) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void deleteCategory(Category category) throws Exception{  
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);
           String sql = String.format("UPDATE categories SET deleted_at = NOW() where id='%d'", category.getId());
           System.out.println(sql);
            preparedStatement = connect
                    .prepareStatement(sql);
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1          
            System.out.println(preparedStatement);            
            preparedStatement.executeUpdate();
            
            System.out.println("Category has been deleted");
            
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
            System.out.println("Column " + i +" "+ resultSet.getMetaData().getColumnName(i));
        }
    }
    
    private void writeResultSetProduct(ResultSet resultSet) throws SQLException {
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
            System.out.println("Name: " + website);
            System.out.println("Quantity: " + summary);
            System.out.println("");
        }
        System.out.println("========");
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
    
    public void getProductBySearch(String keyword, String filter) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "";
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            if (filter.equals("name"))
                sql = String.format("SELECT p.*, c.name AS category_name FROM products p "
                            + "LEFT JOIN categories c ON p.id_category = c.id "
                            + "WHERE p.name = '%s' OR p.name LIKE '%%%s' OR p.name LIKE '%s%%'", 
                        keyword, keyword, keyword);
            else if (filter.equals("quantity"))
                sql = String.format("SELECT *, c.name AS category_name FROM products p "
                                + "JOIN categories c ON p.id_category = c.id "
                                + "WHERE p.quantity = '%s'", keyword);
            else if (filter.equals("category"))
                sql = String.format("SELECT *, c.name AS category_name FROM products p "
                            + "JOIN categories c ON p.id_category = c.id "
                            + "WHERE c.name = '%s' OR c.name LIKE '%%%s' OR c.name LIKE '%s%%'",
                        keyword, keyword, keyword);
            
            preparedStatement = connect.prepareStatement(sql);
            LOGGER.log( Level.INFO, "// query : " + preparedStatement.toString() );
            resultSet = preparedStatement.executeQuery();
            
            int size = 0;
            if (resultSet != null) 
            {
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
                writeGetProductBySearch(resultSet, size);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void writeGetProductBySearch(ResultSet resultSet, int max) throws SQLException {
        if (resultSet.next() == false ) 
            System.out.println("The result is empty");
        else {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println("Result " + resultSet.getRow() + " of " + max + " data");
                System.out.println("ID: " + resultSet.getString("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Quantity: " + resultSet.getString("quantity"));
                System.out.println("Category: " + ((resultSet.getString("category_name") != null) ? resultSet.getString("category_name") : "-"));
                System.out.println("Created at: " + resultSet.getString("created_at"));
                System.out.println("Last update: " + resultSet.getString("updated_at"));
                System.out.println("Deleted at: " + ((resultSet.getString("deleted_at") != null) ? resultSet.getString("deleted_at") : "-"));
                System.out.println("");
            }
        }
    }
    
    private void writeResultSetCategory(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String user = resultSet.getString("id");
            String website = resultSet.getString("name");
//            String summary = resultSet.getString("quantity");
            System.out.println("ID: " + user);
            System.out.println("Name: " + website);
//            System.out.println("Quantity: " + summary);
            System.out.println("");
        }
        System.out.println("========");
    }
    
//    private void close() {
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//
//            if (statement != null) {
//                statement.close();
//            }
//
//            if (connect != null) {
//                connect.close();
//            }
//        } catch (Exception e) {
//
//        }
//    }
    
    public void getCategoryBySearch(String keyword, String filter) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "";
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);
            
                sql = String.format("select count(*) as numberofcategory, categories.name from products left join categories on categories.id=products.id_category where categories.name='%s' group by categories.id;", 
                        keyword, keyword, keyword);
                        
            preparedStatement = connect.prepareStatement(sql);
            LOGGER.log( Level.INFO, "// query : " + preparedStatement.toString() );
            resultSet = preparedStatement.executeQuery();
            
            int size = 0;
            if (resultSet != null) 
            {
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
                writeGetCategoryBySearch(resultSet, size);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void writeGetCategoryBySearch(ResultSet resultSet, int max) throws SQLException {
        if (resultSet.next() == false ) 
            System.out.println("The result is empty");
        else {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println("Result " + resultSet.getRow() + " of " + max + " data");
                System.out.println("ID: " + resultSet.getString("id"));
                System.out.println("Name: " + resultSet.getString("name"));
//                System.out.println("Quantity: " + resultSet.getString("quantity"));
//                System.out.println("Category: " + ((resultSet.getString("category_name") != null) ? resultSet.getString("category_name") : "-"));
                System.out.println("Created at: " + resultSet.getString("created_at"));
                System.out.println("Last update: " + resultSet.getString("updated_at"));
                System.out.println("Deleted at: " + ((resultSet.getString("deleted_at") != null) ? resultSet.getString("deleted_at") : "-"));
                System.out.println("");
            }
        }
    }
}

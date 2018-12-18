/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareag.mini.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    .executeQuery("select * from categories");

            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);
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
    
    public void getAllPurchases() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("SELECT p.id, count(pp.id_product) total_item, sum(pp.quantity) total_quantity, "
                            + "p.created_at, p.updated_at, p.deleted_at  "
                            + "FROM purchases p JOIN products_purchases pp ON p.id = pp.id_purchase "
                            + "WHERE p.deleted_at IS NULL "
                            + "GROUP BY p.id");
            
            int size = 0;
            if (resultSet != null) 
            {
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
                writeGetPurchaseSale(resultSet, size);
            }
               
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void writeGetPurchaseSale(ResultSet resultSet, int max) throws SQLException {
        if (resultSet.next() == false ) 
            System.out.println("The result is empty");
        else {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println("Result " + resultSet.getRow() + " of " + max + " data");
                System.out.println("ID: " + resultSet.getString("id"));
                System.out.println("Total item: " + resultSet.getString("total_item"));
                System.out.println("Total quantity: " + resultSet.getString("total_quantity"));
                System.out.println("Created at: " + resultSet.getString("created_at"));
                System.out.println("Last update: " + resultSet.getString("updated_at"));
                System.out.println("Deleted at: " + ((resultSet.getString("deleted_at") != null) ? resultSet.getString("deleted_at") : "-"));
                System.out.println("");
            }
        }
    }
    
    public int addPurchase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            preparedStatement = connect.prepareStatement("INSERT INTO purchases VALUES ()", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            
            if(rs.next()){
                return rs.getInt(1);
            }
            return 0;
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void addProductPurchase(int idProduct, int idPurchase, int quantity) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            statement = connect.createStatement();
            String query = String.format("INSERT INTO products_purchases(id_product, id_purchase, quantity) VALUES (%d, %d, %d)", 
                    idProduct, idPurchase, quantity);
            statement.execute(query);
            
            this.updateProductQuantity(idProduct, quantity, "+");
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    private void updateProductQuantity(int idProduct, int quantity, String operator) throws Exception {
        int currentQuantity = 0, updateQuantity = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            String query = String.format("SELECT quantity FROM products WHERE id = %d", idProduct);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            if(resultSet.next()){
                currentQuantity = resultSet.getInt(1);
            }
            
            updateQuantity = (operator.equals("+")) ? currentQuantity + quantity : currentQuantity - quantity;
            
            String updateQuery = String.format("UPDATE products SET quantity = %d WHERE id = %d", 
                    updateQuantity, idProduct);
            preparedStatement = connect.prepareStatement(updateQuery);
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void getPurchaseBySearch(String date1, String date2, String order) throws Exception {
        int currentQuantity = 0, updateQuantity = 0;
        String query = "";
        StringBuilder queryBuilder = new StringBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            if (date2.isEmpty())
                query = String.format("SELECT p.name, sum(pp.quantity) quantity FROM products p "
                        + "JOIN products_purchases pp ON p.id = pp.id_product "
                        + "JOIN purchases ps ON ps.id = pp.id_purchase "
                        + "WHERE ps.created_at LIKE '%s%%' AND ps.deleted_at IS NULL "
                        + "GROUP BY p.name", date1);
            else
                query = String.format("SELECT p.name, sum(pp.quantity) quantity FROM products p "
                        + "JOIN products_purchases pp ON p.id = pp.id_product "
                        + "JOIN purchases ps ON ps.id = pp.id_purchase "
                        + "WHERE ps.created_at BETWEEN '%s 00:00:00' AND '%s 23:59:59' "
                        + "AND ps.deleted_at IS NULL "
                        + "GROUP BY p.name", date1, date2);
            
            queryBuilder.append(query);
            queryBuilder.append(" ORDER BY quantity "+order);
            
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(queryBuilder.toString());
            
            int size = 0;
            if (resultSet != null) 
            {
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
                this.writeGetPurchaseSalesAdvance(resultSet, size);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        
    }
    
    public void writeGetPurchaseSalesAdvance(ResultSet resultSet, int max) throws SQLException {
        if (resultSet.next() == false ) 
            System.out.println("The result is empty");
        else {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println("Result " + resultSet.getRow() + " of " + max + " data");
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Total quantity: " + resultSet.getString("quantity"));
                System.out.println("");
            }
        }
    }
    
    public void getAllSales() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("SELECT s.id, count(ps.id_product) total_item, sum(ps.quantity) total_quantity, "
                            + "s.created_at, s.updated_at, s.deleted_at  "
                            + "FROM sales s JOIN products_sales ps ON s.id = ps.id_sale "
                            + "WHERE s.deleted_at IS NULL "
                            + "GROUP BY s.id");
            
            int size = 0;
            if (resultSet != null) 
            {
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
                writeGetPurchaseSale(resultSet, size);
            }
               
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public int addSale() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            preparedStatement = connect.prepareStatement("INSERT INTO sales VALUES ()", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            
            if(rs.next()){
                return rs.getInt(1);
            }
            return 0;
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
        
    public void addProductSale(int idProduct, int idSale, int quantity) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            statement = connect.createStatement();
            String query = String.format("INSERT INTO products_sales(id_product, id_sale, quantity) VALUES (%d, %d, %d)", 
                    idProduct, idSale, quantity);
            statement.execute(query);
            
            this.updateProductQuantity(idProduct, quantity, "-");
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public void getSaleBySearch(String date1, String date2, String order) throws Exception {
        String query = "";
        StringBuilder queryBuilder = new StringBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            if (date2.isEmpty())
                query = String.format("SELECT p.name, sum(ps.quantity) quantity FROM products p "
                        + "JOIN products_sales ps ON p.id = ps.id_product "
                        + "JOIN sales s ON s.id = ps.id_sale "
                        + "WHERE s.created_at LIKE '%s%%' AND s.deleted_at IS NULL "
                        + "GROUP BY p.name", date1);
            else
                query = String.format("SELECT p.name, sum(ps.quantity) quantity FROM products p "
                        + "JOIN products_sales ps ON p.id = ps.id_product "
                        + "JOIN sales s ON s.id = ps.id_sale "
                        + "WHERE s.created_at BETWEEN '%s 00:00:00' AND '%s 23:59:59' "
                        + "AND s.deleted_at IS NULL "
                        + "GROUP BY p.name", date1, date2);
            
            queryBuilder.append(query);
            queryBuilder.append(" ORDER BY quantity "+order);
            
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(queryBuilder.toString());
            
            int size = 0;
            if (resultSet != null) 
            {
                resultSet.last();
                size = resultSet.getRow();
                resultSet.beforeFirst();
                this.writeGetPurchaseSalesAdvance(resultSet, size);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }   
    }
    
    public List<Product> getReportByProductCategory(String report, int idCategory) throws Exception {
        String query = "";
        StringBuilder queryBuilder = new StringBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties properties = new Properties();
            properties.setProperty("user", Constant.DB_USER);
            properties.setProperty("password", Constant.DB_PASSWORD);
            properties.setProperty("useSSL", Constant.DB_USESSL);
            properties.setProperty("serverTimezone", Constant.DB_SERVERTIMEZONE);
            
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+Constant.DB_SCHEMA, properties);

            if (report.equals("sales")) {
                query = String.format("SELECT s.created_at, p.id, p.name, ps.quantity, c.name category_name " +
                    "FROM products p " +
                    "JOIN products_sales ps ON ps.id_product = p.id " +
                    "JOIN sales s ON ps.id_sale = s.id " +
                    "JOIN categories c ON p.id_category = c.id " +
                    "WHERE c.id = %d " +
                    "ORDER BY s.created_at DESC", idCategory);
            } else if (report.equals("purchase")) {
                query = String.format("SELECT s.created_at, p.id, p.name, ps.quantity, c.name category_name " +
                    "FROM products p " +
                    "JOIN products_purchases ps ON ps.id_product = p.id " +
                    "JOIN purchases s ON ps.id_purchase = s.id " +
                    "JOIN categories c ON p.id_category = c.id " +
                    "WHERE c.id = 1 " +
                    "ORDER BY s.created_at DESC");
            }
            
            queryBuilder.append(query);
            statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(queryBuilder.toString());
            
            return this.writeGetReport(resultSet);
            
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }   
    }
    
    private List<Product> writeGetReport(ResultSet resultSet) throws SQLException {
        List<Product> dataList = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setCreatedAt(resultSet.getString("created_at"));
            product.setName(resultSet.getString("name"));
            product.setCategoryName(resultSet.getString("category_name"));
            product.setQuantity(resultSet.getInt("quantity"));
            dataList.add(product);
        }
        return dataList;
    }
  
}

package service;

import model.Product;
import model.UpdateProductDTO;
import repository.ProductRepo;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import static utilities.PropertiesDB.loadProperties;


public class ServiceImpl implements Service {

    Properties prop = loadProperties("src/app.properties");
    String dbUrl = prop.getProperty("url");
    String dbUser = prop.getProperty("username");
    String dbPass = prop.getProperty("password");


    @Override
    public List<Product> getProducts() {

        ProductRepo.products.clear();

            String selectQuery = "select * from products";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(selectQuery);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setCategory(rs.getString("category"));
                    product.setPrice(rs.getDouble("price"));
                    ProductRepo.products.add(product);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        return ProductRepo.products;
    }

    @Override
    public boolean addProduct(int id, String productName, String category, double price) {

        String InsertQuery = "insert into products values(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(InsertQuery)
        ) {
            ps.setInt(1, id);
            ps.setString(2, productName);
            ps.setString(3, category);
            ps.setDouble(4, price);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProductByID(int id) {
        String DeleteQuery = "delete from products where id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(DeleteQuery)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void updateProductByID(int id, UpdateProductDTO updateProductDTO) {
       String UpdateQuery = "update products set product_name = ?, category = ?, price = ? where id = ?";
       try(Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
       PreparedStatement ps = conn.prepareStatement(UpdateQuery)){
           ps.setInt(4, id);
           ps.setString(1, updateProductDTO.productName);
           ps.setString(2, updateProductDTO.productCategory);
           ps.setDouble(3, updateProductDTO.productPrice);
           ps.executeUpdate();
           System.out.println("Product updated");
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }

    @Override
    public Product getProductByID(int id) {
        Product foundProduct = ProductRepo.products.stream()
                .filter(product -> product.getId() == id).
                findFirst().orElse(null);
        if (foundProduct == null) {
            System.out.println("Product not found");
            return null;
        } else return foundProduct;
    }


    @Override
    public boolean searchProduct(int id) {
        Product foundProduct = ProductRepo.products.stream()
                .filter(product -> product.getId() == id).
                findFirst().orElse(null);
        if (foundProduct == null) {
            return false;
        } else {
            return true;
        }
    }
}

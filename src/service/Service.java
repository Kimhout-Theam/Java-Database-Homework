package service;

import model.Product;
import model.UpdateProductDTO;

import java.util.List;

public interface Service {
 List<Product> getProducts();

 boolean addProduct(int id, String productName, String category, double price);

 boolean deleteProductByID(int id);
 void updateProductByID(int id, UpdateProductDTO updateProductDTO);
 Product getProductByID(int id);
 boolean searchProduct(int id);


}

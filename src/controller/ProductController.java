package controller;

import model.Product;
import model.UpdateProductDTO;
import service.ServiceImpl;

import java.util.List;

public class ProductController {
ServiceImpl service = new ServiceImpl();

public List<Product> getAllProducts() {
    return  service.getProducts();
}

    public boolean addProduct(int id, String name, String category, double price) {
       return service.addProduct(id, name, category, price);
    }

    public boolean deleteProduct(int id) {
      return   service.deleteProductByID(id);
    }

    public void updateProductByID(int id, UpdateProductDTO updateProductDTO) {
    service.updateProductByID(id,updateProductDTO);
    }


public Product getProductByID(int id) {
    return service.getProductByID(id);
}

public boolean searchProduct(int id){
    return service.searchProduct(id);
}

}

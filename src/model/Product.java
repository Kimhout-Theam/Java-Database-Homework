package model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String productName;
    private  String category;
    private  double price;

    public Product() {};

    public Product(int id, String productName, String category, double price) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

}

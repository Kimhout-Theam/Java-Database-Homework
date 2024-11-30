package model;

public class UpdateProductDTO {
public String productName;
public String productCategory;
public double productPrice;
public UpdateProductDTO() {}
    public UpdateProductDTO(String productName, String productCategory, double productPrice) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }
}

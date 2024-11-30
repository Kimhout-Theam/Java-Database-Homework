package view;

import controller.ProductController;
import model.UpdateProductDTO;
import repository.ProductRepo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {

    static Pattern pattern = Pattern.compile(".*[\\d\\p{Punct}].*\n");
    static Matcher matcher;

    public static void viewClient() {
        ProductController productController = new ProductController();
        TableView tv = new TableView();
        while (true) {
            System.out.println("=".repeat(20));
            System.out.print("""
                    Product Information
                    
                    1. List Products
                    2. Add Product
                    3. Delete Product
                    4. Update Product
                    5. Search Product(s)
                    6. Exit Program
                    """);
            System.out.println("=".repeat(20));
            System.out.print("Enter your choice: ");
            int option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> {
                    tv = new TableView();
                    System.out.println("=".repeat(20));
                    System.out.println("List Products");
                    System.out.println("=".repeat(20));
                    tv.TableViewProduct(productController.getAllProducts());
                }
                case 2 -> {
                    int id = 0;
                    String productName;
                    String productCategory = null;
                    double productPrice = 0;
                    System.out.println("Add Product");
                    while (true) {
                        try {
                            System.out.print("Input ID: ");
                            id = new Scanner(System.in).nextInt();
                            int finalId = id;
                            if (ProductRepo.products.stream().anyMatch(product -> product.getId() == finalId)) {
                                System.out.println("Product ID already exists");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid ID");
                        }
                    }
                    while (true) {
                        try {
                            System.out.print("Product Name: ");
                            productName = new Scanner(System.in).nextLine();
                            matcher = pattern.matcher(productName);
                            boolean matchFound = matcher.find();
                            if (matchFound) {
                                System.out.println("Product Name cannot Contain Punctuation!");
                            } else break;
                        } catch (Exception e) {
                            System.out.println("Invalid Product Name");
                        }
                    }
                    while (true) {
                        try {
                            System.out.print("Product Category: ");
                            productCategory = new Scanner(System.in).nextLine();
                            matcher = pattern.matcher(productCategory);
                            boolean matchFound = matcher.find();
                            if (matchFound) {
                                System.out.println("Product Category cannot Contain any Punctuation!");
                            } else break;
                        } catch (Exception e) {
                            System.out.println("Invalid Product Category");
                        }
                    }
                    while (true) {
                        System.out.print("Product Price: ");
                        try {
                            productPrice = new Scanner(System.in).nextDouble();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Product Price");
                        }
                    }
            boolean isSuccess = productController.addProduct(id, productName, productCategory, productPrice);
            if (isSuccess) {
                System.out.println("Product Added!");
            } else System.out.println("Error Adding Product!");
        }
        case 3 -> {
            int id = 0;
            tv.TableViewProduct(productController.getAllProducts());
            System.out.println("Delete Product");
            try {
                System.out.print("Input ID: ");
                id = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.println("Error Deleting Product");
            }

            boolean isFound = productController.searchProduct(id);
            if (isFound) {
                boolean isSuccess = productController.deleteProduct(id);
                if (isSuccess) {
                    System.out.println("Product Deleted!");
                    productController.getAllProducts();
                } else System.out.println("Error Deleting Product!");
            } else System.out.println("Product not Found!");
        }
        case 4 -> {
            int id = 0;
            tv.TableViewProduct(productController.getAllProducts());
            System.out.println("Update Product");
            System.out.print("Input ID: ");
            id = new Scanner(System.in).nextInt();
            boolean isFound = productController.searchProduct(id);
            if (isFound) {

                String productName;
                while (true) {
                    try {
                        System.out.print("Insert New Product name: ");
                        productName = new Scanner(System.in).nextLine();
                        matcher = pattern.matcher(productName);
                        boolean matchFound = matcher.find();
                        if (matchFound) {
                            System.out.println("Product Name cannot Contain any number or Punctuation!");
                        } else break;
                    } catch (Exception e) {
                        System.out.println("Invalid Product Name");
                    }
                }
                String productCategory;
                while (true) {
                    try {
                        System.out.print("Insert New Product category: ");
                        productCategory = new Scanner(System.in).nextLine();
                        matcher = pattern.matcher(productCategory);
                        boolean matchFound = matcher.find();
                        if (matchFound) {
                            System.out.println("Product Category cannot Contain any number or Punctuation!");
                        } else break;
                    } catch (Exception e) {
                        System.out.println("Invalid Product Category");
                    }
                }
                double productPrice;
                while (true) {
                    try {
                        System.out.print("Insert New Product Price: ");
                        productPrice = new Scanner(System.in).nextDouble();
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid Product Price");
                    }
                }


                productController.updateProductByID(id, new UpdateProductDTO(productName, productCategory, productPrice));
                productController.getAllProducts();
            } else {
                System.out.println("Product Not Found!");
            }
        }
        case 5 -> {
            int id = 0;
            tv.TableViewProduct(productController.getAllProducts());
            System.out.println("Search Product");
            System.out.print("Input ID: ");
            id = new Scanner(System.in).nextInt();
            boolean isSuccess = productController.searchProduct(id);
            if (isSuccess) {
                System.out.println("Product Found!");
                tv = new TableView();
                tv.TableViewOneProduct(productController.getProductByID(id));
            } else System.out.println("Error Searching Product!");
        }
        case 6 -> {
            System.out.println("Exit Program");
            System.exit(0);
        }
        default -> System.out.println("Invalid Option!");
    }


}


    }
            }

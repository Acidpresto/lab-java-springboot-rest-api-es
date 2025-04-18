package Excpetion;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name) {
        super("Product with name " + name + " not found");
    }
}

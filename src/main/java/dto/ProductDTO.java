package dto;

public class ProductDTO {
    private String productName;
    private double productPrice;
    private String productCategory;
    private  int productQuantity;
    private Long id;

    public ProductDTO(){}

    public String getProductName() {return productName;}

    public void setProductName (String productName) {this.productName = productName;}

    public double getProductPrice() {return productPrice;}
    public void setProductPrice (double productPrice) {this.productPrice = productPrice;}

    public String getProductCategory() {return productCategory;}
    public void setProductCategory(String productCategory) {this.productCategory = productCategory;}

    public int getProductQuantity() {return productQuantity;}
    public void setProductQuantity(int productQuantity) {this.productQuantity = productQuantity;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    }

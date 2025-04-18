package dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {

    @NotBlank(message="The name is mandatory")
    @Size(min = 3, message="Name should be larger")
    private String productName;

    @Min(value = 0, message="The price must be positive")
    private double productPrice;

    @NotEmpty(message="Input one category")
    private String productCategory;

    @Min (value = 1, message="Al least one item should be selected")
    private int productQuantity;

    private Long id;

    public @NotBlank(message="The name is mandatory") @Size(min = 3, message="Name should be larger") String getProductName() {return productName;}
    public void setProductName(@NotBlank(message="The name is mandatory") @Size(min = 3, message="Name should be larger")String productName) {this.productName = productName;}

    public @Min(value =1, message="Al least one item should be selected") int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(@Min (value =1, message="Al least one item should be selected") int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public @NotEmpty(message="Input one category") String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory( @NotEmpty(message="Input one category") String productCategory) {
        this.productCategory = productCategory;
    }

    public @Min(value = 0, message="The price must be positive") double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(@Min(value = 0, message="The price must be positive") double productPrice) {
        this.productPrice = productPrice;
    }
}

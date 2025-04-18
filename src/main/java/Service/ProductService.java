package Service;

import Excpetion.ProductNotFoundException;
import dto.ProductRequestDTO;
import dto.ProductResponseDTO;
import model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    private  List<Product> products = new ArrayList<>();
    private Long idCounter = 1L;

    public ProductService() {
        products.add(new Product(nextId(), "Cafe", 2.5, "breakfast", 3));
        products.add(new Product(nextId(), "Milk", 1.5, "breakfast", 6));
    }

    private Long nextId() {
        return idCounter++;
    }

    //CREATE A NEW PRODUCT
    public ProductResponseDTO createProductFromRequestDTO(ProductRequestDTO productRequestDTO) {
        Product newProduct = new Product(nextId(),
                productRequestDTO.getProductName(),
                productRequestDTO.getProductPrice(),
                productRequestDTO.getProductCategory(),
                productRequestDTO.getProductQuantity());
        products.add(newProduct);
        ProductResponseDTO productResponseSTO = new ProductResponseDTO(newProduct.getId(),
                newProduct.getProductName(),
                newProduct.getProductPrice(),
                newProduct.getProductCategory(),
                newProduct.getProductQuantity()
        );
        return productResponseSTO;
    }

    //GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return products;
    }

    //GET PRODUCT BY ID
    public Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    //GET PRODUCT BY NAME
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    //UPDATE THE WHOLE PRODUCT
    public Product updateProduct(String name, ProductRequestDTO productRequestDTO) {
        Product product = getProductByName(name);
        if (product == null) {
            return null;
        }
        product.setProductName(productRequestDTO.getProductName());
        product.setProductPrice(productRequestDTO.getProductPrice());
        product.setProductCategory(productRequestDTO.getProductCategory());
        product.setProductQuantity(productRequestDTO.getProductQuantity());

        return product;
    }
//DELETE A PRODUCT
    public void deleteProduct(String name) {
        boolean hasRemovedProduct = false;
        for (Product product : products) {
            if (product.getProductName().equals(name)) {
                hasRemovedProduct = true;
                products.remove(product);
                break;
            }
        }
        if (hasRemovedProduct == false) throw new ProductNotFoundException(name);
    }

    //OBTAIN PRODUCT BY CATEGORY
    public List<Product> getProductByCategory(String category) {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            boolean equalsCategory = category == null || product.getProductCategory().equals(category);
            if (equalsCategory) {
                result.add(product);}
        }
        return result;
    }

    //OBTAIN PRODUCT BY PRICE RANGE
    public List<Product> getProductByPrice(int minPrice, int maxPrice){
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            boolean isMinPrice = product.getProductPrice() >= minPrice;
            boolean isMaxPrice = product.getProductPrice() <= maxPrice;
            boolean isInRange = isMinPrice && isMaxPrice;

            if (isInRange) {
                result.add(product);
            }
        }
        return result;
    }
}


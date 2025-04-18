package Controller;

import Excpetion.MissingApiKeyException;
import Service.ProductService;
import dto.ProductRequestDTO;
import dto.ProductResponseDTO;
import jakarta.validation.Valid;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

public void validateApiKey(@RequestHeader(value = "API-Key", required = false) String apiKey) {
    if (apiKey == null || apiKey.isEmpty()){
        throw new MissingApiKeyException();
    }
}

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestHeader("API-Key") String apiKey) {
        System.out.println("apiKey: " + apiKey);

        if (!"123456".equals(apiKey)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProductbyProductRequestDTP(
            @RequestBody @Valid ProductRequestDTO product) {
        ProductResponseDTO responseDTO = productService.createProductFromRequestDTO(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByProductName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String name, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(name, productRequestDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String name) {
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductByPrice(
            @RequestParam(defaultValue = "0") int minPrice,
            @RequestParam(defaultValue = "100") int maxPrice ){
        return ResponseEntity.ok(productService.getProductByPrice(minPrice, maxPrice));
    }
}









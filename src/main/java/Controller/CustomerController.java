package Controller;

import jakarta.validation.Valid;
import model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    private final Map<String, Customer> customerMap = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody Customer customer) {
        if (customerMap.containsKey(customer.getEmail())) {
            return new ResponseEntity<>("Customer already exists with this email.", HttpStatus.CONFLICT);
        }
        customerMap.put(customer.getEmail(), customer);
        return new ResponseEntity<>("Customer created successfully!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(new ArrayList<>(customerMap.values()), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerMap.get(email);
        if (customer == null) {
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@PathVariable String email, @Valid @RequestBody Customer updatedCustomer) {
        Customer existingCustomer = customerMap.get(email);
        if (existingCustomer == null) {
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }

        updatedCustomer.setEmail(email); // Keep original email
        customerMap.put(email, updatedCustomer);
        return new ResponseEntity<>("Customer updated successfully.", HttpStatus.OK);
    }
    
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        Customer removed = customerMap.remove(email);
        if (removed == null) {
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Customer deleted successfully.", HttpStatus.OK);
    }

}



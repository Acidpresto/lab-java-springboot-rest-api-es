package Excpetion;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExcpetionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        });
        return errors;
    }


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Producto no encontrado: " + ex.getMessage());
        return error;
    }


    @ExceptionHandler(MissingApiKeyException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleMissingApiKey(MissingApiKeyException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Falta el encabezado API-Key");
        return error;
    }

    // Manejador de Rango de Precios Inválido
    @ExceptionHandler(InvalidPriceRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidPriceRange(InvalidPriceRangeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Rango de precios inválido: " + ex.getMessage());
        return error;
    }




}

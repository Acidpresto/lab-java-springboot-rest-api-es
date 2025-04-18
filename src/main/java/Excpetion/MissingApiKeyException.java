package Excpetion;

public class MissingApiKeyException extends RuntimeException {
    public MissingApiKeyException() {
        super("API Key not found");
    }
}

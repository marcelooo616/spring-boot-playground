package br.com.drop.model.exeption;


public class BusinessRule extends RuntimeException {

    public BusinessRule(String message) {
        super(message);
    }
}

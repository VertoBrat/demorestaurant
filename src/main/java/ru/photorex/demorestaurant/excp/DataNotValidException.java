package ru.photorex.demorestaurant.excp;


import java.util.List;

public class DataNotValidException extends RuntimeException {
    public DataNotValidException(List<String> errors) {
        super("Can't save your data, errors in fields: " + errors);
    }
}

package ru.photorex.demorestaurant.excp;


import java.util.List;

public class DataNotValidException extends RuntimeException {
    public DataNotValidException(List<String> errors) {
        super("Не удалось записать данные. Ошибка в полях: " + errors);
    }
}

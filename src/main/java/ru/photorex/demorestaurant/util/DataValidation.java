package ru.photorex.demorestaurant.util;

import org.springframework.validation.BindingResult;
import ru.photorex.demorestaurant.excp.DataNotValidException;

import java.util.List;
import java.util.stream.Collectors;

public class DataValidation {

    public static void checkErrors(BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorFields =
                    result.getFieldErrors().stream()
                            .map(r-> String.valueOf(r.getField()))
                            .collect(Collectors.toList());
            throw new DataNotValidException(errorFields);
        }
    }
}

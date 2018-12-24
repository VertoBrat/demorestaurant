package ru.photorex.demorestaurant.util;

import org.hibernate.exception.ConstraintViolationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConstraintViolationParser {

    public static String parse(ConstraintViolationException ex) {
        Pattern pattern = Pattern.compile("(?<=\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(ex.getSQLException().getLocalizedMessage());
        return matcher.find() ? matcher.group() + " already exists" : "Some field already exists";
    }
}

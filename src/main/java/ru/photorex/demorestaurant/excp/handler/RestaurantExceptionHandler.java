package ru.photorex.demorestaurant.excp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.photorex.demorestaurant.excp.*;

import java.time.LocalDateTime;


@ControllerAdvice
public class RestaurantExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RestaurantNotFoundException ex) {
        return createResponse(ex);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(DishNotFoundException ex) {
        return createResponse(ex);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException ex) {
        return createResponse(ex);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(TooLateAddVoteException ex) {
        return createResponse(ex);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(DataNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.PRECONDITION_FAILED.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RestaurantNotFoundNewDishException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NO_CONTENT.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<ErrorResponse> createResponse(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

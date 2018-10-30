package ru.photorex.demorestaurant.excp.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timeStamp;
}

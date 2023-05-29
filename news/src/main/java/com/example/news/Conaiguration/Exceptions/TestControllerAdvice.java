package com.example.news.Conaiguration.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
Класс отвечает за возврат статуса с сообщением на неккоректный запрос
 */

@ControllerAdvice
@ResponseBody
public class TestControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(NotFoundException exception) {
        return String.format("The HTTP Status will be Internal Server Error (CODE 404)\n %s\n",exception.getMessage()) ;
    }
}
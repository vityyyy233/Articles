package com.example.news.Conaiguration.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 Класс отвечает за сообщение в статусе HttpStatus.NOT_FOUND на неккоректный запрос
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Article with id=" + id + " not found");
    }

    public NotFoundException(String newsSite) {
        super("Article with newsSite=" + newsSite + " not found");
    }
}

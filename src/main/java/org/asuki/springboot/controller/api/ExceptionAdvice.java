package org.asuki.springboot.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException e) {
        log.error("Argument not valid", e);

        return format("{\"error\":\"%s\"}", e.getMessage());
    }
}

package ru.practicum.ewm.service.controller.advice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.service.controller.advice.exception.BadRequestException;
import ru.practicum.ewm.service.controller.advice.exception.NotFoundException;
import ru.practicum.ewm.service.controller.advice.exception.ValidationException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable e) {
        log.warn("Something goes wrong {}", e.getStackTrace(), e);
        return new ErrorResponse("Unhandled exception.");
    }

    @ExceptionHandler({DataIntegrityViolationException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleValidation(final Exception e) {
        log.debug("Got status 409 Conflict {}", e.getStackTrace(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(final NotFoundException e) {
        log.debug("Got status 404 Not found {}", e.getStackTrace(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(final Exception e) {
        log.debug("Got status 400 Bad request {}", e.getStackTrace(), e);
        return new ErrorResponse(e.getMessage());
    }

    @Data
    public static class ErrorResponse {
        private final String exception;
    }
}

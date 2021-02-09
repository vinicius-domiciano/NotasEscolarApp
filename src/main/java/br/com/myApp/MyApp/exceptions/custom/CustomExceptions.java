package br.com.myApp.MyApp.exceptions.custom;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.dto.errors.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptions {

    @ExceptionHandler(value = BadRequestException.class)
    public Exception badRequestsExceptions(BadRequestException e) {
        return e;
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundExceptions(NotFoundException exception) {
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setMessage(exception.getMessage());
        err.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public  RuntimeException runtimeExceptions(RuntimeException e) {
        return e;
    }

}

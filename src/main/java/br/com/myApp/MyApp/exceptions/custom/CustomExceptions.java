package br.com.myApp.MyApp.exceptions.custom;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.dto.errors.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptions {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> badRequestsExceptions(BadRequestException exception) {
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setMessage(exception.getMessage());
        err.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundExceptions(NotFoundException exception) {
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setMessage(exception.getMessage());
        err.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDTO>> methodArgumentNotValidException (MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ErrorResponseDTO> errors  =  fieldErrors.stream().map(fieldError -> {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
            errorResponseDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
            errorResponseDTO.setMessage(fieldError.getDefaultMessage());
            return errorResponseDTO;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> argumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        String message = exception.getMessage();

        if (message.indexOf("required type 'java.util.UUID'") != -1) {
             error.setMessage("Ops, é necessario passar um UUID como valor do " +
                     exception.getParameter().getParameterName() + " e não passar o valor [" + exception.getValue().toString() + "]");
        } else {
            error.setMessage("Ops, o valor informado [" + exception.getValue().toString() + "] esta invalido.");
        }

        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> httpMessageNotReadableExceptions(HttpMessageNotReadableException exception) {

        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        String message = exception.getMessage();

        String enumValues = "";

        if (message != null) {
            System.out.println(message);
            enumValues = message.substring(message.indexOf("Enum class:"));
            enumValues = enumValues.substring(0, (enumValues.indexOf("];") + 1));
        }

        if (message.indexOf("br.com.myApp.MyApp.model.enumerations") != -1) {
            error.setMessage("Ops, é necessario inserir um valor estatico formado para o campo sendo eles (nome ou indice): " + enumValues);
        } else {
            error.setMessage("Ops, não foi possivel identificar o erro!!");
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

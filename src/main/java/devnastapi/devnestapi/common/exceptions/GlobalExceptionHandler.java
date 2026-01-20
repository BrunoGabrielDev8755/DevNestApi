package devnastapi.devnestapi.common.exceptions;

import devnastapi.devnestapi.common.errordefault.ErrorField;
import devnastapi.devnestapi.common.errordefault.ErrorDefault;
import devnastapi.devnestapi.common.exceptions.usergenericexceptions.DuplicateUserException;
import devnastapi.devnestapi.common.exceptions.usergenericexceptions.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDefault handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorField> listErrors = fieldErrors
                .stream()
                .map(fieldError -> new ErrorField(fieldError
                        .getField(), fieldError
                        .getDefaultMessage())).collect(Collectors.toList());
        return new ErrorDefault(HttpStatus.UNPROCESSABLE_ENTITY.value() ,"Erro de validaçao",listErrors);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateUserException.class)
    public ErrorDefault handleDuplicateUserException(DuplicateUserException e){
        return new ErrorDefault(HttpStatus.CONFLICT.value(), "Duplicate User", null);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorDefault> handleNotFoundUserException(NotFoundUserException e){
        ErrorDefault errorDefault = new ErrorDefault(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                null
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDefault);
    }



}

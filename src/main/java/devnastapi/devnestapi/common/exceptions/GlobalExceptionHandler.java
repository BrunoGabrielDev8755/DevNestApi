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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the application.
 *
 * <p>This class intercepts and handles specific exceptions thrown in the application
 * and returns standardized error responses with proper HTTP status codes.</p>
 *
 * <p>Handled exceptions include:</p>
 * <ul>
 *     <li>MethodArgumentNotValidException - for validation errors</li>
 *     <li>DuplicateUserException - when trying to create a user that already exists</li>
 *     <li>NotFoundUserException - when a requested user is not found</li>
 * </ul>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors thrown by Spring when request body fails validation.
     *
     * @param e the MethodArgumentNotValidException thrown during validation
     * @return ResponseEntity containing ErrorDefault with field-specific validation errors
     *         and HTTP status 422 (Unprocessable Entity)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDefault> handleValidationException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<ErrorField> listErrors = fieldErrors.stream()
                .map(fieldError -> new ErrorField(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        ErrorDefault errorDefault = new ErrorDefault(
                HttpStatus.UNAUTHORIZED.value(),
                "Validation Error",
                listErrors
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body(errorDefault);
    }

    /**
     * Handles DuplicateUserException when a user with the same ID already exists.
     *
     * @param e the DuplicateUserException thrown when trying to create a duplicate user
     * @return ResponseEntity containing ErrorDefault with HTTP status 409 (Conflict)
     */
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorDefault> handleDuplicateUserException(DuplicateUserException e) {
        ErrorDefault errorDefault = new ErrorDefault(
                HttpStatus.CONFLICT.value(),
                "This user already exists",
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(errorDefault);
    }

    /**
     * Handles NotFoundUserException when a requested user is not found in the system.
     *
     * @param e the NotFoundUserException thrown when user is not found
     * @return ResponseEntity containing ErrorDefault with HTTP status 404 (Not Found)
     */
    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorDefault> handleNotFoundUserException(NotFoundUserException e) {
        ErrorDefault errorDefault = new ErrorDefault(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDefault);
    }



}

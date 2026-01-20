package devnastapi.devnestapi.common.errordefault;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorDefault(int status, String message, List<ErrorField> errorFields) {

    public static ErrorDefault defaultResponse(String message){
     return new ErrorDefault(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorDefault conflict(String message){
        return new ErrorDefault (HttpStatus.CONFLICT.value(), message, List.of());
    }

}

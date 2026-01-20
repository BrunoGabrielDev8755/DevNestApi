package devnastapi.devnestapi.common.exceptions.usergenericexceptions;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String message) {
        super(message);
    }
}

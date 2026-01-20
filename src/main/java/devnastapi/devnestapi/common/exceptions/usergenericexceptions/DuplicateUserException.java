package devnastapi.devnestapi.common.exceptions.usergenericexceptions;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }
}

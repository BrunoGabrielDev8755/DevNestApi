package devnastapi.devnestapi.common.exceptions.usergenericexceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}

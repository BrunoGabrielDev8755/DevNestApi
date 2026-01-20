package devnastapi.devnestapi.course.exceptions;

public class CourseAlredyExistsException extends RuntimeException {
    public CourseAlredyExistsException(String message) {
        super(message);
    }
}

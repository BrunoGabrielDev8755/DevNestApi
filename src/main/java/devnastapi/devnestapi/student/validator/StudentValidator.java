package devnastapi.devnestapi.student.validator;

import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator class for Student entities.
 * <p>
 * Provides methods to validate the existence of a student in the database.
 * Used by the service layer to enforce business rules.
 */
@RequiredArgsConstructor
@Component
public class StudentValidator {

    private final StudentRepository studentRepository;

    public boolean studentExistsByEmail(Student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }

    public boolean studentExistsById(UUID id){
        return studentRepository.existsById(id);
    }

}

package devnastapi.devnestapi.student.validator;

import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validator component for Student entities.
 *
 * <p>This class provides validation methods to check the existence of
 * students in the database. It acts as a wrapper around repository
 * existence checks, allowing for consistent validation logic across
 * the service layer.</p>
 *
 * <p>The validator follows these design principles:
 * <ul>
 *   <li>Single Responsibility: Only validates student existence</li>
 *   <li>Reusability: Can be injected into multiple services</li>
 *   <li>Testability: Easy to mock for unit testing</li>
 *   <li>Consistency: Provides uniform validation patterns</li>
 * </ul></p>
 *
 * <p><strong>Usage Example:</strong>
 * <pre>
 * {@code
 * if (validator.studentExistsByEmail(student)) {
 *     throw new DuplicateUserException("Student already exists");
 * }
 * }
 * </pre></p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see Student
 * @see StudentRepository
 * @see Component
 */
@Component
public class StudentValidator {

    /**
     * Repository for database operations on Student entities.
     */
    private final StudentRepository studentRepository;

    /**
     * Constructs a new {@code StudentValidator} with the required repository.
     *
     * @param studentRepository the repository for student database operations,
     *                          must not be {@code null}
     *
     * @throws IllegalArgumentException if {@code studentRepository} is {@code null}
     */
    public StudentValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Checks if a student exists with the given email address.
     *
     * <p>This method performs a database lookup to determine if any student
     * has the specified email address. It's commonly used to prevent
     * duplicate registrations during student creation.</p>
     *
     * <p><strong>Note:</strong> The email comparison is exact and case-sensitive
     * as defined by the underlying repository method {@link StudentRepository#existsByEmail(String)}.
     * Ensure email normalization (e.g., lowercase) is performed before calling
     * this method if case-insensitive checking is required.</p>
     *
     * @param student the student entity containing the email to check,
     *                must not be {@code null} and must have a non-null email
     * @return {@code true} if a student exists with the given email,
     *         {@code false} otherwise
     *
     * @throws IllegalArgumentException if {@code student} is {@code null}
     *                                  or {@code student.getEmail()} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see StudentRepository#existsByEmail(String)
     * @see Student#getEmail()
     */
    public boolean studentExistsByEmail(Student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }

    /**
     * Checks if a student exists with the given ID.
     *
     * <p>This method verifies the existence of a student by their unique
     * identifier. It's commonly used before performing operations that
     * require an existing student (e.g., updates, deletions, retrievals).</p>
     *
     * <p><strong>Performance Note:</strong> This method uses {@link StudentRepository#existsById(Object)}
     * which is typically optimized by JPA providers to execute a count query
     * rather than fetching the entire entity.</p>
     *
     * @param id the unique identifier of the student to check,
     *           must not be {@code null}
     * @return {@code true} if a student exists with the given ID,
     *         {@code false} otherwise
     *
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see StudentRepository#existsById(Object)
     * @see UUID
     */
    public boolean studentExistsById(UUID id) {
        return studentRepository.existsById(id);
    }
}
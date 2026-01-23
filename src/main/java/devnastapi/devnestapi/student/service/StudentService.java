package devnastapi.devnestapi.student.service;

import devnastapi.devnestapi.common.exceptions.usergenericexceptions.DuplicateUserException;
import devnastapi.devnestapi.common.exceptions.usergenericexceptions.NotFoundUserException;
import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.repository.StudentRepository;
import devnastapi.devnestapi.student.validator.StudentValidator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for student management operations.
 *
 * <p>This service class implements business logic for student CRUD operations,
 * acting as an intermediary between the controller layer and the repository layer.
 * It handles validation, security concerns (password encoding), and business rules.</p>
 *
 * <p>Key responsibilities include:
 * <ul>
 *   <li>Creating new students with proper validation and password encoding</li>
 *   <li>Retrieving student information with appropriate error handling</li>
 *   <li>Updating existing student records</li>
 *   <li>Deleting student records</li>
 *   <li>Searching for students using various criteria</li>
 *   <li>Ensuring data consistency and enforcing business rules</li>
 * </ul></p>
 *
 * <p>This service uses constructor injection for dependencies and follows
 * the single responsibility principle by delegating validation to a separate
 * {@link StudentValidator} class.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see Student
 * @see StudentRepository
 * @see StudentValidator
 * @see PasswordEncoder
 */
@Service
public class StudentService {

    /**
     * Repository for database operations on Student entities.
     */
    private final StudentRepository repository;

    /**
     * Validator for business rule validation.
     */
    private final StudentValidator validator;

    /**
     * Password encoder for secure password hashing.
     */
    private final PasswordEncoder encoder;

    /**
     * Constructs a new {@code StudentService} with required dependencies.
     *
     * @param repository the student repository for database operations
     * @param validator the validator for business rule validation
     * @param encoder the password encoder for secure password hashing
     *
     * @throws IllegalArgumentException if any parameter is {@code null}
     */
    public StudentService(StudentRepository repository, StudentValidator validator, PasswordEncoder encoder) {
        this.repository = repository;
        this.validator = validator;
        this.encoder = encoder;
    }

    /**
     * Creates a new student in the system.
     *
     * <p>This method performs the following steps:
     * <ol>
     *   <li>Validates that a student with the same email doesn't already exist</li>
     *   <li>Encodes the plaintext password using {@link PasswordEncoder}</li>
     *   <li>Sets the default role as "STUDENT"</li>
     *   <li>Persists the student entity to the database</li>
     * </ol></p>
     *
     * @param student the student entity to create, must not be {@code null}
     * @return the saved student entity with generated ID and encoded password
     *
     * @throws DuplicateUserException if a student with the same email already exists
     * @throws IllegalArgumentException if {@code student} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see StudentValidator#studentExistsByEmail(Student)
     * @see PasswordEncoder#encode(CharSequence)
     */
    public Student createNewStudent(Student student) {
        if (!validator.studentExistsByEmail(student)) {
            throw new DuplicateUserException("This user already exists");
        }
        student.setPassword(encoder.encode(student.getPassword()));
        student.setRoles("STUDENT");
        return repository.save(student);
    }

    /**
     * Retrieves a student by ID using exception-based approach.
     *
     * <p>This method returns the student directly and throws an exception if
     * the student is not found. This is the preferred approach for refactored
     * code as it provides cleaner error handling at the service layer.</p>
     *
     * @param id the unique identifier of the student to retrieve
     * @return the student entity with the given ID
     *
     * @throws NotFoundUserException if no student exists with the given ID
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see #getStudent(UUID)
     */
    public Student getStudentRefactored(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundUserException("Student not found"));
    }

    /**
     * Retrieves a student by ID using Optional-based approach.
     *
     * <p>This deprecated method returns an {@link Optional} containing the student.
     * It's recommended to use {@link #getStudentRefactored(UUID)} instead for
     * cleaner error handling with exceptions.</p>
     *
     * @param id the unique identifier of the student to retrieve
     * @return an {@link Optional} containing the student if found, empty otherwise
     *
     * @throws NotFoundUserException if no student exists with the given ID
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @deprecated Use {@link #getStudentRefactored(UUID)} instead for better error handling
     * @see #getStudentRefactored(UUID)
     */
    @Deprecated
    public Optional<Student> getStudent(UUID id) {
        if (!validator.studentExistsById(id)) {
            throw new NotFoundUserException("No student found with this ID");
        }
        return repository.findById(id);
    }

    /**
     * Deletes a student by ID.
     *
     * <p>This method performs an idempotent delete operation:
     * <ul>
     *   <li>Validates that the student exists</li>
     *   <li>Deletes the student from the database</li>
     *   <li>Returns {@code true} on successful deletion</li>
     * </ul>
     * Note: The validation check makes this non-idempotent. Consider
     * using {@link StudentRepository#deleteById(Object)} directly if
     * idempotency is required.</p>
     *
     * @param id the unique identifier of the student to delete
     * @return {@code true} if the student was successfully deleted
     *
     * @throws NotFoundUserException if no student exists with the given ID
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see StudentValidator#studentExistsById(UUID)
     */
    public Boolean deleteStudent(UUID id) {
        if (!validator.studentExistsById(id)){
            throw new NotFoundUserException("Student not found");
        }
        repository.deleteById(id);
        return true;
    }

    /**
     * Updates an existing student's information.
     *
     * <p>This method performs a partial update of student information:
     * <ol>
     *   <li>Retrieves the existing student from the database</li>
     *   <li>Updates the email, password, and name fields</li>
     *   <li>Persists the updated entity</li>
     * </ol>
     *
     * <p><strong>Important Notes:</strong>
     * <ul>
     *   <li>The password is updated without re-encoding. This is a security risk.</li>
     *   <li>Only email, password, and name are updated. Other fields remain unchanged.</li>
     *   <li>No validation is performed on the updated email for uniqueness.</li>
     * </ul></p>
     *
     * @param student the student entity containing updated information
     * @return {@code true} if the update was successful
     *
     * @throws NotFoundUserException if no student exists with the given ID
     * @throws IllegalArgumentException if {@code student} or {@code student.getId()} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see #createNewStudent(Student)
     */
    public Boolean updateStudent(Student student) {

        Student existingStudent = repository.findById(student.getId())
                .orElseThrow(() -> new NotFoundUserException("Student not found"));

        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setName(student.getName());

        repository.save(existingStudent);
        return true;
    }

    /**
     * Searches for students using example-based querying.
     *
     * <p>This method performs a flexible search using Spring Data's Example API:
     * <ul>
     *   <li>Name and course fields support partial matching (CONTAINING)</li>
     *   <li>Search is case-insensitive</li>
     *   <li>Null values are ignored in the search criteria</li>
     *   <li>Only name and course fields are searchable</li>
     * </ul></p>
     *
     * @param name the name to search for (partial match, optional)
     * @param course the course to search for (partial match, optional)
     * @return a list of students matching the search criteria, never {@code null}
     *
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see Example
     * @see ExampleMatcher
     */
    public List<Student> searchStudentByExample(String name, String course) {
        var student = new Student();
        student.setName(name);
        student.setCourse(course);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Student> studentExample = Example.of(student, matcher);
        return repository.findAll(studentExample);
    }

    /**
     * Searches for a student by email address.
     *
     * <p>This method performs an exact match search on the email field.
     * It's primarily used for authentication purposes.</p>
     *
     * @param email the email address to search for
     * @return the student with the given email, or {@code null} if not found
     *
     * @throws IllegalArgumentException if {@code email} is {@code null} or empty
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see StudentRepository#findByEmail(String)
     */
    public Student searchOnDb(String email) {
        return repository.findByEmail(email);
    }
}
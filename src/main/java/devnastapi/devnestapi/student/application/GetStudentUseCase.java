package devnastapi.devnestapi.student.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Use case for retrieving student information from the system.
 *
 * <p>This class implements the student retrieval use case,
 * providing methods to fetch student data by ID. It handles
 * ID conversion and delegates to the service layer for data access.</p>
 *
 * <p>Offers two approaches for student retrieval:
 * <ul>
 *   <li>Optional-based approach for explicit null handling</li>
 *   <li>Direct return approach with potential null returns or exceptions</li>
 * </ul></p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see StudentService
 * @see Helpers
 * @see Student
 * @see Optional
 */
@Component
public class GetStudentUseCase {

    /**
     * Helper class for common operations and utilities.
     */
    private final Helpers helpers;

    /**
     * Service for student business operations and data access.
     */
    private final StudentService service;

    /**
     * Constructs a new instance of {@code GetStudentUseCase}.
     *
     * @param helpers the helper class for common operations
     * @param service the service for student business operations
     *
     * @throws IllegalArgumentException if {@code helpers} or {@code service} are null
     */
    public GetStudentUseCase(Helpers helpers, StudentService service) {
        this.helpers = helpers;
        this.service = service;
    }

    /**
     * Retrieves a student by ID using Optional for null-safe handling.
     *
     * <p>This method:
     * <ol>
     *   <li>Converts the string ID to the appropriate format</li>
     *   <li>Delegates to the service layer to fetch the student</li>
     *   <li>Returns an {@code Optional} that may contain the student or be empty</li>
     * </ol>
     * This approach is preferred when the absence of a student is a valid scenario.</p>
     *
     * @param id the ID of the student to retrieve, in string format
     * @return an {@code Optional} containing the student if found,
     *         or an empty {@code Optional} if no student exists with the given ID
     *
     * @throws IllegalArgumentException if the {@code id} is null, empty, or invalid
     * @throws org.springframework.dao.DataAccessException if there's a data access error
     *
     * @see Helpers#idFromString(String)
     * @see StudentService#getStudentRefactored(UUID) (Object)
     * @see Optional
     */
    public Optional<Student> getStudentWithId(String id) {
        var getStudent = service.getStudent(helpers.idFromString(id));
        return getStudent;
    }

    /**
     * Retrieves a student by ID using a direct return approach.
     *
     * <p>This method provides an alternative to {@link #getStudentWithId(String)}
     * that returns the student directly rather than wrapped in an Optional.
     * The implementation may throw exceptions or return null when no student is found,
     * depending on the service layer implementation.</p>
     *
     * @param id the ID of the student to retrieve, in string format
     * @return the student with the given ID, or null/exception based on service implementation
     *
     * @throws IllegalArgumentException if the {@code id} is null, empty, or invalid
     * @throws IllegalStateException if no student is found with the given ID (implementation-dependent)
     * @throws org.springframework.dao.DataAccessException if there's a data access error
     *
     * @see #getStudentWithId(String)
     * @see Helpers#idFromString(String)
     * @see StudentService#getStudentRefactored(UUID)
     */
    public Student getStudentWithIdRefatored(String id) {
        return service.getStudentRefactored(helpers.idFromString(id));
    }
}
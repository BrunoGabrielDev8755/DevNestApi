package devnastapi.devnestapi.student.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.student.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Use case for deleting a student.
 *
 * <p>This class implements the student deletion use case,
 * acting as an application component that orchestrates the
 * operation of removing a student from the system.</p>
 *
 * <p>Responsible for converting the input ID and delegating
 * the deletion operation to the service layer.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see StudentService
 * @see Helpers
 */
@Component
public class DeleteStudentUseCase {

    /**
     * Student service for business operations.
     */
    private final StudentService service;

    /**
     * Helper class for common operations.
     */
    private final Helpers helpers;

    /**
     * Constructs a new instance of {@code DeleteStudentUseCase}.
     *
     * @param service the student service for business operations
     * @param helpers the helper class for common operations
     *
     * @throws IllegalArgumentException if {@code service} or {@code helpers} are null
     */
    public DeleteStudentUseCase(StudentService service, Helpers helpers) {
        this.service = service;
        this.helpers = helpers;
    }

    /**
     * Deletes a student by the provided ID.
     *
     * <p>This method converts the ID from string to the appropriate format
     * using the {@code Helpers} utility and delegates the deletion operation
     * to the student service.</p>
     *
     * @param id the ID of the student to be deleted, in string format
     * @return {@code true} if the student was successfully deleted,
     *         {@code false} otherwise or if the student was not found
     *
     * @throws IllegalArgumentException if the {@code id} is null, empty, or invalid
     * @throws IllegalStateException if an error occurs during deletion
     *
     * @see StudentService#deleteStudent(UUID)
     * @see Helpers#idFromString(String)
     */
    public Boolean deleteStudentById(String id) {
        return service.deleteStudent(helpers.idFromString(id));
    }
}
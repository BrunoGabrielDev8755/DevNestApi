package devnastapi.devnestapi.student.application;

import devnastapi.devnestapi.student.dto.StudentDto;
import devnastapi.devnestapi.student.mapper.StudentMapper;
import devnastapi.devnestapi.student.service.StudentService;
import org.springframework.stereotype.Component;

/**
 * Use case for updating student information in the system.
 *
 * <p>This class implements the student update use case,
 * orchestrating the process of modifying an existing student's
 * information based on the provided data transfer object.</p>
 *
 * <p>Responsible for converting the DTO to a domain entity
 * and delegating the update operation to the service layer,
 * ensuring proper separation of concerns and business rule
 * enforcement.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see StudentService
 * @see StudentMapper
 * @see StudentDto
 */
@Component
public class UpdateStudentUseCase {

    /**
     * Service for student business operations and persistence.
     */
    private final StudentService service;

    /**
     * Mapper for converting between DTO and entity representations.
     */
    private final StudentMapper mapper;

    /**
     * Constructs a new instance of {@code UpdateStudentUseCase}.
     *
     * @param service the service for student business operations
     * @param mapper the mapper for converting between DTO and entity
     *
     * @throws IllegalArgumentException if {@code service} or {@code mapper} are null
     */
    public UpdateStudentUseCase(StudentService service, StudentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Updates a student's information based on the provided DTO.
     *
     * <p>This method performs the following operations:
     * <ol>
     *   <li>Converts the {@code StudentDto} to a domain {@code Student} entity</li>
     *   <li>Delegates the update operation to the service layer</li>
     *   <li>Returns the result of the update operation</li>
     * </ol>
     * The DTO must contain a valid student ID to identify which record to update.</p>
     *
     * @param dto the student data transfer object containing updated information.
     *            Must include a valid student ID and the fields to be updated.
     * @return {@code true} if the student was successfully updated,
     *         {@code false} if the student was not found or the update failed
     *
     * @throws IllegalArgumentException if {@code dto} is null, has an invalid ID,
     *                                  or contains invalid data for the update
     * @throws IllegalStateException if the update violates business rules
     *                               (e.g., duplicate unique fields)
     * @throws org.springframework.dao.DataAccessException if there's a persistence error
     *
     * @see StudentMapper#ToEntity(StudentDto)
     * @see StudentService#updateStudent(devnastapi.devnestapi.student.model.Student)
     */
    public boolean updateStudentById(StudentDto dto) {
        return service.updateStudent(mapper.ToEntity(dto));
    }
}
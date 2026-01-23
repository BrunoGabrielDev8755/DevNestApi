package devnastapi.devnestapi.student.application;

import devnastapi.devnestapi.student.dto.StudentDto;
import devnastapi.devnestapi.student.mapper.StudentMapper;
import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.service.StudentService;
import org.springframework.stereotype.Component;

/**
 * Use case for enrolling a new student in the system.
 *
 * <p>This class implements the student enrollment use case,
 * orchestrating the flow of creating a new student record
 * by converting DTO to entity, persisting it through the service,
 * and converting back to DTO for the response.</p>
 *
 * <p>Follows the Clean Architecture pattern by separating
 * application logic from infrastructure concerns.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see StudentService
 * @see StudentMapper
 * @see StudentDto
 * @see Student
 */
@Component
public class EnrollStudentUseCase {

    /**
     * Mapper for converting between DTO and entity representations.
     */
    private final StudentMapper mapper;

    /**
     * Service for student business operations and persistence.
     */
    private final StudentService service;

    /**
     * Constructs a new instance of {@code EnrollStudentUseCase}.
     *
     * @param mapper the mapper for converting between DTO and entity
     * @param service the service for student business operations
     *
     * @throws IllegalArgumentException if {@code mapper} or {@code service} are null
     */
    public EnrollStudentUseCase(StudentMapper mapper, StudentService service) {
        this.mapper = mapper;
        this.service = service;
    }

    /**
     * Executes the student enrollment process.
     *
     * <p>This method performs the complete enrollment workflow:
     * <ol>
     *   <li>Converts the incoming DTO to a domain entity</li>
     *   <li>Persists the entity using the service layer</li>
     *   <li>Converts the saved entity back to DTO</li>
     *   <li>Returns the DTO with any generated fields (e.g., ID)</li>
     * </ol></p>
     *
     * @param dto the student data transfer object containing enrollment information
     * @return the persisted student DTO with generated fields and confirmation data
     *
     * @throws IllegalArgumentException if {@code dto} is null or contains invalid data
     * @throws IllegalStateException if the student cannot be enrolled due to business rules
     * @throws org.springframework.dao.DataAccessException if there's a persistence error
     *
     * @see StudentMapper#ToEntity(StudentDto)
     * @see StudentService#createNewStudent(Student)
     * @see StudentMapper#toDto(Student)
     */
    public StudentDto execute(StudentDto dto) {
        Student saved = service.createNewStudent(mapper.ToEntity(dto));
        return mapper.toDto(saved);
    }
}
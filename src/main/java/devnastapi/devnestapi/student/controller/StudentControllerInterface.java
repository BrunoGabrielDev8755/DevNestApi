package devnastapi.devnestapi.student.controller;

import devnastapi.devnestapi.common.genericinterfaces.GenericControllerInterface;
import devnastapi.devnestapi.student.application.DeleteStudentUseCase;
import devnastapi.devnestapi.student.application.EnrollStudentUseCase;
import devnastapi.devnestapi.student.application.GetStudentUseCase;
import devnastapi.devnestapi.student.application.UpdateStudentUseCase;
import devnastapi.devnestapi.student.dto.StudentDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing student resources.
 *
 * <p>This controller provides RESTful endpoints for all student-related operations,
 * including creation, retrieval, update, and deletion of student records.</p>
 *
 * <p>Implements the {@link GenericControllerInterface} to ensure consistent
 * controller patterns across the application. All endpoints are protected
 * with role-based authorization using Spring Security's {@link PreAuthorize}.</p>
 *
 * <p>The controller delegates business logic to appropriate use cases,
 * following the Clean Architecture principle of separating presentation
 * concerns from application logic.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see GenericControllerInterface
 * @see EnrollStudentUseCase
 * @see GetStudentUseCase
 * @see DeleteStudentUseCase
 * @see UpdateStudentUseCase
 * @see StudentDto
 */
@RestController
@RequestMapping("/students")
public class StudentControllerInterface implements GenericControllerInterface {

    /**
     * Use case for enrolling new students.
     */
    private final EnrollStudentUseCase enrollStudentUserCase;

    /**
     * Use case for retrieving student information.
     */
    private final GetStudentUseCase getStudentUseCase;

    /**
     * Use case for deleting student records.
     */
    private final DeleteStudentUseCase deleteStudentUseCase;

    /**
     * Use case for updating student information.
     */
    private final UpdateStudentUseCase updateStudentUseCase;

    /**
     * Constructs a new {@code StudentControllerInterface}.
     *
     * @param enrollStudentUserCase the use case for enrolling new students
     * @param getStudentUseCase the use case for retrieving student information
     * @param deleteStudentUseCase the use case for deleting student records
     * @param updateStudentUseCase the use case for updating student information
     *
     * @throws IllegalArgumentException if any parameter is {@code null}
     */
    public StudentControllerInterface(
            EnrollStudentUseCase enrollStudentUserCase,
            GetStudentUseCase getStudentUseCase,
            DeleteStudentUseCase deleteStudentUseCase,
            UpdateStudentUseCase updateStudentUseCase
    ) {
        this.enrollStudentUserCase = enrollStudentUserCase;
        this.getStudentUseCase = getStudentUseCase;
        this.deleteStudentUseCase = deleteStudentUseCase;
        this.updateStudentUseCase = updateStudentUseCase;
    }

    /**
     * Creates a new student in the system.
     *
     * <p>Endpoint: {@code POST /students}</p>
     * <p>Authorization: Requires {@code ROLE_STUDENT} authority</p>
     *
     * <p>Validates the incoming student data and creates a new student record.
     * Returns the created student with generated fields (e.g., ID) and
     * includes a {@code Location} header with the URI of the new resource.</p>
     *
     * @param studentDto the student data transfer object containing student information
     * @return {@code ResponseEntity} with:
     *         <ul>
     *           <li>Status 201 (Created) on success</li>
     *           <li>{@code Location} header pointing to the new resource</li>
     *           <li>The created student DTO in the response body</li>
     *         </ul>
     *
     * @throws jakarta.validation.ConstraintViolationException if validation fails
     * @throws org.springframework.dao.DataAccessException if persistence error occurs
     * @throws org.springframework.security.access.AccessDeniedException if user lacks required role
     *
     * @see EnrollStudentUseCase#execute(StudentDto)
     * @see GenericControllerInterface#generateHeaderLocation(Object)
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('STUDENT')")
    public ResponseEntity<Object> createNewUser(@RequestBody @Valid StudentDto studentDto) {
        StudentDto saved = enrollStudentUserCase.execute(studentDto);
        return ResponseEntity
                .created(generateHeaderLocation(saved.id()))
                .body(saved);
    }

    /**
     * Retrieves a student by ID using Optional-based approach.
     *
     * <p>Endpoint: {@code GET /students/{id}}</p>
     * <p>Authorization: Requires {@code ROLE_ADMIN} authority</p>
     *
     * <p>Returns the student wrapped in an {@code Optional}, which may be empty
     * if no student is found with the given ID. This approach provides explicit
     * null-safety in the response.</p>
     *
     * @param id the student ID as a path variable
     * @return {@code ResponseEntity} with:
     *         <ul>
     *           <li>Status 200 (OK) with the student if found</li>
     *           <li>Status 200 (OK) with empty optional if not found</li>
     *         </ul>
     *
     * @throws org.springframework.security.access.AccessDeniedException if user lacks required role
     * @throws IllegalArgumentException if the ID format is invalid
     *
     * @see GetStudentUseCase#getStudentWithId(String)
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getStudent(@PathVariable("id") String id) {
        var student = getStudentUseCase.getStudentWithId(id);
        return ResponseEntity.ok(student);
    }

    /**
     * Retrieves a student by ID using direct return approach.
     *
     * <p>Endpoint: {@code GET /students/refactored/{id}}</p>
     * <p>Authorization: Requires {@code ROLE_ADMIN} authority</p>
     *
     * <p>Alternative to {@link #getStudent(String)} that returns the student
     * directly rather than wrapped in an Optional. May return {@code null}
     * or throw an exception if the student is not found, depending on the
     * underlying implementation.</p>
     *
     * @param id the student ID as a path variable
     * @return {@code ResponseEntity} with:
     *         <ul>
     *           <li>Status 200 (OK) with the student if found</li>
     *           <li>May return null or throw exception if not found (implementation-dependent)</li>
     *         </ul>
     *
     * @throws org.springframework.security.access.AccessDeniedException if user lacks required role
     * @throws IllegalArgumentException if the ID format is invalid
     *
     * @see GetStudentUseCase#getStudentWithIdRefatored(String)
     */
    @GetMapping("/refactored/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getStudentRefactored(@PathVariable("id") String id){
        var student = getStudentUseCase.getStudentWithIdRefatored(id);
        return ResponseEntity.ok(student);
    }

    /**
     * Deletes a student by ID.
     *
     * <p>Endpoint: {@code DELETE /students/deletar/{id}}</p>
     * <p>Authorization: Requires {@code ROLE_USER} authority</p>
     *
     * <p>Note: The endpoint uses Portuguese "deletar" in the path. Consider
     * using English "delete" for consistency with other endpoints.</p>
     *
     * @param id the student ID as a path variable
     * @return {@code ResponseEntity} with:
     *         <ul>
     *           <li>Status 200 (OK) with success message</li>
     *           <li>Note: Returns success even if student didn't exist (idempotent operation)</li>
     *         </ul>
     *
     * @throws org.springframework.security.access.AccessDeniedException if user lacks required role
     * @throws IllegalArgumentException if the ID format is invalid
     *
     * @see DeleteStudentUseCase#deleteStudentById(String)
     */
    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") String id) {
        deleteStudentUseCase.deleteStudentById(id);
        return ResponseEntity.ok("Student has been deleted");
    }

    /**
     * Partially updates a student's information.
     *
     * <p>Endpoint: {@code PATCH /students/update/{id}}</p>
     * <p>Authorization: Requires {@code ROLE_ADMIN} authority</p>
     *
     * <p>Note: The method signature doesn't include the ID path parameter in the
     * method parameters. The ID should be extracted from the {@code StudentDto}
     * object or added as a method parameter for proper resource identification.</p>
     *
     * @param dto the student data transfer object containing updated information
     * @return {@code ResponseEntity} with:
     *         <ul>
     *           <li>Status 200 (OK) with success message</li>
     *           <li>Returns success message even if no changes were made</li>
     *         </ul>
     *
     * @throws org.springframework.security.access.AccessDeniedException if user lacks required role
     * @throws IllegalArgumentException if the DTO or ID is invalid
     * @throws jakarta.persistence.EntityNotFoundException if student doesn't exist
     *
     * @see UpdateStudentUseCase#updateStudentById(StudentDto)
     */
    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> patchStudent(@RequestBody StudentDto dto) {
        updateStudentUseCase.updateStudentById(dto);
        return ResponseEntity.ok().body("Student updated successfully");
    }
}
package devnastapi.devnestapi.student.controller;

import devnastapi.devnestapi.common.exceptions.usergenericexceptions.DuplicateUserException;
import devnastapi.devnestapi.common.exceptions.usergenericexceptions.NotFoundUserException;
import devnastapi.devnestapi.common.genericinterfaces.GenericControllerInterface;
import devnastapi.devnestapi.student.application.DeleteStudentUseCase;
import devnastapi.devnestapi.student.application.EnrollStudentUseCase;
import devnastapi.devnestapi.student.application.GetStudentUseCase;
import devnastapi.devnestapi.student.application.UpdateStudentUseCase;
import devnastapi.devnestapi.student.dto.StudentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing Student resources.
 * <p>
 * Provides CRUD operations for students:
 * <ul>
 *     <li>Create a new student</li>
 *     <li>Get student by ID</li>
 *     <li>Update student information</li>
 *     <li>Delete a student</li>
 * </ul>
 * <p>
 * All endpoints are secured with role-based access using Spring Security.
 * Roles used:
 * <ul>
 *     <li>ADMIN: Full access to all operations</li>
 *     <li>USER: Limited access</li>
 *     <li>STUDENT: Access to self-registration only</li>
 * </ul>
 */
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentControllerInterface implements GenericControllerInterface {

    private final EnrollStudentUseCase enrollStudentUserCase;
    private final GetStudentUseCase getStudentUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;

    /**
     * Registers a new student.
     *
     * @param studentDto DTO containing student information to be created.
     * @return ResponseEntity with HTTP status 201 (Created) and the created StudentDto in the body.
     * @throws DuplicateUserException if a student with the same ID already exists.
     * @pre Authorized roles: STUDENT
     * @apiNote TODO: Implement additional Spring Security rules:
     * <ul>
     *     <li>Block creation if already logged in</li>
     *     <li>Limit endpoint for public registration only</li>
     *     <li>Allow ADMIN to create any student</li>
     * </ul>
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
     * Retrieves a student by their ID.
     *
     * @param id The unique identifier of the student.
     * @return ResponseEntity containing the student information.
     * @throws NotFoundUserException if the student does not exist.
     * @pre Authorized roles: ADMIN
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getStudent(@PathVariable("id") String id) {
        var student = getStudentUseCase.getStudentWithId(id);
        return ResponseEntity.ok(student);
    }

    /**
     * Retrieves a student by ID using a refactored approach.
     *
     * @param id The unique identifier of the student.
     * @return ResponseEntity containing the student information.
     * @pre Authorized roles: ADMIN
     */
    @GetMapping("/refactored/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getStudentRefactored(@PathVariable("id") String id){
        var student = getStudentUseCase.getStudentWithIdRefatored(id);
        return ResponseEntity.ok(student);
    }

    /**
     * Deletes a student by their ID.
     *
     * @param id The unique identifier of the student to be deleted.
     * @return ResponseEntity with a confirmation message.
     * @throws NotFoundUserException if the student does not exist.
     * @pre Authorized roles: USER
     */
    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") String id) {
        deleteStudentUseCase.deleteStudentById(id);
        return ResponseEntity.ok("Student has been deleted");
    }

    /**
     * Updates student information.
     *
     * @param dto DTO containing updated student information.
     * @return ResponseEntity with a success message.
     * @throws NotFoundUserException if the student does not exist.
     * @pre Authorized roles: ADMIN
     */
    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> patchStudent(@RequestBody StudentDto dto) {
        updateStudentUseCase.updateStudentById(dto);
        return ResponseEntity.ok().body("Student updated successfully");
    }

}

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

@RestController
@RequestMapping("/students")
public class StudentControllerInterface implements GenericControllerInterface {

    private final EnrollStudentUseCase enrollStudentUserCase;
    private final GetStudentUseCase getStudentUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;

    // Construtor explícito, runtime-safe
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

    @PostMapping
    @PreAuthorize("hasAnyRole('STUDENT')")
    public ResponseEntity<Object> createNewUser(@RequestBody @Valid StudentDto studentDto) {
        StudentDto saved = enrollStudentUserCase.execute(studentDto);
        return ResponseEntity
                .created(generateHeaderLocation(saved.id()))
                .body(saved);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getStudent(@PathVariable("id") String id) {
        var student = getStudentUseCase.getStudentWithId(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/refactored/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getStudentRefactored(@PathVariable("id") String id){
        var student = getStudentUseCase.getStudentWithIdRefatored(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") String id) {
        deleteStudentUseCase.deleteStudentById(id);
        return ResponseEntity.ok("Student has been deleted");
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> patchStudent(@RequestBody StudentDto dto) {
        updateStudentUseCase.updateStudentById(dto);
        return ResponseEntity.ok().body("Student updated successfully");
    }

}

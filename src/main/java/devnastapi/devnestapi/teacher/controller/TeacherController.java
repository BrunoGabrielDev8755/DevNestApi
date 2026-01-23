package devnastapi.devnestapi.teacher.controller;

import devnastapi.devnestapi.common.genericinterfaces.GenericControllerInterface;
import devnastapi.devnestapi.teacher.application.CreateTeacherUseCase;
import devnastapi.devnestapi.teacher.application.DeleteTeacherUseCase;
import devnastapi.devnestapi.teacher.application.GetTeacherUserCase;
import devnastapi.devnestapi.teacher.application.UpdateTeacherUseCase;
import devnastapi.devnestapi.teacher.dto.TeacherDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class TeacherController implements GenericControllerInterface {


    public TeacherController(CreateTeacherUseCase createTeacherUseCase
            , UpdateTeacherUseCase updateTeacherUseCase
            , DeleteTeacherUseCase deleteTeacherUseCase
            , GetTeacherUserCase getTeacherUserCase) {
        this.createTeacherUseCase = createTeacherUseCase;
        this.updateTeacherUseCase = updateTeacherUseCase;
        this.deleteTeacherUseCase = deleteTeacherUseCase;
        this.getTeacherUserCase = getTeacherUserCase;
    }

    private final CreateTeacherUseCase createTeacherUseCase;
    private final GetTeacherUserCase getTeacherUserCase;
    private final DeleteTeacherUseCase deleteTeacherUseCase;
    private final UpdateTeacherUseCase updateTeacherUseCase;


    @PostMapping
    public ResponseEntity<Object>createNewTeacher(@RequestBody @Valid TeacherDto dto){
        var createdTeacher = createTeacherUseCase.createTeacher(dto);
        return ResponseEntity.created(generateHeaderLocation(createdTeacher.getId())).body("Student created successfully! ID:" + createdTeacher.getId());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getTeacherById(@PathVariable("id") String id) {
        var teacher = getTeacherUserCase.getTeacherBYId(id);
        return ResponseEntity.ok(teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("id")String id){
        var deletedTeacher = deleteTeacherUseCase.deleteTeacherById(id);
        return ResponseEntity.ok().body(deletedTeacher);
    }


















}

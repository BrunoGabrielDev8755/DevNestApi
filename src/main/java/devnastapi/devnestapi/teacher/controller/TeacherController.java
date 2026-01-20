package devnastapi.devnestapi.teacher.controller;

import devnastapi.devnestapi.common.genericinterfaces.GenericControllerInterface;
import devnastapi.devnestapi.teacher.application.CreateTeacherUseCase;
import devnastapi.devnestapi.teacher.dto.TeacherDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class TeacherController implements GenericControllerInterface {


    private final CreateTeacherUseCase createTeacherUseCase;
    private final CreateTeacherUseCase getTeacherUserCase;


    @PostMapping
    public ResponseEntity<Object>createNewTeacher(@RequestBody @Valid TeacherDto dto){
        var createdTeacher = createTeacherUseCase.createTeacher(dto);
        return ResponseEntity.created(generateHeaderLocation(createdTeacher.getId())).body("Student created successfully! ID:" + createdTeacher.getId());
    }

    public ResponseEntity<Object> getTeacher(@RequestParam String id){

        return ResponseEntity.ok("");
    }















}

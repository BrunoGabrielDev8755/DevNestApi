package devnastapi.devnestapi.teacher.application;

import devnastapi.devnestapi.teacher.dto.TeacherDto;
import devnastapi.devnestapi.teacher.mapper.TeacherMapper;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateTeacherUseCase {

    TeacherService service;
    TeacherMapper mapper;

    public Teacher createTeacher(TeacherDto dto){
        var teacherSaved = service.createNewTeacher(mapper.dtoToEntity(dto));
        return teacherSaved;
    }

}

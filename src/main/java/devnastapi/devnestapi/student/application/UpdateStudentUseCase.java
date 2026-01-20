package devnastapi.devnestapi.student.application;


import devnastapi.devnestapi.student.dto.StudentDto;
import devnastapi.devnestapi.student.mapper.StudentMapper;
import devnastapi.devnestapi.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UpdateStudentUseCase {

    StudentService service;
    StudentMapper mapper;

    public boolean updateStudentById(StudentDto dto){
        return service.updateStudent(mapper.ToEntity(dto));
    }


}

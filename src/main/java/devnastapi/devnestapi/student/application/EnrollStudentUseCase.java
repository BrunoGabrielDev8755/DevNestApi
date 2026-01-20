package devnastapi.devnestapi.student.application;

import devnastapi.devnestapi.student.dto.StudentDto;
import devnastapi.devnestapi.student.mapper.StudentMapper;
import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EnrollStudentUseCase {

    private final StudentService service;
    private final StudentMapper mapper;

    public StudentDto execute(StudentDto dto) {
        Student saved = service.createNewStudent(mapper.ToEntity(dto));
        return mapper.toDto(saved);
    }


}

package devnastapi.devnestapi.student.mapper;

import devnastapi.devnestapi.student.dto.StudentDto;
import devnastapi.devnestapi.student.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student ToEntity(StudentDto dto);

    StudentDto toDto(Student student);
}

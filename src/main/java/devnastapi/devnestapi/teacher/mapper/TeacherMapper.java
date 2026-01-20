package devnastapi.devnestapi.teacher.mapper;

import devnastapi.devnestapi.teacher.model.Teacher;

import devnastapi.devnestapi.teacher.dto.TeacherDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

     Teacher dtoToEntity(TeacherDto teacherDto);

     Teacher toEntityDto(Teacher teacher);

}

package devnastapi.devnestapi.course.mapper;
import devnastapi.devnestapi.course.dto.CourseDto;
import devnastapi.devnestapi.course.model.Course;
import org.mapstruct.Mapper;


@Mapper(componentModel = "Spring")
public interface CourseMapper {

    Course toEntity(CourseDto dto);

    CourseDto toDto (Course course);


}

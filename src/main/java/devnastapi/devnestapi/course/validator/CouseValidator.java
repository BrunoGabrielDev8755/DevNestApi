package devnastapi.devnestapi.course.validator;

import devnastapi.devnestapi.course.repository.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CouseValidator {

    private CourseRepository courseRepository;

    public boolean courseExistsByName(String name){
        return courseRepository.findByNameOfCourse(name);
    }

    public boolean courseExistsById(UUID id){
        return courseRepository.existsById(id);
    }
}

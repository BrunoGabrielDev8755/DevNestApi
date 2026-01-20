package devnastapi.devnestapi.course.repository;

import devnastapi.devnestapi.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    boolean findByNameOfCourse(String name);

}

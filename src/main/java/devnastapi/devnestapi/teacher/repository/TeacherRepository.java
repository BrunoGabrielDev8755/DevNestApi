package devnastapi.devnestapi.teacher.repository;

import devnastapi.devnestapi.course.model.Course;
import devnastapi.devnestapi.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    boolean existsByEmail(String mail);

    boolean existsById(UUID uuid);

    Teacher findAllByCourses(Course course);

    @Query("select id,  from teacher ")
    Teacher findForAdmin();

    boolean existsAll();
}

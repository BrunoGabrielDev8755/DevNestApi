package devnastapi.devnestapi.course.service;

import devnastapi.devnestapi.course.exceptions.CourseAlredyExistsException;
import devnastapi.devnestapi.course.exceptions.CourseNotFoundException;
import devnastapi.devnestapi.course.model.Course;
import devnastapi.devnestapi.course.repository.CourseRepository;
import devnastapi.devnestapi.course.validator.CouseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service responsible for managing Course entities.
 * <p>
 * Provides CRUD operations and search functionalities for courses.
 * Throws exceptions when business rules are violated.
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CouseValidator validator;

    /**
     * Registers a new course in the database.
     *
     * @param course The Course object containing data to be saved.
     * @return The saved Course object.
     * @throws CourseAlredyExistsException If a course with the same name already exists.
     */
    public Course registerNewCourse(Course course) {
        if (validator.courseExistsByName(course.getNameOfCourse())) {
            throw new CourseAlredyExistsException("This course already exists");
        }
        return courseRepository.save(course);
    }

    /**
     * Deletes a course by its UUID.
     *
     * @param id The UUID of the course to delete.
     * @return true if the course was successfully deleted.
     * @throws CourseNotFoundException If the course does not exist.
     */
    public boolean deleteCourse(UUID id) {
        if (!validator.courseExistsById(id)) {
            throw new CourseNotFoundException("This course does not exist");
        }
        courseRepository.deleteById(id);
        return true;
    }

    /**
     * Retrieves a course by its UUID.
     *
     * @param id The UUID of the course to retrieve.
     * @return Optional containing the found Course.
     * @throws CourseNotFoundException If no course is found with the given ID.
     */
    public Optional<Course> getCourseById(UUID id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("This course does not exist");
        }
        return course;
    }

    /**
     * Searches for courses by name.
     *
     * @param name The name or partial name of the course to search.
     * @return List of courses matching the given name.
     */
    public List<Course> getCourseByName(String name) {

        var course = new Course();
        course.setNameOfCourse(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Course> courseExample = Example.of(course, matcher);

        return courseRepository.findAll(courseExample);
    }
}

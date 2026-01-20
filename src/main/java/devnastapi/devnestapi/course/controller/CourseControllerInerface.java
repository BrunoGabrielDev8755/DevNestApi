package devnastapi.devnestapi.course.controller;

import devnastapi.devnestapi.common.genericinterfaces.GenericControllerInterface;
import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.course.dto.CourseDto;
import devnastapi.devnestapi.course.mapper.CourseMapper;
import devnastapi.devnestapi.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for managing Course resources.
 * <p>
 * Provides endpoints for creating, searching, retrieving, and deleting courses.
 * Access to these routes is restricted based on user roles defined in the system.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseControllerInerface implements GenericControllerInterface {

    private final CourseMapper mapper;
    private final CourseService service;
    private final Helpers helpers;

    /**
     * Creates a new instance of {@link CourseControllerInerface} using constructor injection.
     *
     * @param mapper  Mapper responsible for converting CourseDto to Course entity.
     * @param service Service layer responsible for course business logic.
     * @param helpers Utility helper class for ID conversions and common operations.
     */
    /**
     * Creates a new course in the system.
     * <p>
     * Only users with roles <b>TEACHER</b> or <b>ADMIN</b> are authorized to perform this action.
     *
     * @param dto Data transfer object containing course creation data.
     * @return ResponseEntity containing a success message and the resource's location header.
     */
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createNewCourse(@RequestBody @Valid CourseDto dto) {
        var savedCourse = mapper.toEntity(dto);
        service.registerNewCourse(savedCourse);

        return ResponseEntity.created(generateHeaderLocation(savedCourse.getId()))
                .body("Course created successfully. ID: " + savedCourse.getId());
    }

    /**
     * Deletes a course by its ID.
     * <p>
     * Only users with roles <b>TEACHER</b> or <b>ADMIN</b> can perform this action.
     *
     * @param id The UUID of the course to delete, received as String.
     * @return ResponseEntity confirming the deletion operation.
     */
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable String id) {
        service.deleteCourse(helpers.idFromString(id));
        return ResponseEntity.ok("Course deleted successfully.");
    }

    /**
     * Retrieves all courses whose name partially matches the provided value.
     * <p>
     * Accessible to <b>TEACHER</b>, <b>ADMIN</b>, and <b>STUDENT</b>.
     *
     * @param name A substring or full name to filter courses by name.
     * @return ResponseEntity containing a list of matching courses.
     */
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','STUDENT')")
    @GetMapping("/search")
    public ResponseEntity<Object> getCourseByName(@RequestParam String name) {
        return ResponseEntity.ok(service.getCourseByName(name));
    }

    /**
     * Retrieves a specific course by its ID.
     * <p>
     * Accessible to <b>TEACHER</b>, <b>ADMIN</b>, and <b>STUDENT</b>.
     *
     * @param id The ID of the course, received as String.
     * @return ResponseEntity containing the requested course.
     */
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(service.getCourseById(helpers.idFromString(id)));
    }
}

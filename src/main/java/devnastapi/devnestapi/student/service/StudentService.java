package devnastapi.devnestapi.student.service;

import devnastapi.devnestapi.common.exceptions.usergenericexceptions.DuplicateUserException;
import devnastapi.devnestapi.common.exceptions.usergenericexceptions.NotFoundUserException;
import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.repository.StudentRepository;
import devnastapi.devnestapi.student.validator.StudentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service responsible for CRUD operations of Students.
 *
 * Dependencies injected via constructor:
 * - StudentRepository: access to the database.
 * - StudentValidator: validate business rules.
 * - Helpers: utility methods (e.g., converting String IDs).
 */
@Service
@RequiredArgsConstructor
public class StudentService{

    private final StudentRepository repository; // operations with database
    private final StudentValidator validator;    // validators for Student class
    private final PasswordEncoder encoder;

    /**
     * Creates a new student in the database.
     *
     * @param student The Student object received from the controller (JSON request),
     *                containing data for the new student record.
     * @return The saved Student object.
     * @throws DuplicateUserException If the student already exists in the database.
     */
    public Student createNewStudent(Student student) {
        if (!validator.studentExistsByEmail(student)) {
            throw new DuplicateUserException("This user already exists");
        }
        student.setPassword(encoder.encode(student.getPassword()));
        student.setRoles("STUDENT");
        return repository.save(student);
    }

    public Student getStudentRefactored(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundUserException("Student not found"));
    }


    @Deprecated
    public Optional<Student> getStudent(UUID id) {
        if (!validator.studentExistsById(id)) {
            throw new NotFoundUserException("No student found with this ID");
        }
        return repository.findById(id);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id The ID of the student.
     * @return True if the deletion was successful.
     * @throws NotFoundUserException If no student is found with the given ID.
     */
    public Boolean deleteStudent(UUID id) {
        if (!validator.studentExistsById(id)){
            throw new NotFoundUserException("Student not found");
        }
        repository.deleteById(id);
        return true;
    }


    public Boolean updateStudent(Student student) {

        Student existingStudent = repository.findById(student.getId())
                .orElseThrow(() -> new NotFoundUserException("Student not found"));

        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setName(student.getName());

        repository.save(existingStudent);
        return true;
    }

    /**
     * Searches students by example using name and course.
     * <p>
     * Performs a case-insensitive, partial match search.
     * Fields that are null are ignored.
     * </p>
     *
     * @param name The name of the student (partial match, optional).
     * @param course The course name (partial match, optional).
     * @return A list of students matching the search criteria.
     */
    public List<Student> searchStudentByExample(String name, String course) {
        var student = new Student();
        student.setName(name);
        student.setCourse(course);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Student> studentExample = Example.of(student, matcher);
        return repository.findAll(studentExample);
    }

    public Student searchOnDb(String email) {
        return repository.findByEmail(email);
    }

}

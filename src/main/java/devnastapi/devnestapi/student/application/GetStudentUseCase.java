package devnastapi.devnestapi.student.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class GetStudentUseCase {

    Helpers helpers;
    StudentService service;

    public Optional<Student> getStudentWithId(String id){
        var getStudent = service.getStudent(helpers.idFromString(id));
        return getStudent;
    }

    public Student getStudentWithIdRefatored(String id){
        return service.getStudentRefactored(helpers.idFromString(id));
    }

    


}

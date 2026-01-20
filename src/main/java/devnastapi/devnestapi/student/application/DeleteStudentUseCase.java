package devnastapi.devnestapi.student.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteStudentUseCase {

    StudentService service;
    Helpers helpers;

    public Boolean deleteStudentById(String id){
         return service.deleteStudent(helpers.idFromString(id));
    }



}

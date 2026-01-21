package devnastapi.devnestapi.teacher.service;


import devnastapi.devnestapi.common.exceptions.usergenericexceptions.DuplicateUserException;
import devnastapi.devnestapi.common.exceptions.usergenericexceptions.NotFoundUserException;
import devnastapi.devnestapi.course.model.Course;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.repository.TeacherRepository;
import devnastapi.devnestapi.teacher.validator.TeacherValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherValidator validator;
    private final PasswordEncoder encoder;


    public Teacher createNewTeacher(Teacher teacher){
        if(!validator.teacherExistsByEmail(teacher)){
            throw new DuplicateUserException("this user alredy exists");
        }
            teacher.setPassword(encoder.encode(teacher.getPassword()));
            repository.save(teacher);
        return teacher;
    }

    public boolean deleteTeacher(UUID id){
        if (!validator.teacherExistsById(id)){
            throw new DuplicateUserException("User not found");
        }
        return true;
    }

    public Optional<Teacher> getTeacherById(UUID id){
        if (!validator.teacherExistsById(id)){
            throw new NotFoundUserException("User not found");
        }
        var foundedTeacher = repository.findById(id);
        return foundedTeacher;
    }


    /**
     * TODO: implementar uma forma de pesquisa melhor com queryByExample para
     * professores com o metodo de pesquisa sendo cursos
     * */

    @Deprecated
    List<Teacher> getListofAllTeacherForStudent(Course course){
        return List.of(repository.findAllByCourses(course));
    }

    List<Teacher> getListAllTeacherForAdmin(){
        return repository.findAll();
    }

    
}

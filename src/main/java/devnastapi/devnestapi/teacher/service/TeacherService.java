package devnastapi.devnestapi.teacher.service;


import devnastapi.devnestapi.common.exceptions.usergenericexceptions.DuplicateUserException;
import devnastapi.devnestapi.common.exceptions.usergenericexceptions.NotFoundUserException;
import devnastapi.devnestapi.course.model.Course;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.repository.TeacherRepository;
import devnastapi.devnestapi.teacher.validator.TeacherValidator;
import org.springframework.core.codec.Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherValidator validator;
    private final PasswordEncoder encoder;

    public TeacherService(TeacherRepository repository
            , TeacherValidator validator
            , PasswordEncoder encoder){
        this.repository = repository;
        this.validator = validator;
        this.encoder = encoder;
    }

    public Teacher createNewTeacher(Teacher teacher){
        if(!validator.teacherExistsByEmail(teacher)){
            throw new DuplicateUserException("this user alredy exists");
        }
            Teacher teacherSaved = new Teacher();
            teacherSaved.setPassword(encoder.encode(teacher.getPassword()));
            repository.save(teacherSaved);
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

    public Teacher UpadateTeacherById(UUID id , Teacher teacher){
        if(!validator.teacherExistsById(id)){
            throw new NotFoundUserException("this User dont Exists");
        }
        Teacher teacherUpdated = new Teacher();
        teacherUpdated.setPassword(teacher.getPassword());
        teacherUpdated.setMail(teacherUpdated.getMail());
        teacherUpdated.setName(teacher.getName());
        repository.save(teacherUpdated);

        return teacherUpdated;
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

    public Teacher searchOnDb(String email){
        return repository.findByEmail(email);
    }

    
}

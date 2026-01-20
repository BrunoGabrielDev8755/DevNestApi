package devnastapi.devnestapi.teacher.validator;

import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class TeacherValidator {

    private final TeacherRepository repository;

    public TeacherValidator(TeacherRepository repository){
        this.repository = repository;
    }

    public boolean teacherExistsByEmail(Teacher teacher){
        return repository.existsByEmail(teacher.getEmail());
    }

    public boolean teacherExistsById(UUID id){
        return repository.existsById(id);
    }



}

package devnastapi.devnestapi.teacher.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateTeacherUseCase {

    private final Helpers helpers;

    public UpdateTeacherUseCase(TeacherService service, Helpers helpers) {
        this.service = service;
        this.helpers = helpers;
    }

    private final TeacherService service;

    public boolean UpdateTeacher(String id, Teacher teacher){
        service.UpadateTeacherById(helpers.idFromString(id) ,teacher);
        return true;
    }

}

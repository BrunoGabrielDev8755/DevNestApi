package devnastapi.devnestapi.teacher.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetTeacherUserCase {

    private final Helpers helpers;

    public GetTeacherUserCase(Helpers helpers, TeacherService service) {
        this.helpers = helpers;
        this.service = service;
    }

    private final TeacherService service;

    public Teacher getTeacherBYId(String id){
        Optional<Teacher> teacher = service.getTeacherById(helpers.idFromString(id));
        return teacher.orElse(null);
    }



}

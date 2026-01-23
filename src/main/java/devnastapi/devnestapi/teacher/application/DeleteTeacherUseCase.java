package devnastapi.devnestapi.teacher.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.teacher.service.TeacherService;

public class DeleteTeacherUseCase {

    public DeleteTeacherUseCase(Helpers helpers, TeacherService service) {
        this.helpers = helpers;
        this.service = service;
    }

    private final Helpers helpers;
    private final TeacherService service;

    public String deleteTeacherById(String id){
        service.deleteTeacher(helpers.idFromString(id));
        return "this Teacher he was delete with sucessful";
    }
}

package devnastapi.devnestapi.teacher.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTeacherUseCase {

    public DeleteTeacherUseCase(Helpers helpers, TeacherService service) {
        this.helpers = helpers;
        this.service = service;
    }

    private final Helpers helpers;
    private final TeacherService service;

    public String deleteTeacherById(String id){
        service.deleteTeacher(helpers.idFromString(id));
        return "this docent he was delete with sucessful";
    }
}

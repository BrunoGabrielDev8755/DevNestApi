package devnastapi.devnestapi.teacher.application;

import devnastapi.devnestapi.common.helpers.Helpers;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Stack;
import java.util.UUID;

@RequiredArgsConstructor
public class GetTeacherUserCase {

    private final Helpers helpers;
    private final TeacherService service;

    public Teacher getTeacherBYId(String id){
        service.getTeacherById(helpers.idFromString(id));



        return null;
    }



}

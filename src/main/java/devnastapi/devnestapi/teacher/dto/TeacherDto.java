package devnastapi.devnestapi.teacher.dto;

import java.util.Date;

public record TeacherDto(String name,
                         String course,
                         String password,
                         Date dateOfBirth) {
    @Override
    public String course() {
        return course;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public Date dateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String name() {
        return name;
    }
}

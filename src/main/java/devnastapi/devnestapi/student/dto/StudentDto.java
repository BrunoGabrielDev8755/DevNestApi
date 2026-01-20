package devnastapi.devnestapi.student.dto;

import java.time.LocalDate;
import java.util.UUID;

public record StudentDto(
        UUID id
        , String name
        , String email
        , LocalDate birthDate
        , String password
) {


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }



}

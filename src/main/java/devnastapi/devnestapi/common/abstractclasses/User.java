package devnastapi.devnestapi.common.abstractclasses;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
public class User {

    private String name;

    private String email;

    private LocalDate dateOfBirth;

    private String password;

    private String course;

    private LocalDate lastModify;

    private LocalDate createDate;

    private String roles;

}

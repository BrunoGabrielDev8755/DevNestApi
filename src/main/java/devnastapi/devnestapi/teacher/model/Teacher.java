package devnastapi.devnestapi.teacher.model;

import devnastapi.devnestapi.common.abstractclasses.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.UUID;

@Data
@Table
@Entity(name = "teacher")
public class Teacher extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    UUID id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "A senha não pode estar vazia")
    @Column(name = "password" ,nullable = false)
    String password;

    @NotBlank(message = "O email não pode estar vazio")
    @Column(name = "mail" , nullable = false)
    String mail;

    @NotBlank(message = "O nome do curso não pode estar vazio")
    @OneToMany(mappedBy = "name_of_course")
    @Column(name = "course", nullable = false)
    String Course;

    @CreatedDate
    Date dateOfCreation;

    @LastModifiedDate
    Date lastModifyDate;
}

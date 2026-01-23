package devnastapi.devnestapi.teacher.model;

import devnastapi.devnestapi.common.genericclasses.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.UUID;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UUID getId() {
        return id;
    }

    public String getCourse() {
        return Course;
    }

    @NotBlank(message = "A senha não pode estar vazia")
    @Column(name = "password" ,nullable = false)
    String password;

    @NotBlank(message = "O email não pode estar vazio")
    @Column(name = "mail" , nullable = false)
    String mail;

    @NotBlank(message = "O nome do curso não pode estar vazio")
    @OneToMany(mappedBy = "name_of_course")
    @Column(name = "course")
    String Course;

    @CreatedDate
    Date dateOfCreation;

    @LastModifiedDate
    Date lastModifyDate;
}

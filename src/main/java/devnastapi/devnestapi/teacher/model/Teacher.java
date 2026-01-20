package devnastapi.devnestapi.teacher.model;

import devnastapi.devnestapi.people.model.People;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.UUID;

@Table
@Entity(name = "teacher")
public class Teacher extends People {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    UUID id;

    @Column(name = "password")
    String password;

    @Column(name = "mail")
    String mail;

    @OneToMany(mappedBy = "name_of_course")
    @Column(name = "course")
    String Course;

    @CreatedDate
    Date dateOfCreation;

    @LastModifiedDate
    Date lastModifyDate;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}

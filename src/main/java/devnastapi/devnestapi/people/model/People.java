package devnastapi.devnestapi.people.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.UUID;

public abstract class People
{
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String password;


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Entity
    @Table(name = "student", schema = "public")
    public static class Student extends People {

        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.UUID)
        @Id
        UUID id;

        @Column(name = "name", nullable = false)
        String name;

        @Column(name = "password", nullable = false)
        String password;

        @Column(name = "mail" , nullable = false)
        String email;

        @Column(name = "date_of_birth", nullable = false)
        LocalDate dateOfBirth;

        @Column(name = "course", nullable = false)
        String Course;

        @LastModifiedDate
        @Column(name = "last_modify")
        LocalDate lastModify;

        @CreatedDate
        @Column(name = "create_date")
        LocalDate createDate;



        @OneToMany(mappedBy = "course")




        public LocalDate getCreateDate() {
            return createDate;
        }

        public void setCreateDate(LocalDate createDate) {
            this.createDate = createDate;
        }

        public LocalDate getLastModify() {
            return lastModify;
        }

        public void setLastModify(LocalDate lastModify) {
            this.lastModify = lastModify;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public void setCourse(String course){
            this.Course = course;
        }


    }
}

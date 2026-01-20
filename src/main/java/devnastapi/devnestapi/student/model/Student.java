package devnastapi.devnestapi.student.model;

import devnastapi.devnestapi.people.model.People;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Represents a student in the system.
 * <p>
 * Extends {@link People} to inherit common personal information.
 * Stores basic data such as name, email, password, date of birth, course, and timestamps.
 */
@Entity
@Table(name = "student", schema = "public")
public class Student extends People {

    /**
     * Unique identifier for the student.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    /**
     * Full name of the student.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Password for authentication (should be stored hashed).
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Email of the student.
     */
    @Column(name = "mail", nullable = false)
    private String email;

    /**
     * Date of birth of the student.
     */
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Name of the course the student is enrolled in.
     */
    @Column(name = "course", nullable = false)
    private String course;

    /**
     * Last modification date of the student record.
     */
    @LastModifiedDate
    @Column(name = "last_modify")
    private LocalDate lastModify;

    /**
     * Creation date of the student record.
     */
    @CreatedDate
    @Column(name = "create_date")
    private LocalDate createDate;

    @Type(ListArrayType.class)
    @Column(name = "roles" , columnDefinition = "varchar[]")
    private List<String> roles;

    // ---------- Getters and Setters ----------

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public LocalDate getLastModify() {
        return lastModify;
    }

    public void setLastModify(LocalDate lastModify) {
        this.lastModify = lastModify;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

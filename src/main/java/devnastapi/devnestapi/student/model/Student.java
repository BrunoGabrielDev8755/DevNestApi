package devnastapi.devnestapi.student.model;

import devnastapi.devnestapi.common.genericclasses.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a student entity in the system.
 *
 * <p>Extends {@link User} to inherit common personal information such as identification or general contact details.</p>
 *
 * <p>This class contains all necessary information about a student, including authentication data, personal details,
 * enrollment information, roles, and timestamps for creation and modification.</p>
 *
 * <p>Stored in the database table <b>student</b> under schema <b>public</b>.</p>
 */
@Data
@Entity
@Table(name = "student", schema = "public")
public class Student extends User{

    /**
     * Unique identifier for the student.
     * Automatically generated using UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    /**
     * Full name of the student.
     * Cannot be null.
     */
    @NotBlank(message = "")
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Password used for authentication.
     * <p>Should be stored in a hashed format for security purposes.</p>
     * Cannot be null.
     */
    @NotBlank(message = "")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Email address of the student.
     * Must be unique and cannot be null.
     */
    @NotBlank()
    @Column(name = "mail", nullable = false)
    private String email;

    /**
     * Date of birth of the student.
     * Cannot be null.
     */
    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Course in which the student is enrolled.
     * Cannot be null.
     */
    @Column(name = "course", nullable = false)
    private String course;

    /**
     * Date when the student record was last modified.
     * Automatically updated on record changes.
     */

    @LastModifiedDate
    @Column(name = "last_modify")
    private LocalDate lastModify;

    /**
     * Date when the student record was created.
     * Automatically set on record creation.
     */
    @CreatedDate
    @Column(name = "create_date")
    private LocalDate createDate;

    /**
     * Roles assigned to the student for authorization purposes.
     * Stored as a string array in the database.
     */
    @Column(name = "roles")
    private String roles;
}

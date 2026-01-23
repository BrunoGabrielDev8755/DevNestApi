package devnastapi.devnestapi.student.model;

import devnastapi.devnestapi.common.genericclasses.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a student entity in the system.
 *
 * <p>Extends {@link User} to inherit common personal information such as
 * identification or general contact details.</p>
 *
 * <p>This class contains all necessary information about a student, including
 * authentication data, personal details, enrollment information, roles,
 * and timestamps for creation and modification.</p>
 *
 * <p>Stored in the database table <b>student</b> under schema <b>public</b>.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
 * @see User
 */
@Entity
@Table(name = "student", schema = "public")
public class Student extends User {

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
     * Cannot be null or empty.
     */
    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Password used for authentication.
     * <p>Should be stored in a hashed format for security purposes.</p>
     * Cannot be null or empty.
     */
    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Email address of the student.
     * Must be unique and cannot be null or empty.
     * <p>Note: The database column is named "mail" but the field is called "email".</p>
     */
    @NotBlank(message = "Email is required")
    @Column(name = "mail", nullable = false)
    private String email;

    /**
     * Date of birth of the student.
     * Cannot be null.
     */
    @NotNull(message = "Date of birth is required")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Course in which the student is enrolled.
     * Cannot be null.
     */
    @NotBlank(message = "Course is required")
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
     * <p>Stored as a string in the database. Consider using a separate
     * entity or collection for roles if multiple roles are needed.</p>
     */
    @Column(name = "roles")
    private String roles;

    /**
     * Returns the unique identifier of the student.
     *
     * @return the student's UUID, may be {@code null} for unsaved entities
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the student's full name.
     *
     * @return the student's name, never {@code null}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's full name.
     *
     * @param name the new name for the student, must not be {@code null} or empty
     * @throws IllegalArgumentException if {@code name} is {@code null} or empty
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the student's password (hashed).
     *
     * <p><strong>Security Note:</strong> This method returns the hashed password.
     * Never return or log plaintext passwords.</p>
     *
     * @return the hashed password, never {@code null}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the student's password.
     *
     * <p><strong>Security Note:</strong> The password should already be hashed
     * before being set. Consider using a password encoder service.</p>
     *
     * @param password the hashed password, must not be {@code null} or empty
     * @throws IllegalArgumentException if {@code password} is {@code null} or empty
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the student's email address.
     *
     * @return the student's email address, never {@code null}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email address.
     *
     * @param email the new email address, must not be {@code null} or empty
     * @throws IllegalArgumentException if {@code email} is {@code null}, empty, or invalid
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the student's date of birth.
     *
     * @return the student's date of birth, never {@code null}
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the student's date of birth.
     *
     * @param dateOfBirth the new date of birth, must not be {@code null}
     * @throws IllegalArgumentException if {@code dateOfBirth} is {@code null} or in the future
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the course in which the student is enrolled.
     *
     * @return the student's course, never {@code null}
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the course in which the student is enrolled.
     *
     * @param course the new course, must not be {@code null} or empty
     * @throws IllegalArgumentException if {@code course} is {@code null} or empty
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Returns the roles assigned to the student.
     *
     * <p>Roles are stored as a string. For multiple roles, consider if they
     * are comma-separated or use a different format.</p>
     *
     * @return the student's roles, may be {@code null}
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Sets the roles assigned to the student.
     *
     * @param roles the new roles for the student
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * Returns the date when the student record was last modified.
     *
     * @return the last modification date, may be {@code null} if never modified
     */
    public LocalDate getLastModify() {
        return lastModify;
    }

    /**
     * Returns the date when the student record was created.
     *
     * @return the creation date, may be {@code null} for unsaved entities
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    // Note: Setters for lastModify and createDate are typically not provided
    // as they are managed by Spring Data/JPA annotations
}
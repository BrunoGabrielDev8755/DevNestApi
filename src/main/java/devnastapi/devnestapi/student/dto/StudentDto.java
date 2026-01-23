package devnastapi.devnestapi.student.dto;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for student information.
 *
 * <p>This record represents student data as it is transferred between layers
 * of the application, particularly between the controller and service layers.
 * It encapsulates all necessary information about a student while avoiding
 * direct exposure of the domain entity.</p>
 *
 * <p>As a record, it provides immutable data carrier semantics with automatic
 * implementation of {@code equals()}, {@code hashCode()}, and {@code toString()}
 * methods based on its components. Additional getter methods are provided
 * for compatibility with frameworks that expect JavaBean-style accessors.</p>
 *
 * <p><strong>Note:</strong> The {@code password} field is included in the DTO,
 * which may be a security concern. Consider using separate DTOs for creation
 * (with password) and retrieval (without password) to prevent accidental
 * password exposure in API responses.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @param id the unique identifier of the student, may be {@code null} for new students
 * @param name the full name of the student, must not be {@code null} or empty
 * @param email the email address of the student, must be a valid email format
 * @param birthDate the date of birth of the student, must be in the past
 * @param password the student's password (plaintext for input, hashed for output),
 *                 may be {@code null} for retrieval operations
 *
 * @see java.lang.Record
 * @see java.util.UUID
 * @see java.time.LocalDate
 */
public record StudentDto(
        UUID id,
        String name,
        String email,
        LocalDate birthDate,
        String password
) {

    /**
     * Returns the student's birth date.
     *
     * <p>This getter method is provided for compatibility with frameworks
     * that expect JavaBean-style property accessors. The record's component
     * accessor {@link #birthDate()} should be preferred in modern code.</p>
     *
     * @return the student's birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Returns the student's password.
     *
     * <p><strong>Security Note:</strong> This method exposes the password field.
     * When this DTO is used in API responses, ensure the password is either:
     * <ul>
     *   <li>Hashed/encrypted</li>
     *   <li>Excluded from serialization using {@code @JsonIgnore} or similar</li>
     *   <li>Replaced with a separate DTO without password field</li>
     * </ul></p>
     *
     * @return the student's password (may be plaintext or hashed depending on context)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the student's email address.
     *
     * <p>This getter method is provided for compatibility with frameworks
     * that expect JavaBean-style property accessors. The record's component
     * accessor {@link #email()} should be preferred in modern code.</p>
     *
     * @return the student's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the student's name.
     *
     * <p>This getter method is provided for compatibility with frameworks
     * that expect JavaBean-style property accessors. The record's component
     * accessor {@link #name()} should be preferred in modern code.</p>
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student's unique identifier.
     *
     * <p>This getter method is provided for compatibility with frameworks
     * that expect JavaBean-style property accessors. The record's component
     * accessor {@link #id()} should be preferred in modern code.</p>
     *
     * @return the student's unique identifier, may be {@code null} for new students
     */
    public UUID getId() {
        return id;
    }
}
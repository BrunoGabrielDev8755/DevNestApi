package devnastapi.devnestapi.student.repository;

import devnastapi.devnestapi.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for {@link Student} entities.
 *
 * <p>This interface extends {@link JpaRepository} to provide CRUD operations
 * and additional query methods for {@link Student} entities. It leverages
 * Spring Data JPA's repository abstraction to reduce boilerplate code.</p>
 *
 * <p>Key features provided by this repository:
 * <ul>
 *   <li>Standard CRUD operations (save, findById, findAll, delete, etc.)</li>
 *   <li>Pagination and sorting support through {@link JpaRepository}</li>
 *   <li>Custom query methods using Spring Data's query derivation mechanism</li>
 *   <li>Transaction management (methods are transactional by default)</li>
 * </ul></p>
 *
 * <p><strong>Query Derivation:</strong> Spring Data JPA automatically implements
 * query methods based on their names. The method names follow the pattern
 * {@code [action]By[property]}, where property names must match entity field names.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see Student
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.repository.CrudRepository
 */
public interface StudentRepository extends JpaRepository<Student, UUID> {

    /**
     * Checks if a student exists with the given email address.
     *
     * <p>This method performs a case-sensitive search for the exact email.
     * The query is automatically derived from the method name:
     * {@code existsByEmail} → {@code SELECT COUNT(s) > 0 FROM Student s WHERE s.email = ?1}</p>
     *
     * <p><strong>Performance Note:</strong> This method is optimized by Spring Data JPA
     * to execute a count query rather than fetching the entire entity.</p>
     *
     * @param email the email address to check, must not be {@code null} or empty
     * @return {@code true} if a student exists with the given email, {@code false} otherwise
     *
     * @throws IllegalArgumentException if {@code email} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see Student#getEmail()
     */
    boolean existsByEmail(String email);

    /**
     * Finds a student by their email address.
     *
     * <p>This method performs a case-sensitive search for the exact email.
     * The query is automatically derived from the method name:
     * {@code findByEmail} → {@code SELECT s FROM Student s WHERE s.email = ?1}</p>
     *
     * <p><strong>Note:</strong> This method assumes email uniqueness. If multiple
     * students could have the same email, consider using {@link #findByEmail(String)}
     * or ensure database-level uniqueness constraints.</p>
     *
     * <p><strong>Note on Naming:</strong> The entity field is named {@code email}
     * but the database column is named {@code mail}. Spring Data JPA uses the
     * entity field name, not the column name, for query derivation.</p>
     *
     * @param email the email address to search for, must not be {@code null} or empty
     * @return the {@link Student} with the given email, or {@code null} if not found
     *
     * @throws IllegalArgumentException if {@code email} is {@code null}
     * @throws org.springframework.dao.DataAccessException if a database error occurs
     *
     * @see Student#getEmail()
     */
    Student findByEmail(String email);
}
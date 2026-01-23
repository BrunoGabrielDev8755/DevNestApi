package devnastapi.devnestapi.student.authservice;

import devnastapi.devnestapi.student.model.Student;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Custom {@link Authentication} implementation for student authentication.
 *
 * <p>This class wraps a {@link Student} object and implements the Spring Security
 * {@link Authentication} interface, providing authentication information for
 * student users in the system.</p>
 *
 * <p>It assigns the "ROLE_STUDENT" authority to authenticated students and
 * exposes student information through the standard Spring Security authentication
 * methods.</p>
 *
 * <p>This implementation is immutable once created and always returns
 * {@code true} from {@link #isAuthenticated()} as it represents an already
 * authenticated student.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see Authentication
 * @see Student
 * @see GrantedAuthority
 */
public class StudentAuthentication implements Authentication {

    /**
     * The authenticated student entity.
     */
    private final Student student;

    /**
     * Constructs a new {@code StudentAuthentication} instance.
     *
     * @param student the authenticated student entity, must not be {@code null}
     *
     * @throws IllegalArgumentException if {@code student} is {@code null}
     */
    public StudentAuthentication(Student student) {
        this.student = student;
    }

    /**
     * Returns the authorities granted to the student.
     *
     * <p>Students are granted the "ROLE_STUDENT" authority by default.
     * This authority can be used in Spring Security expressions and
     * authorization checks.</p>
     *
     * @return an unmodifiable collection containing the "ROLE_STUDENT" authority
     *
     * @see GrantedAuthority
     * @see SimpleGrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

    /**
     * Returns the credentials that prove the principal's identity.
     *
     * <p>In this implementation, returns the student's password hash
     * or authentication token. The exact format depends on the
     * authentication mechanism used.</p>
     *
     * @return the student's credentials (typically password hash), or {@code null}
     */
    @Override
    public @Nullable Object getCredentials() {
        return student.getPassword();
    }

    /**
     * Returns additional details about the authentication request.
     *
     * <p>Returns the complete {@link Student} entity, providing full
     * access to student information for auditing or additional processing.</p>
     *
     * @return the complete {@link Student} entity
     */
    @Override
    public @Nullable Object getDetails() {
        return student;
    }

    /**
     * Returns the identity of the principal being authenticated.
     *
     * <p>Returns the {@link Student} entity representing the authenticated
     * user's identity.</p>
     *
     * @return the {@link Student} entity
     */
    @Override
    public @Nullable Object getPrincipal() {
        return student;
    }

    /**
     * Indicates whether the authentication is complete and successful.
     *
     * <p>This implementation always returns {@code true} as it represents
     * an already authenticated student. The {@link #setAuthenticated(boolean)}
     * method is a no-op and cannot change this state.</p>
     *
     * @return {@code true} always, indicating the student is authenticated
     */
    @Override
    public boolean isAuthenticated() {
        return true;
    }

    /**
     * Sets the authenticated state.
     *
     * <p>This implementation does nothing as {@code StudentAuthentication}
     * represents an already authenticated principal. The state cannot be
     * changed after creation.</p>
     *
     * @param isAuthenticated the authenticated flag (ignored in this implementation)
     * @throws IllegalArgumentException if called with {@code false} (not applicable here)
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // No-op: StudentAuthentication represents an already authenticated principal
    }

    /**
     * Returns the name of the authenticated principal.
     *
     * <p>Returns the student's name from the {@link Student} entity.
     * This is typically used for display purposes and logging.</p>
     *
     * @return the student's name
     */
    @Override
    public String getName() {
        return student.getName();
    }
}
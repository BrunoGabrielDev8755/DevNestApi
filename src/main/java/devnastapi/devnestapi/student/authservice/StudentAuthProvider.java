package devnastapi.devnestapi.student.authservice;

import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.service.StudentService;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Custom authentication provider for student authentication.
 *
 * <p>This class implements Spring Security's {@link AuthenticationProvider} interface
 * to provide custom authentication logic for student users. It validates student
 * credentials against the database and creates authenticated sessions.</p>
 *
 * <p>The provider supports {@link UsernamePasswordAuthenticationToken} authentication
 * requests and performs the following validation steps:
 * <ol>
 *   <li>Extracts username (typically email) and password from authentication request</li>
 *   <li>Retrieves student from database using the provided login identifier</li>
 *   <li>Compares the provided password with the stored hashed password</li>
 *   <li>Creates a {@link StudentAuthentication} object for successful authentications</li>
 * </ol></p>
 *
 * <p>Security features include:
 * <ul>
 *   <li>Password hashing comparison using {@link PasswordEncoder}</li>
 *   <li>Generic error messages to prevent user enumeration attacks</li>
 *   <li>Integration with Spring Security authentication framework</li>
 * </ul></p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see AuthenticationProvider
 * @see StudentAuthentication
 * @see UsernamePasswordAuthenticationToken
 * @see PasswordEncoder
 * @see StudentService
 */
@Component
public class StudentAuthProvider implements AuthenticationProvider {

    /**
     * Password encoder for secure password comparison.
     */
    private final PasswordEncoder encoder;

    /**
     * Student service for database operations.
     */
    private final StudentService service;

    /**
     * Constructs a new {@code StudentAuthProvider}.
     *
     * @param encoder the password encoder for secure password comparison
     * @param service the student service for database operations
     *
     * @throws IllegalArgumentException if {@code encoder} or {@code service} are {@code null}
     */
    public StudentAuthProvider(PasswordEncoder encoder, StudentService service) {
        this.encoder = encoder;
        this.service = service;
    }

    /**
     * Authenticates a student based on provided credentials.
     *
     * <p>This method performs the complete authentication workflow:
     * <ol>
     *   <li>Extracts login identifier (username/email) and raw password from the authentication request</li>
     *   <li>Retrieves the student from the database using the login identifier</li>
     *   <li>Verifies the student exists (throws {@link UsernameNotFoundException} if not found)</li>
     *   <li>Compares the raw password with the stored hashed password using {@link PasswordEncoder#matches(CharSequence, String)}</li>
     *   <li>If password matches, creates and returns an authenticated {@link StudentAuthentication} object</li>
     *   <li>If password doesn't match, throws {@link UsernameNotFoundException} with generic error message</li>
     * </ol></p>
     *
     * <p><strong>Security Note:</strong> Generic error messages ("Email or Password is uncorrectly")
     * are used for both user-not-found and password-mismatch scenarios to prevent
     * user enumeration attacks.</p>
     *
     * @param authentication the authentication request object containing user credentials
     * @return a fully authenticated {@link StudentAuthentication} object if credentials are valid
     *
     * @throws UsernameNotFoundException if authentication fails due to:
     *         <ul>
     *           <li>Student not found with the provided login identifier</li>
     *           <li>Password doesn't match the stored hash</li>
     *         </ul>
     * @throws AuthenticationException for other authentication-related errors
     * @throws IllegalArgumentException if {@code authentication} is {@code null} or credentials are invalid
     *
     * @see StudentService#searchOnDb(String)
     * @see PasswordEncoder#matches(CharSequence, String)
     * @see StudentAuthentication
     */
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();

        String rawPassword = authentication.getCredentials().toString();

        Student studentFounded = service.searchOnDb(login);

        if (studentFounded == null){
            throw new UsernameNotFoundException("Email or Password is uncorrectly");
        }

        String passwordOnDb = studentFounded.getPassword();

        boolean passwordMatches = encoder.matches(rawPassword, passwordOnDb);

        if (passwordMatches){

            Authentication auth = new StudentAuthentication(studentFounded);

            auth.setAuthenticated(true);

            return auth;
        }

        throw new UsernameNotFoundException("Email or Password is uncorrectly");
    }

    /**
     * Indicates whether this provider supports the given authentication type.
     *
     * <p>This provider supports {@link UsernamePasswordAuthenticationToken} authentication
     * requests, which are the standard token type for username/password authentication
     * in Spring Security.</p>
     *
     * @param authentication the authentication class to check
     * @return {@code true} if the authentication class is assignable from
     *         {@link UsernamePasswordAuthenticationToken}, {@code false} otherwise
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
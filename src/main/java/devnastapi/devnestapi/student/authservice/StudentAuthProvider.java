package devnastapi.devnestapi.student.authservice;

import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentAuthProvider implements AuthenticationProvider {

    private final PasswordEncoder encoder;
    private final StudentService service;

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

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}

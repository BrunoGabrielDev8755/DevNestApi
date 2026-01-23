package devnastapi.devnestapi.student.authservice;

import devnastapi.devnestapi.student.model.Student;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


@RequiredArgsConstructor
public class StudentAuthentication implements Authentication {

    public StudentAuthentication(Student student) {
        this.student = student;
    }

    private final Student student;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

    @Override
    public @Nullable Object getCredentials() {
        return student.getPassword();
    }

    @Override
    public @Nullable Object getDetails() {
        return student;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return student;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

    @Override
    public String getName() {
        return student.getName();
    }
}

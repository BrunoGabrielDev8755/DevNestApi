package devnastapi.devnestapi.student.authservice;

import devnastapi.devnestapi.common.genericclasses.CustomUserDetailsService;
import devnastapi.devnestapi.student.model.Student;
import devnastapi.devnestapi.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class StudentUserDetailsService extends CustomUserDetailsService{

   private final StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentService.searchOnDb(email);
        if(student == null){
            throw new UsernameNotFoundException("This User dont Exists");
        }
        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .roles(student.getRoles())
                .build();
    }
}

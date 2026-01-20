package devnastapi.devnestapi.student.authservice;


import devnastapi.devnestapi.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class StudentUserDetailsService {

   private final StudentService service;


    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{



        return null;
    }

}

package devnastapi.devnestapi.teacher.authService;

import devnastapi.devnestapi.common.genericclasses.CustomUserDetailsService;
import devnastapi.devnestapi.teacher.model.Teacher;
import devnastapi.devnestapi.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
public class TeacherUserDetailsService extends CustomUserDetailsService {

    private final TeacherService teacherService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Teacher teacher = teacherService.searchOnDb(email);
            if(teacher == null){
                throw new UsernameNotFoundException("This User dont Exists");
            }
            return User.builder()
                    .username(teacher.getEmail())
                    .password(teacher.getPassword())
                    .roles(teacher.getRoles())
                    .build();
        }

}

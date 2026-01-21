package devnastapi.devnestapi.common.security;

import devnastapi.devnestapi.student.authservice.StudentUserDetailsService;
import devnastapi.devnestapi.student.service.StudentService;
import devnastapi.devnestapi.teacher.authService.TeacherUserDetailsService;
import devnastapi.devnestapi.teacher.service.TeacherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/students/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/courses/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/courses/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/courses/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.PATCH, "/courses/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/courses/**").authenticated()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public UserDetailsService studentUserDetailsService(StudentService studentService) {
        return new StudentUserDetailsService(studentService);
    }

    @Bean
    public UserDetailsService teacherUserDetailsService(TeacherService teacherService) {
        return new TeacherUserDetailsService (teacherService);
    }




    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(userDetailsService); // UserDetailsService no construtor

        authProvider.setPasswordEncoder(encoder);   // seta o encoder

        return authProvider;
    }

}
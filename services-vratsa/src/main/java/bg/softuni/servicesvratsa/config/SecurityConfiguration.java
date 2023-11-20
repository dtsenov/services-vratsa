package bg.softuni.servicesvratsa.config;

import bg.softuni.servicesvratsa.repository.UserRepository;
import bg.softuni.servicesvratsa.service.impl.ServicesVratsaUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${services-vratsa.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/", "/users/login", "/users/register", "/about", "/users/login-error").permitAll()
                                .requestMatchers("/services/add").hasRole("BOSS")
                                .requestMatchers("/products/add").hasRole("BOSS")
                                .requestMatchers("/products/all").permitAll()
                                .requestMatchers("/services/all").permitAll()
                                .requestMatchers(HttpMethod.POST, "/products/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf().disable()
                .formLogin(
                        formLogin -> {
                            formLogin
                                    .loginPage("/users/login")
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .defaultSuccessUrl("/")
                                    .failureForwardUrl("/users/login-error");
                        }
                )
                .logout(
                        logout -> {
                            logout
                                    .logoutUrl("/users/logout")
                                    .logoutSuccessUrl("/")
                                    .invalidateHttpSession(true);
                        }
                )
                .rememberMe(
                        rememberMe -> {
                            rememberMe
                                    .key(rememberMeKey)
                                    .rememberMeParameter("rememberme")
                                    .rememberMeCookieName("rememberme");
                        }
                ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ServicesVratsaUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}

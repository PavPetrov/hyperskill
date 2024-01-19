package account.security;

import account.service.AppUserDetailsServiceImpl;
import account.config.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;


@Configuration
public class SecurityConfiguration {
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    public SecurityConfiguration(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.exceptionHandling(ex -> ex.authenticationEntryPoint(restAuthenticationEntryPoint)) // Handle auth errors
                .csrf(csrf -> {
                    csrf.disable();
                }) // For Postman
                //     .csrf(cfg -> cfg.disable()).headers(cfg -> cfg.frameOptions().disable())
                //      .headers(headers -> headers.frameOptions().disable()) // For the H2 console
                .authorizeHttpRequests(auth -> auth  // manage access
                                //        .requestMatchers(regexMatcher(".*h2-console.*")).permitAll()
                                .requestMatchers("/actuator/shutdown", "/error/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                                //             .requestMatchers(HttpMethod.GET, "/api/empl/payment").authenticated()
                                .anyRequest().authenticated()
                        // other matchers
                )
                .sessionManagement(sessions -> sessions
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}



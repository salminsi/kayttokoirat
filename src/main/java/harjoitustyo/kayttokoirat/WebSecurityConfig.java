package harjoitustyo.kayttokoirat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//tieto minne url-osoitteisiin on pääsyoikeudet
@Configuration
@EnableMethodSecurity(securedEnabled = true)

public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // salasanan kryptaus
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dogs**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/dogs**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/dogs**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/dogs**").hasAuthority("ADMIN")
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/saveuser").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults() // kirjautuminen Postmanilla
                )
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions
                        .disable()) // h2-konsolille
                )
                .formLogin(formlogin -> formlogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/main", true)
                        .permitAll())
                .logout(logout -> logout
                        .permitAll())
                .csrf(csrf -> csrf.disable() // for h2console.
                );
        return http.build();
    }

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}

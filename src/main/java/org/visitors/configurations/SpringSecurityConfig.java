package org.visitors.configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.visitors.controllers.PlainTextPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
    	return PlainTextPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                        		.requestMatchers("/").permitAll()
                                .requestMatchers("/index").permitAll()
                                
                                .requestMatchers("/users").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/users/edit").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/users/load/{userId}/**").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/users/delete/{userId}/**").hasAnyRole("Registrator", "System Administrator")

                                .requestMatchers("/visitors").permitAll()
                                .requestMatchers("/visitors/appointments/load/{visitorId}").permitAll()
                                .requestMatchers("/visitors/edit").permitAll()
                                .requestMatchers("/visitors/load/{visitorId}/**").permitAll()
                                .requestMatchers("/visitors/delete/{visitorId}/**").permitAll()

                                .requestMatchers("/roles").hasAnyRole( "System Administrator")
                                .requestMatchers("/roles/edit").hasAnyRole( "System Administrator")
                                .requestMatchers("/roles/load/{roleId}/**").hasAnyRole( "System Administrator")
                                .requestMatchers("/roles/delete/{roleId}/**").hasAnyRole( "System Administrator")

                                .requestMatchers("/appointments").hasAnyRole("Personnel", "Registrator", "System Administrator")
                                .requestMatchers("/appointments/edit").hasAnyRole("Personnel", "Registrator", "System Administrator")
                                .requestMatchers("/appointments/load/{appointmentId}/**").hasAnyRole("Personnel", "Registrator", "System Administrator")
                                .requestMatchers("/appointments/delete/{appointmentId}/**").hasAnyRole("Personnel", "Registrator", "System Administrator")

                                .requestMatchers("/registries").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/registries/edit").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/registries/load/{registrieId}/**").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/registries/visitor/**").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/registries/delete/{registrieId}/**").hasAnyRole("Registrator", "System Administrator")
                                .requestMatchers("/registries/delete").hasAnyRole("Registrator", "System Administrator")

                                .requestMatchers("/waitinglist").permitAll()
                                .requestMatchers("/callPatient").hasAnyRole("Personnel", "System Administrator")
                                .requestMatchers("/callPatient/**").hasAnyRole("Personnel", "System Administrator")
                                .requestMatchers("/checkPatient").hasAnyRole("Personnel", "System Administrator")
                                .requestMatchers("/checkPatient/**").hasAnyRole("Personnel", "System Administrator")
                                .requestMatchers("/checkPatient/startCheck").hasAnyRole("Personnel", "System Administrator")
                                .requestMatchers("/checkPatient/returnToWaitingList").hasAnyRole("Personnel", "System Administrator")
                                .requestMatchers("/checkPatient/finishCheck").hasAnyRole("Personnel", "System Administrator")

                                .requestMatchers("/editprofile").permitAll()
                                .requestMatchers("/profile").permitAll()
                                .requestMatchers("/static").permitAll()
                                .requestMatchers("/images").permitAll()
                                .requestMatchers("/favicon.ico").permitAll()
                                .requestMatchers("/styles.css").permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
}
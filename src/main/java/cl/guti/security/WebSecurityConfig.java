package cl.guti.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {
	
    // ---- Matcher
    // 1. AnyRequest
    // 2. RequestMatchers
    // 3. RequestMatchers with HttpMethod


    // ---- Authorization rule
    // 1. PermitAll
    // 2. DenyAll
    // 3. Authenticated
    // 4. HasRole
    // 5. HasAuthority
    // 6. Access (SpEL) - Spring Expression Language

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { return http .httpBasic() .and().authorizeHttpRequests()
	 * //.anyRequest().permitAll() // .requestMatchers("/demo").permitAll() //
	 * .requestMatchers("/admin").hasRole("ADMIN") //
	 * .requestMatchers("/dba").hasAnyRole("DBA", "ADMIN")
	 * .requestMatchers(HttpMethod.POST, "/add").hasRole("ADMIN")
	 * .requestMatchers(HttpMethod.GET, "/add").authenticated()
	 * .and().csrf().disable().build(); }
	 */



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and().authorizeHttpRequests()
                //.anyRequest().permitAll()
                //.anyRequest().denyAll()
                //.anyRequest().authenticated()
                //.anyRequest().hasRole("ADMIN")
                .anyRequest().hasAnyAuthority("WRITE","ADMIN")
                //.anyRequest().hasAuthority("write")
                //.anyRequest().access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('DBA')")) //Spring Expression Language (SpEL)
                .and().build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}

package co.edu.usco.pw.springboot_crud01.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(NoOpPasswordEncoder.getInstance())
				.withUser("rector").password("rector").roles("USER", "ADMIN") //acceder al rol de admin con el usuario rector
				.and()
				.withUser("estudiante").password("estudiante").roles("USER");//acceder al rol de usuario con el usuario estudiante
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login", "/h2-console/**").permitAll() // Permitir acceso a ciertas URLs sin autenticación
				.antMatchers("/estudiante/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.formLogin(); // Configuración de inicio de sesión basado en formulario
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}

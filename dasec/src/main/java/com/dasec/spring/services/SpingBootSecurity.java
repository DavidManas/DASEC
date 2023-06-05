package com.dasec.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpingBootSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;

	/* El método configure(AuthenticationManagerBuilder auth) configura la
	 * autenticación. Se especifica el UserDetailsService que se utilizará para
	 * cargar los detalles del usuario (nombres de usuario, contraseñas, roles,
	 * etc.) y el PasswordEncoder que se utilizará para cifrar las contraseñas. */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(getEnecoder());
	}

	/* El método configure(HttpSecurity http) configura la autorización. Se
	 * especifican las rutas a las que se permite el acceso y los roles necesarios
	 * para acceder a cada ruta. También se especifica la página de inicio de sesión
	 * personalizada y la página de destino después del inicio de sesión exitoso.
	 * Además, se deshabilita la protección contra CSRF (ataques de falsificación de
	 * solicitudes entre sitios) porque la aplicación no utiliza formularios para
	 * crear o modificar recursos. */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/administrador/**").hasRole("ADMIN")
				.antMatchers("/productos/**").hasRole("ADMIN").and().formLogin().loginPage("/usuario/login").permitAll()
				.defaultSuccessUrl("/usuario/acceder");
	}

	/* El método getEnecoder() devuelve un objeto BCryptPasswordEncoder, que es un
	 * algoritmo de cifrado de contraseñas utilizado para almacenar las contraseñas
	 * de los usuarios de forma segura en la base de datos. */
	@Bean
	public BCryptPasswordEncoder getEnecoder() {
		return new BCryptPasswordEncoder();
	}

}

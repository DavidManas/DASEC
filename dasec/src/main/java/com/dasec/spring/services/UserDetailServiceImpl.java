package com.dasec.spring.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dasec.spring.entities.Usuario;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	HttpSession session;

	private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	/* El método loadUserByUsername recibe el nombre de usuario como argumento y
	 * busca en la base de datos un usuario con ese nombre de usuario. Si encuentra
	 * el usuario, almacena su identificador de usuario en la sesión HTTP y devuelve
	 * un objeto UserDetails que representa al usuario, incluyendo su nombre de
	 * usuario, contraseña y roles. Si no se encuentra el usuario, se lanza una
	 * excepción UsernameNotFoundException. */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Este es el username");
		Optional<Usuario> optionalUser = usuarioService.findByEmail(username);
		if (optionalUser.isPresent()) {
			log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
			session.setAttribute("idusuario", optionalUser.get().getId());
			Usuario usuario = optionalUser.get();
			return User.builder().username(usuario.getNombre()).password(usuario.getPassword()).roles(usuario.getTipo())
					.build();
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

}

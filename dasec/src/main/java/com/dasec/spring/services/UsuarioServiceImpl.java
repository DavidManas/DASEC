package com.dasec.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasec.spring.entities.Usuario;
import com.dasec.spring.repositories.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	/* findById(Integer id): devuelve un objeto Optional que contiene el Usuario con
	 * el id proporcionado. Si no existe, se devuelve un objeto Optional vacío. */
	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	/* save(Usuario usuario): guarda un objeto Usuario en la base de datos y
	 * devuelve el objeto guardado. */
	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	/* findByEmail(String email): devuelve un objeto Optional que contiene el
	 * Usuario con la dirección de correo electrónico proporcionada. Si no existe,
	 * se devuelve un objeto Optional vacío. */
	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	/* findAll(): devuelve una lista de todos los Usuarios presentes en la base de
	 * datos. */
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

}

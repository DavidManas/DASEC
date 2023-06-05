package com.dasec.spring.services;

import java.util.List;
import java.util.Optional;

import com.dasec.spring.entities.Usuario;

public interface IUsuarioService {
	/* findAll(): Devuelve una lista de todos los usuarios. */ 
	List<Usuario> findAll();
	
	/* findById(Integer id): Devuelve un usuario específico según el id proporcionado. */
	Optional<Usuario> findById(Integer id);
	
	/* save(Usuario usuario): Guarda un nuevo usuario o actualiza uno existente en algún repositorio 
	 * o base de datos. */
	Usuario save (Usuario usuario);
	
	/* findByEmail(String email): Devuelve un usuario específico según el email proporcionado. */
	Optional<Usuario> findByEmail(String email);

}

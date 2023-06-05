package com.dasec.spring.services;

import java.util.List;
import java.util.Optional;

import com.dasec.spring.entities.Orden;
import com.dasec.spring.entities.Usuario;

public interface IOrdenService {
	/* findAll(): Devuelve una lista de todas las órdenes. */
	List<Orden> findAll();
	
	/* findById(Integer id): Devuelve una orden específica según el id proporcionado. */
	Optional<Orden> findById(Integer id);
	
	/* save(Orden orden): Guarda una nueva orden o actualiza una existente en algún repositorio o 
	 * base de datos. */
	Orden save (Orden orden);
	
	/* generarNumeroOrden(): Genera un nuevo número de orden para la nueva orden. */
	String generarNumeroOrden();
	
	/* findByUsuario(Usuario usuario): Devuelve una lista de órdenes asociadas a un usuario específico. */
	List<Orden> findByUsuario (Usuario usuario);
}

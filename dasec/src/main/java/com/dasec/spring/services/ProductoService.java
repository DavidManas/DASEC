package com.dasec.spring.services;

import java.util.List;
import java.util.Optional;

import com.dasec.spring.entities.Producto;

public interface ProductoService {
	
	/* save(Producto producto): Guarda un nuevo producto o actualiza uno existente 
	 * en algún repositorio o base de datos. */
	public Producto save( Producto producto);
	
	/* get(Integer id): Devuelve un producto específico según el id proporcionado. */ 
	public Optional<Producto> get(Integer id);
	
	/* update(Producto producto): Actualiza un producto existente en algún repositorio o base de datos. */
	public void update(Producto producto);
	
	/* delete(Integer id): Elimina un producto específico según el id proporcionado. */
	public void delete(Integer id);
	
	/* findAll(): Devuelve una lista de todos los productos. */
	public List<Producto> findAll();

}

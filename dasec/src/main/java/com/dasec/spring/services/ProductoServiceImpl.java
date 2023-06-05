package com.dasec.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasec.spring.entities.Producto;
import com.dasec.spring.repositories.IProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private IProductoRepository productoRepository;

	/* save(Producto producto): Guarda un nuevo producto o actualiza uno existente
	 * en el repositorio de productos usando la función save proporcionada por el
	 * IProductoRepository. */
	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	/* get(Integer id): Devuelve un producto específico según el id proporcionado
	 * del repositorio de productos utilizando la función findById proporcionada por
	 * el IProductoRepository. */
	@Override
	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);
	}

	/* update(Producto producto): Actualiza un producto existente en el repositorio
	 * de productos usando la función save proporcionada por el IProductoRepository. */
	@Override
	public void update(Producto producto) {
		productoRepository.save(producto);
	}

	/* delete(Integer id): Elimina un producto específico según el id proporcionado
	 * del repositorio de productos utilizando la función deleteById proporcionada
	 * por el IProductoRepository. */
	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);
	}

	/* findAll(): Devuelve una lista de todos los productos del repositorio de
	 * productos utilizando la función findAll proporcionada por el
	 * IProductoRepository. */
	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

}

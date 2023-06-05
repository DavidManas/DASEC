package com.dasec.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasec.spring.entities.DetalleOrden;
import com.dasec.spring.repositories.IDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;

	/* La función save(DetalleOrden detalleOrden) guarda un detalle de orden en la base de 
	 * datos utilizando el método save() proporcionado por el repositorio. */
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}

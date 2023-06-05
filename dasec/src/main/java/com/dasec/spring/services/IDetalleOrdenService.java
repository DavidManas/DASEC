package com.dasec.spring.services;

import com.dasec.spring.entities.DetalleOrden;

/* La interfaz define el contrato que se debe seguir para implementar el servicio que manejará 
 * los detalles de la orden. En este caso, la única función que se debe implementar es save(), 
 * que toma un objeto DetalleOrden como argumento y devuelve el mismo objeto como resultado. */ 
public interface IDetalleOrdenService {
	DetalleOrden save (DetalleOrden detalleOrden);

}

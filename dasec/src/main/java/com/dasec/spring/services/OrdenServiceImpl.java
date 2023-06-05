package com.dasec.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dasec.spring.entities.Orden;
import com.dasec.spring.entities.Usuario;
import com.dasec.spring.repositories.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService {
	
	@Autowired
	private IOrdenRepository ordenRepository;

	/* La función save(Orden orden) guarda una orden en la base de datos utilizando el método 
	 * save() proporcionado por el repositorio. */
	@Override
	public Orden save(Orden orden) {
		return ordenRepository.save(orden);
	}

	/* La función findAll() devuelve una lista de todas las órdenes almacenadas en la base de 
	 * datos, utilizando el método findAll() proporcionado por el repositorio. */
	@Override
	public List<Orden> findAll() {
		return ordenRepository.findAll();
	}

	/* La función generarNumeroOrden() genera un número de orden único para cada nueva orden. 
	 * Esta función busca todas las órdenes almacenadas en la base de datos utilizando la 
	 * función findAll(), luego extrae el número de cada orden y los almacena en una lista de 
	 * enteros. Luego, la función calcula el próximo número de orden disponible sumando 1 al 
	 * máximo número de orden existente y lo devuelve en un formato concatenado con ceros a 
	 * la izquierda. */
	public String generarNumeroOrden() {
		int numero=0;
		String numeroConcatenado="";
		
		List<Orden> ordenes = findAll();
		
		List<Integer> numeros= new ArrayList<Integer>();
		
		ordenes.stream().forEach(o -> numeros.add( Integer.parseInt( o.getNumero())));
		
		if (ordenes.isEmpty()) {
			numero=1;
		}else {
			numero= numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if (numero<10) { //0000001000
			numeroConcatenado="000000000"+String.valueOf(numero);
		}else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
		}else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}else if(numero<10000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}		
		
		return numeroConcatenado;
	}

	/* La función findByUsuario(Usuario usuario) devuelve una lista de órdenes asociadas a un usuario 
	 * específico utilizando el método findByUsuario() proporcionado por el repositorio. */
	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		return ordenRepository.findByUsuario(usuario);
	}

	/* La función findById(Integer id) devuelve una orden específica de la base de datos utilizando el 
	 * método findById() proporcionado por el repositorio. */ 
	@Override
	public Optional<Orden> findById(Integer id) {
		return ordenRepository.findById(id);
	}

}

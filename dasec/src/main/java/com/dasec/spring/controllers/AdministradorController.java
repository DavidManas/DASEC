package com.dasec.spring.controllers;

// Importación de librerías y clases necesarias
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dasec.spring.entities.Orden;
import com.dasec.spring.entities.Producto;
import com.dasec.spring.services.IOrdenService;
import com.dasec.spring.services.IUsuarioService;
import com.dasec.spring.services.ProductoService;

/*Cuando entramos como administrador*/
@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IOrdenService ordensService;

	private Logger logg = LoggerFactory.getLogger(AdministradorController.class);

	@GetMapping("")
	public String home(Model model) {
		// Se obtienen todos los productos y se agregan al modelo
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);

		// Se retorna la vista correspondiente al home del administrador
		return "administrador/home";
	}

	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		// Se obtienen todos los usuarios y se agregan al modelo
		model.addAttribute("usuarios", usuarioService.findAll());
		// Se retorna la vista correspondiente a la lista de usuarios
		return "administrador/usuarios";
	}

	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		// Se obtienen todas las órdenes y se agregan al modelo
		model.addAttribute("ordenes", ordensService.findAll());
		// Se retorna la vista correspondiente a la lista de órdenes
		return "administrador/ordenes";
	}

	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		// Se imprime el ID de la orden en el log
		logg.info("Id de la orden {}", id);
		// Se obtiene la orden correspondiente al ID y se agrega al modelo
		Orden orden = ordensService.findById(id).get();
		model.addAttribute("detalles", orden.getDetalle());
		// Se retorna la vista correspondiente al detalle de la orden
		return "administrador/detalleorden";
	}

}

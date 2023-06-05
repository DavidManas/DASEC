package com.dasec.spring.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dasec.spring.entities.Producto;
import com.dasec.spring.entities.Usuario;
import com.dasec.spring.services.IUsuarioService;
import com.dasec.spring.services.ProductoService;
import com.dasec.spring.services.UploadFileService;
import com.dasec.spring.services.UsuarioServiceImpl;

@Controller // Indica que esta clase es un controlador de Spring MVC
@RequestMapping("/productos") // Especifica la ruta base para todas las solicitudes HTTP relacionadas con los
								// productos
public class ProductoController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired // Anotación de Spring que realiza una inyección de dependencias de
				// ProductoService
	private ProductoService productoService;

	@Autowired // Anotación de Spring que realiza una inyección de dependencias de
				// IUsuarioService
	private IUsuarioService usuarioService;

	@Autowired // Anotación de Spring que realiza una inyección de dependencias de
				// UploadFileService
	private UploadFileService upload;

	@GetMapping("") // Método que maneja las solicitudes GET a la ruta base /productos y devuelve la
					// vista show.html
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll()); // Agrega todos los productos al modelo
		return "productos/show"; // Devuelve la vista show.html
	}

	@GetMapping("/create") // Método que maneja las solicitudes GET a la ruta /productos/create y devuelve
							// la vista create.html
	public String create() {
		return "productos/create"; // Devuelve la vista create.html
	}

	@PostMapping("/save") // Método que maneja las solicitudes POST a la ruta /productos/save
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session)
			throws IOException {
		LOGGER.info("Este es el objeto producto {}", producto); // Imprime el objeto producto en el registro de logs

		// Busca al usuario actual
		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get(); 
		
		producto.setUsuario(u); // Establece el usuario en el objeto producto

		// imagen
		if (producto.getId() == null) { // Si el producto es nuevo (no tiene ID)...
			String nombreImagen = upload.saveImage(file); // Guarda la imagen en el servidor
			producto.setImagen(nombreImagen); // Establece el nombre de la imagen en el objeto producto
		} else { // Si el producto ya existe (tiene ID)...
			// No hace nada
		}

		productoService.save(producto); // Guarda el producto en la base de datos
		return "redirect:/productos"; // Redirige al usuario a la lista de productos
	}

	@GetMapping("/edit/{id}") // Método que maneja las solicitudes GET a la ruta /productos/edit/{id} y
								// devuelve la vista edit.html
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto(); // Crea un nuevo objeto producto
		Optional<Producto> optionalProducto = productoService.get(id); // Busca el producto por ID
		producto = optionalProducto.get(); // Obtiene el producto de la opción

		LOGGER.info("Producto buscado: {}", producto); // Imprime el producto buscado en el registro de logs
		model.addAttribute("producto", producto); // Agrega el producto al modelo

		return "productos/edit"; // Devuelve la vista edit.html
	}

	@PostMapping("/update") // Método que maneja las solicitudes POST a la ruta /productos/update
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		Producto p = new Producto();
		p = productoService.get(producto.getId()).get();

		if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem

			producto.setImagen(p.getImagen());
		} else {// cuando se edita tbn la imagen
				// eliminar cuando no sea la imagen por defecto
			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {

		Producto p = new Producto();
		p = productoService.get(id).get();

		// eliminar cuando no sea la imagen por defecto
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}

		productoService.delete(id);
		return "redirect:/productos";
	}

}

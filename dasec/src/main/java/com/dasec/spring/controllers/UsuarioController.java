package com.dasec.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dasec.spring.entities.Orden;
import com.dasec.spring.entities.Usuario;
import com.dasec.spring.services.IOrdenService;
import com.dasec.spring.services.IUsuarioService;

/* Este es un controlador en Java para el manejo de usuarios. Aquí se utiliza la anotación @Controller para indicar 
 * que se trata de un controlador y @RequestMapping("/usuario") para especificar que la ruta base para las 
 * operaciones de usuario es "/usuario".*/
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IOrdenService ordenService;

	BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

	// /usuario/registro
	@GetMapping("/registro") /*
								 * Este método maneja la solicitud GET para la ruta "/usuario/registro". Al ser
								 * llamado, devuelve la vista correspondiente a la página de registro de
								 * usuario.
								 */
	public String create() {
		return "usuario/registro";
	}

	@PostMapping("/save") /*
							 * El método "save()" maneja la solicitud POST para la ruta "/usuario/save".
							 * Este método guarda la información del usuario en la base de datos utilizando
							 * el objeto usuarioService. La contraseña se cifra antes de ser guardada
							 * utilizando la clase BCryptPasswordEncoder.
							 */
	public String save(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuario.setPassword(passEncode.encode(usuario.getPassword()));
		usuarioService.save(usuario);
		return "redirect:/";
	}

	@GetMapping("/login") /*
							 * El método "login()" maneja la solicitud GET para la ruta "/usuario/login" y
							 * devuelve la vista correspondiente a la página de inicio de sesión de usuario.
							 */
	public String login() {
		return "usuario/login";
	}

	/*
	 * Este código es un método de tipo GET que utiliza la anotación @GetMapping con
	 * la ruta /acceder. El método recibe dos parámetros: usuario y session. En
	 * primer lugar, se registra un mensaje de registro en el archivo de registro
	 * con el objeto Logger y el mensaje "Accesos" y los detalles del usuario.
	 * Luego, se busca el usuario en la base de datos utilizando el idusuario
	 * almacenado en la sesión. Si el usuario existe en la base de datos, se
	 * establece el idusuario de la sesión y se redirige al usuario a la página
	 * correspondiente según su tipo de usuario (ADMIN o USER). Si no existe el
	 * usuario en la base de datos, se registra un mensaje de error. En ambos casos,
	 * el usuario es redirigido a la página de inicio utilizando return
	 * "redirect:/".
	 */
	@GetMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);

		Optional<Usuario> user = usuarioService
				.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
		// logger.info("Usuario de db: {}", user.get());

		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());

			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			} else {
				return "redirect:/";
			}
		} else {
			logger.info("Usuario no existe");
		}

		return "redirect:/";
	}

	/*
	 * El código representa un método que maneja la solicitud GET para la URL
	 * "/compras". El método utiliza el objeto Model para agregar el atributo
	 * "sesion", que es el ID del usuario que inició sesión almacenado en la sesión
	 * HTTP. A continuación, se utiliza este ID para buscar el objeto Usuario
	 * correspondiente en la base de datos mediante el método findById() del
	 * servicio de Usuario. Luego, se utiliza el objeto Usuario para buscar todas
	 * las Ordenes asociadas al usuario utilizando el método findByUsuario() del
	 * servicio de Orden. Finalmente, se agrega la lista de Ordenes al objeto Model
	 * para que se pueda mostrar en la vista "usuario/compras".
	 */
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		List<Orden> ordenes = ordenService.findByUsuario(usuario);
		logger.info("ordenes {}", ordenes);

		model.addAttribute("ordenes", ordenes);

		return "usuario/compras";
	}

	/*
	 * Este código define un método controlador que maneja una solicitud GET a la
	 * ruta /detalle/{id}. El parámetro id se obtiene de la URL mediante la
	 * anotación @PathVariable. Dentro del método, se utiliza el objeto ordenService
	 * para buscar la orden correspondiente al id especificado. Si se encuentra la
	 * orden, se obtienen sus detalles y se agregan al modelo. Luego, se agrega al
	 * modelo el atributo sesion, que contiene el ID del usuario almacenado en la
	 * sesión. Finalmente, se devuelve el nombre de la vista
	 * "usuario/detallecompra", que se mostrará al usuario.
	 */
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la orden: {}", id);
		Optional<Orden> orden = ordenService.findById(id);

		model.addAttribute("detalles", orden.get().getDetalle());

		// session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/detallecompra";
	}

	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}
}

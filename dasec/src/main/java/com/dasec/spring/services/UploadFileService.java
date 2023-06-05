package com.dasec.spring.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	private String folder = "images//";

	/* El método saveImage recibe un objeto MultipartFile que representa un archivo
	 * de imagen cargado en un formulario web. El método verifica que el archivo no
	 * esté vacío y luego guarda los bytes del archivo en una ruta determinada en el
	 * servidor (definida por la variable folder). Luego, devuelve el nombre del
	 * archivo guardado. */
	public String saveImage(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}

	/* El método deleteImage recibe el nombre de un archivo de imagen y lo elimina
	 * del servidor. El método construye una ruta para el archivo de imagen y
	 * utiliza el método delete() de la clase File para eliminar el archivo. */
	public void deleteImage(String nombre) {
		String ruta = "images//";
		File file = new File(ruta + nombre);
		file.delete();
	}

}

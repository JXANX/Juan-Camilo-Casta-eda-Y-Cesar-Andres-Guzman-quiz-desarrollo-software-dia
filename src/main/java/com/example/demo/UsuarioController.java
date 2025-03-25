package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final MotocicletaService usuarioService;

	@Autowired
	public UsuarioController(MotocicletaService usuarioService) {
		this.usuarioService = usuarioService;
	}

	// Obtener todos los usuarios
	@GetMapping
	public ResponseEntity<List<Motocicleta>> getAllUsuarios() {
		List<Motocicleta> motocicletas = usuarioService.findAll();
		return new ResponseEntity<>(motocicletas, HttpStatus.OK);
	}

	// Obtener un usuario por ID
	@GetMapping("/{id}")
	public ResponseEntity<Motocicleta> getUsuarioById(@PathVariable String id) {
		Motocicleta motocicleta = usuarioService.findById(id);
		if (motocicleta != null) {
			return new ResponseEntity<>(motocicleta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Crear un nuevo usuario
	@PostMapping
	public ResponseEntity<Motocicleta> createUsuario(@RequestBody Motocicleta motocicleta) {
		Motocicleta newMotocicleta = usuarioService.save(motocicleta);
		return new ResponseEntity<>(newMotocicleta, HttpStatus.CREATED);
	}

	// Actualizar un usuario existente
	@PutMapping("/{id}")
	public ResponseEntity<Motocicleta> updateUsuario(@PathVariable String id, @RequestBody Motocicleta motocicleta) {
		Motocicleta existingMotocicleta = usuarioService.findById(id);
		if (existingMotocicleta != null) {
			motocicleta.setId(id);
			Motocicleta updatedMotocicleta = usuarioService.update(motocicleta);
			return new ResponseEntity<>(updatedMotocicleta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Eliminar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable String id) {
		Motocicleta existingMotocicleta = usuarioService.findById(id);
		if (existingMotocicleta != null) {
			usuarioService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Ruta con parámetros de consulta (query string)
	@GetMapping("/buscar")
	public ResponseEntity<List<Motocicleta>> buscarUsuarios(@RequestParam(required = false) String nombre,
															@RequestParam(required = false) String email, @RequestParam(defaultValue = "0") int edad) {
		List<Motocicleta> motocicletas = usuarioService.buscarPorFiltros(nombre, email, edad);
		return new ResponseEntity<>(motocicletas, HttpStatus.OK);
	}


	// Ruta que lee cabeceras HTTP
	@GetMapping("/auth")
	public ResponseEntity<Motocicleta> getUserByToken(@RequestHeader("Authorization") String authToken) {
		Motocicleta motocicleta = usuarioService.findByAuthToken(authToken);
		if (motocicleta != null) {
			return new ResponseEntity<>(motocicleta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
}
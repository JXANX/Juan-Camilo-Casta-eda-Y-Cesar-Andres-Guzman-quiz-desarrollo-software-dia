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
@RequestMapping("/api/motocicletas")
public class MotocicletaController {

	private final MotocicletaService motocicletaService;

	@Autowired
	public MotocicletaController(MotocicletaService motocicletaService) {
		this.motocicletaService = motocicletaService;
	}

	// Obtener todos los usuarios
	@GetMapping
	public ResponseEntity<List<Motocicleta>> getAllMotocicleta() {
		List<Motocicleta> motocicletas = motocicletaService.findAll();
		return new ResponseEntity<>(motocicletas, HttpStatus.OK);
	}

	// Obtener un usuario por ID
	@GetMapping("/{id}")
	public ResponseEntity<Motocicleta> getMotocicletaById(@PathVariable String id) {
		Motocicleta motocicleta = motocicletaService.findById(id);
		if (motocicleta != null) {
			return new ResponseEntity<>(motocicleta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Crear un nuevo usuario
	@PostMapping
	public ResponseEntity<Motocicleta> createMotocicleta(@RequestBody Motocicleta motocicleta) {
		Motocicleta newMotocicleta = motocicletaService.save(motocicleta);
		return new ResponseEntity<>(newMotocicleta, HttpStatus.CREATED);
	}

	// Actualizar un usuario existente
	@PutMapping("/{id}")
	public ResponseEntity<Motocicleta> updateMotocicleta(@PathVariable String id, @RequestBody Motocicleta motocicleta) {
		Motocicleta existingMotocicleta = motocicletaService.findById(id);
		if (existingMotocicleta != null) {
			motocicleta.setId(id);
			Motocicleta updatedMotocicleta = motocicletaService.update(motocicleta);
			return new ResponseEntity<>(updatedMotocicleta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Eliminar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMotocicleta(@PathVariable String id) {
		Motocicleta existingMotocicleta = motocicletaService.findById(id);
		if (existingMotocicleta != null) {
			motocicletaService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Ruta con parámetros de consulta (query string)
	@GetMapping("/buscar")
	public ResponseEntity<List<Motocicleta>> buscarMotocicleta(@RequestParam(required = false) String placa,
															@RequestParam(required = false) String cilindraje,@RequestParam(required = false) String precioMotocicleta,
															   @RequestParam(required = false) String colorMotocicleta){
		List<Motocicleta> motocicletas = motocicletaService.buscarPorFiltros(placa, cilindraje, precioMotocicleta, colorMotocicleta);
		return new ResponseEntity<>(motocicletas, HttpStatus.OK);
	}


	// Ruta que lee cabeceras HTTP
	@GetMapping("/auth")
	public ResponseEntity<String> getMotocicletaByToken(@RequestHeader("Authorization") String authToken) {
		String motocicleta = motocicletaService.findByAuthToken(authToken);
		if (motocicleta != null) {
			return new ResponseEntity<>(motocicleta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
}
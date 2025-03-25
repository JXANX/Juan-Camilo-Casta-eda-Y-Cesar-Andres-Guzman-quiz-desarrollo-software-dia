package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        
        // Inicializamos algunos datos de ejemplo
        initSampleData();
    }
    
    private void initSampleData() {
        // Crear usuarios
        Motocicleta juan = new Motocicleta("Juan Pérez", "juan@example.com", 30);
        Motocicleta maria = new Motocicleta("María López", "maria@example.com", 25);
        Motocicleta carlos = new Motocicleta("Carlos Ruiz", "carlos@example.com", 40);
        
        // Guardar usuarios
        save(juan);
        save(maria);
        save(carlos);
        
        // Agregar permisos a Juan
        List<String> permisosJuan = new ArrayList<>();
        permisosJuan.add("LEER");
        permisosJuan.add("ESCRIBIR");
        permisosJuan.add("INACTIVO_ADMIN");
        usuarioRepository.setPermisos(juan.getId(), permisosJuan);
        
        // Agregar permisos a María
        List<String> permisosMaria = new ArrayList<>();
        permisosMaria.add("LEER");
        permisosMaria.add("ADMIN");
        usuarioRepository.setPermisos(maria.getId(), permisosMaria);
        
        // Agregar accesos a Juan
        Map<String, Object> acceso1 = new HashMap<>();
        acceso1.put("fecha", "2023-01-01");
        acceso1.put("ip", "192.168.1.1");
        acceso1.put("navegador", "Chrome");
        usuarioRepository.registrarAcceso(juan.getId(), acceso1);
        
        Map<String, Object> acceso2 = new HashMap<>();
        acceso2.put("fecha", "2023-01-02");
        acceso2.put("ip", "192.168.1.2");
        acceso2.put("navegador", "Firefox");
        usuarioRepository.registrarAcceso(juan.getId(), acceso2);
        
    }
    
    // Crear un nuevo usuario
    public Motocicleta save(Motocicleta motocicleta) {
        return usuarioRepository.save(motocicleta);
    }
    
    // Obtener un usuario por ID
    public Motocicleta findById(String id) {
        return usuarioRepository.findById(id);
    }
    
    // Listar todos los usuarios
    public List<Motocicleta> findAll() {
        return usuarioRepository.findAll();
    }
    
    // Actualizar un usuario
    public Motocicleta update(Motocicleta motocicleta) {
        return usuarioRepository.update(motocicleta);
    }
    
    // Eliminar un usuario
    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }
    
    // Buscar usuarios por filtros
    public List<Motocicleta> buscarPorFiltros(String nombre, String email, int edad) {
        return usuarioRepository.buscarPorFiltros(nombre, email, edad);
    }
    
    // Encontrar usuario por token de autorización
    public Motocicleta findByAuthToken(String authToken) {
        return usuarioRepository.findByAuthToken(authToken);
    }
    

    
}
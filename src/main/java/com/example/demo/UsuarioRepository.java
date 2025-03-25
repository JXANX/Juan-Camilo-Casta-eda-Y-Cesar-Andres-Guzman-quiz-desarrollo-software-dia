package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    
    // Simulamos una base de datos con un Map
    private final Map<String, Motocicleta> baseDeDatos = new HashMap<>();
    
    // Simulamos almacén de tokens de autorización
    private final Map<String, String> authTokens = new HashMap<>();
    
    // Simulamos almacén de permisos de usuario
    private final Map<String, List<String>> permisos = new HashMap<>();
    
    // Simulamos almacén de accesos de usuario
    private final Map<String, List<Map<String, Object>>> accesos = new HashMap<>();
    
    // Simulamos almacén de preferencias de usuario
    private final Map<String, Map<String, Object>> preferencias = new HashMap<>();
    
    // Simulamos histórico de estados
    private final Map<String, List<Map<String, Object>>> statusHistorial = new HashMap<>();
    
    // Simulamos documentos de usuario
    private final Map<String, List<Map<String, Object>>> documentos = new HashMap<>();
    
    // Guardar un usuario
    public Motocicleta save(Motocicleta motocicleta) {
        baseDeDatos.put(motocicleta.getId(), motocicleta);
        // Inicializamos estructuras relacionadas
        authTokens.put("token-" + motocicleta.getId(), motocicleta.getId());
        permisos.put(motocicleta.getId(), new ArrayList<>());
        accesos.put(motocicleta.getId(), new ArrayList<>());
        preferencias.put(motocicleta.getId(), new HashMap<>());
        statusHistorial.put(motocicleta.getId(), new ArrayList<>());
        documentos.put(motocicleta.getId(), new ArrayList<>());
        return motocicleta;
    }
    
    // Encontrar usuario por ID
    public Motocicleta findById(String id) {
        return baseDeDatos.get(id);
    }
    
    // Listar todos los usuarios
    public List<Motocicleta> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }
    
    // Eliminar un usuario
    public void deleteById(String id) {
        baseDeDatos.remove(id);
        authTokens.remove("token-" + id);
        permisos.remove(id);
        accesos.remove(id);
        preferencias.remove(id);
        statusHistorial.remove(id);
        documentos.remove(id);
    }
    
    // Actualizar un usuario
    public Motocicleta update(Motocicleta motocicleta) {
        if (baseDeDatos.containsKey(motocicleta.getId())) {
            baseDeDatos.put(motocicleta.getId(), motocicleta);
            return motocicleta;
        }
        return null;
    }
    
    // Buscar usuarios por filtros
    public List<Motocicleta> buscarPorFiltros(String nombre, String email, int edad) {
        return baseDeDatos.values().stream()
                .filter(u -> nombre == null || u.getNombre().contains(nombre))
                .filter(u -> email == null || u.getEmail().contains(email))
                .filter(u -> edad == 0 || u.getEdad() == edad)
                .collect(Collectors.toList());
    }
    
    
    // Encontrar usuario por token de autorización
    public Motocicleta findByAuthToken(String authToken) {
        String userId = authTokens.get(authToken);
        if (userId != null) {
            return baseDeDatos.get(userId);
        }
        return null;
    }
    
    // Establecer permisos para un usuario
    public void setPermisos(String id, List<String> nuevosPermisos) {
        permisos.put(id, nuevosPermisos);
    }
    
    // Registrar un nuevo acceso
    public void registrarAcceso(String id, Map<String, Object> nuevoAcceso) {
        List<Map<String, Object>> accesosUsuario = accesos.getOrDefault(id, new ArrayList<>());
        accesosUsuario.add(0, nuevoAcceso); // Añadir al principio (más reciente)
        accesos.put(id, accesosUsuario);
    }
    
    
    
}
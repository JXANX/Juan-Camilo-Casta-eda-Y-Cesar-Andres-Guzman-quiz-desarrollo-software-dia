package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public class MotocicletaRepository {
    private final List<Motocicleta> baseDeDatos = new ArrayList<>();
    private final List<String> authTokens = new ArrayList<>();
    public Motocicleta save(Motocicleta motocicleta) {
        baseDeDatos.add(motocicleta);
        authTokens.add(motocicleta.getId());
        return motocicleta;
    }
    public Motocicleta findById(String id) {
        for (Motocicleta motocicleta : baseDeDatos) {
            if (motocicleta.getId().equals(id)) {
                return motocicleta;
            }
        }
        return null;
    }
    public List<Motocicleta> findAll() {
        return new ArrayList<>(baseDeDatos);
    }
    public void deleteById(String id) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(id)) {
                baseDeDatos.remove(i);
                return;
            }
        }
    }
    public Motocicleta update(Motocicleta motocicleta) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(motocicleta.getId())) {
                baseDeDatos.set(i, motocicleta);
                return motocicleta;
            }
        }
        return null;
    }
    public List<Motocicleta> buscarPorFiltros(String placa, String cilindraje, String precioMotocicleta, String colorMotocicleta) {
        List<Motocicleta> resultado = new ArrayList<>();
        for (Motocicleta motocicleta : baseDeDatos) {
            boolean coincidePlaca = (placa == null ||
                    motocicleta.getPlaca().contains(placa));
            boolean coincideCilindraje = (cilindraje == null ||
                    motocicleta.getCilindraje().contains(cilindraje));
            boolean conincidePrecio =(precioMotocicleta == null ||
                    motocicleta.getPrecioMotocicleta().contains(precioMotocicleta));
            boolean conincideColor =(colorMotocicleta == null ||
                    motocicleta.getColorMotocicleta().contains(colorMotocicleta));

            if (coincidePlaca && coincideCilindraje && conincidePrecio && conincideColor) {
                resultado.add(motocicleta);
            }
        }
        return resultado;
    }
    public String findByAuthToken(String authToken) {
        for (String token : authTokens) {
            if (token.equals(authToken)) {
                return "Autorizado";
            }
        }
        return null;
    }
}
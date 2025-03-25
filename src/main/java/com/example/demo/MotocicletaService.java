package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MotocicletaService {
    private final MotocicletaRepository motocicletaRepository;
    @Autowired
    public MotocicletaService(MotocicletaRepository motocicletaRepository) {
        this.motocicletaRepository = motocicletaRepository;
        initSampleData();
    }
    private void initSampleData() {
        Motocicleta Boxer = new Motocicleta("XPT98E", "100cc", "5,799,000", "Negra");
        Motocicleta Pulsar = new Motocicleta("MZG24E", "200cc", "14,299,000", "Negra");
        Motocicleta Suzuki = new Motocicleta("TKL72E", "300cc", "10,630,000", "Negra");
        save(Boxer);
        save(Pulsar);
        save(Suzuki);
    }
    public Motocicleta save(Motocicleta motocicleta) {
        return motocicletaRepository.save(motocicleta);
    }
    public Motocicleta findById(String id) {
        return motocicletaRepository.findById(id);
    }
    public List<Motocicleta> findAll() {
        return motocicletaRepository.findAll();
    }
    public Motocicleta update(Motocicleta motocicleta) {
        return motocicletaRepository.update(motocicleta);
    }
    public void deleteById(String id) {
        motocicletaRepository.deleteById(id);
    }
    public List<Motocicleta> buscarPorFiltros(String placa, String cilindraje, String precioMotocicleta, String colorMotocicleta) {
        return motocicletaRepository.buscarPorFiltros(placa, cilindraje, precioMotocicleta, colorMotocicleta);
    }
    public String findByAuthToken(String authToken) {
        return motocicletaRepository.findByAuthToken(authToken);
    }
}

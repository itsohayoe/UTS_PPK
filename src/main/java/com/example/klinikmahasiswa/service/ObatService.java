package com.example.klinikmahasiswa.service;

import com.example.klinikmahasiswa.entity.Obat;
import com.example.klinikmahasiswa.repository.ObatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObatService {
    @Autowired
    private ObatRepository obatRepository;

    public Obat addObat(Obat obat) {
        return obatRepository.save(obat);
    }

    public Obat updateStock(Long id, int stockChange) {
        Obat obat = obatRepository.findById(id).orElseThrow();
        obat.setStok(obat.getStok() + stockChange);
        return obatRepository.save(obat);
    }
}

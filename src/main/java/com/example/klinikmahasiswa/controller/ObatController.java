package com.example.klinikmahasiswa.controller;

import com.example.klinikmahasiswa.entity.Obat;
import com.example.klinikmahasiswa.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/obat")
public class ObatController {

    @Autowired
    private ObatService obatService;

    @PostMapping("/add")
    public ResponseEntity<Obat> addObat(@RequestBody Obat obat) {
        Obat savedObat = obatService.addObat(obat);
        return ResponseEntity.ok(savedObat);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Obat> updateStock(@PathVariable Long id, @RequestParam int stockChange) {
        Obat updatedObat = obatService.updateStock(id, stockChange);
        return ResponseEntity.ok(updatedObat);
    }
}
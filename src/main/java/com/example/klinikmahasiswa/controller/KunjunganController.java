package com.example.klinikmahasiswa.controller;

import com.example.klinikmahasiswa.dto.KunjunganDto;
import com.example.klinikmahasiswa.service.KunjunganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/kunjungan")
public class KunjunganController {
    @Autowired
    private KunjunganService kunjunganService;

    @PostMapping("/add")
    public ResponseEntity<String> addKunjungan(@Valid @RequestBody KunjunganDto kunjunganDto) {
        try {
            kunjunganService.createKunjungan(kunjunganDto);
            return ResponseEntity.ok("Kunjungan berhasil ditambahkan.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateKunjungan(@PathVariable Long id, @Valid @RequestBody KunjunganDto kunjunganDto) {
        try {
            kunjunganService.updateKunjungan(id, kunjunganDto);
            return ResponseEntity.ok("Kunjungan berhasil diperbarui.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.example.klinikmahasiswa.service;

import com.example.klinikmahasiswa.dto.KunjunganDto;
import com.example.klinikmahasiswa.entity.Kunjungan;
import com.example.klinikmahasiswa.entity.Obat;
import com.example.klinikmahasiswa.repository.KunjunganRepository;
import com.example.klinikmahasiswa.repository.ObatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class KunjunganService {
    @Autowired
    private KunjunganRepository kunjunganRepository;

    @Autowired
    private ObatRepository obatRepository; // Untuk mengelola stok obat

    public Kunjungan createKunjungan(@Valid KunjunganDto kunjunganDto) {
        Kunjungan kunjungan = new Kunjungan();
        kunjungan.setTanggalKunjungan(kunjunganDto.getTanggalKunjungan());
        kunjungan.setStatus("MENDATANG"); // Set status default

        // Validasi format tanggal
        if (!isValidDate(kunjungan.getTanggalKunjungan())) {
            throw new IllegalArgumentException("Tanggal Kunjungan harus diisi dengan format yang benar (YYYY-MM-DD).");
        }
        return kunjunganRepository.save(kunjungan);
    }

    private boolean isValidDate(LocalDate date) {
        return date != null;
    }

    public void updateKunjungan(Long kunjunganId, @Valid KunjunganDto kunjunganDto) {
        Optional<Kunjungan> optionalKunjungan = kunjunganRepository.findById(kunjunganId);
        Kunjungan kunjungan = optionalKunjungan.orElseThrow(() ->
                new IllegalArgumentException("Kunjungan tidak ditemukan."));

        String status = kunjunganDto.getStatus();
        if (status != null && (status.equals("SELESAI") || status.equals("BATAL"))) {
            kunjungan.setStatus(status);  // Update status kunjungan
        }

        kunjungan.setKeluhan(kunjunganDto.getKeluhan());

        Map<Long, Integer> obatMap = kunjunganDto.getObatJumlah();
        if (obatMap != null && !obatMap.isEmpty()) {
            for (Map.Entry<Long, Integer> entry : obatMap.entrySet()) {
                Long obatId = entry.getKey(); // Mendapatkan ID obat
                Integer jumlah = entry.getValue(); // Mendapatkan jumlah yang diberikan

                // Temukan obat menggunakan ID
                Optional<Obat> optionalObat = obatRepository.findById(obatId);
                optionalObat.ifPresent(obat -> {
                    // Periksa jika stok cukup
                    if (obat.getStok() >= jumlah) {
                        // Kurangi stok obat
                        obat.setStok(obat.getStok() - jumlah);
                        obatRepository.save(obat); // Simpan perubahan stok
                    } else {
                        throw new IllegalArgumentException("Stok obat " + obat.getName() + " tidak cukup.");
                    }
                });
            }

            kunjungan.setObatJumlah(obatMap); // Menyimpan peta jumlah obat ke kunjungan
        }

        // Simpan perubahan kunjungan
        kunjunganRepository.save(kunjungan);
    }
}
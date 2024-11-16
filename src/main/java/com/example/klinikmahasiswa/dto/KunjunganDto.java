package com.example.klinikmahasiswa.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class KunjunganDto {
    @NotNull(message = "Tanggal Kunjungan harus diisi dengan format YYYY-MM-DD.")
    private LocalDate tanggalKunjungan;

    private String status;    // Status: "MENDATANG", "SELESAI", "BATAL".
    private String keluhan;

    // Informasi obat dan jumlah yang diberikan
    private Map<Long, Integer> obatJumlah = new HashMap<>(); // Map (obat_id -> jumlah)
}

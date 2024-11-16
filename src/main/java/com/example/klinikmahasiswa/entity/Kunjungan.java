package com.example.klinikmahasiswa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "kunjungan")
@Data

public class Kunjungan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User pasien;

    @Column(nullable = false)
    @NotNull(message = "Tanggal Kunjungan harus diisi dengan format YYYY-MM-DD.")
    private LocalDate tanggalKunjungan;

    @Column(nullable = false)
    private String status = "MENDATANG"; // Default status

    @ManyToOne
    private User perawat;

    private String keluhan;

    // Informasi obat dan jumlah yang diberikan
    @ElementCollection
    @CollectionTable(name = "obat_kunjungan", joinColumns = @JoinColumn(name = "kunjungan_id"))
    @MapKeyColumn(name = "obat_id")
    @Column(name = "jumlah")
    private Map<Long, Integer> obatJumlah = new HashMap<>(); // Map (obat_id -> jumlah)
}
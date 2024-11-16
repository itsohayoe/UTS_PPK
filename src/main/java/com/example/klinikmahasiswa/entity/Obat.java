package com.example.klinikmahasiswa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "obat")
@Data

public class Obat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String deskripsi;

    @Column(nullable = false)
    private int stok;
}

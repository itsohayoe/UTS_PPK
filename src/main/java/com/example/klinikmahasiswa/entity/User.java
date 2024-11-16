package com.example.klinikmahasiswa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private Long id; // NIM as primary key, required
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // Role enum (PASIEN, PERAWAT)
    @Enumerated(EnumType.STRING)
    private Gender jk; // Enum untuk jenis kelamin (L, P)
    @Enumerated(EnumType.STRING)
    private Prodi prodi; // Enum untuk prodi (D3_ST, D4_ST, D4_KS)

    private Double berat;
    private Double tinggi;
    private String whatsapp;

    @Transient
    private String kode; // Kode untuk perawat

    public enum Role { PASIEN, PERAWAT }
    public enum Gender { L, P }
    public enum Prodi { D3_ST, D4_ST, D4_KS }
}

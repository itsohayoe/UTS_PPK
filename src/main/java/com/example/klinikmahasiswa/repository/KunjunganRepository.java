package com.example.klinikmahasiswa.repository;

import com.example.klinikmahasiswa.entity.Kunjungan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KunjunganRepository extends JpaRepository<Kunjungan, Long> {
}

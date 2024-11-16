package com.example.klinikmahasiswa.repository;

import com.example.klinikmahasiswa.entity.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObatRepository extends JpaRepository<Obat, Long> {
}

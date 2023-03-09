package com.example.javaserver.repository;

import com.example.javaserver.model.Cutscenes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CutscenesRepository extends JpaRepository<Cutscenes, String> {
    void deleteByCsCode(String csCode);
}

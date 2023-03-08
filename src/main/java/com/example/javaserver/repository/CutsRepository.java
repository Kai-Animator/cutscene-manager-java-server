package com.example.javaserver.repository;

import com.example.javaserver.model.Cuts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CutsRepository extends JpaRepository<Cuts, Integer> {
    List<Cuts> findByCsCode(String csCode);
    List<Cuts> findByCsCodeAndCutName(String csCode, String cutName);

    void deleteByCsCodeAndCutName(String csCode, String cutName);
}

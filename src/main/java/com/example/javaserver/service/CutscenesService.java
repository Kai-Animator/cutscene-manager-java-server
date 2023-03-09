package com.example.javaserver.service;

import com.example.javaserver.repository.CutscenesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CutscenesService {
    private final CutscenesRepository cutscenesRepository;

    public CutscenesService(CutscenesRepository cutscenesRepository) {
        this.cutscenesRepository = cutscenesRepository;
    }

    @Transactional
    public void deleteCutscene(String csCode) {
        cutscenesRepository.deleteByCsCode(csCode);
    }
}

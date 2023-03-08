package com.example.javaserver.service;

import com.example.javaserver.repository.CutsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CutsService {
    private final CutsRepository cutsRepository;

    public CutsService(CutsRepository cutsRepository) {
        this.cutsRepository = cutsRepository;
    }

    @Transactional
    public void deleteCut(String csCode, String cutName) {
        cutsRepository.deleteByCsCodeAndCutName(csCode, cutName);
    }
}

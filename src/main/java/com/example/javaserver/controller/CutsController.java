package com.example.javaserver.controller;

import com.example.javaserver.model.Cuts;
import com.example.javaserver.repository.CutsRepository;
import com.example.javaserver.service.CutsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping
public class CutsController {
    private final CutsRepository cutsRepository;
    private final CutsService cutsService;

    public CutsController(CutsRepository cutsRepository, CutsService cutsService) {
        this.cutsRepository = cutsRepository;
        this.cutsService =  cutsService;
    }

    @GetMapping("/cutscenes/{csCode}/cuts")
    public ResponseEntity<List<Cuts>> getAllCuts(@PathVariable String csCode) {
        List<Cuts> cutsList = cutsRepository.findByCsCode(csCode);
        return ResponseEntity.ok().body(cutsList);
    }

    @GetMapping("/cutscenes/{csCode}/cuts/{cutName}")
    public ResponseEntity<List<Cuts>> getCutsInfo(@PathVariable String csCode, @PathVariable String cutName) {
        List<Cuts> cutslist = cutsRepository.findByCsCodeAndCutName(csCode,cutName);
        if (cutslist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cutslist);
        }
    }

    @PostMapping("/cutscenes/{csCode}/cuts")
    public ResponseEntity createNewCut(@PathVariable String csCode, @RequestBody Cuts newCutInfo) {
        Cuts newCut = new Cuts();
        newCut.setCutName(newCutInfo.getCutName());
        newCut.setCutStatus(newCutInfo.getCutStatus());
        newCut.setCutScene(newCutInfo.getCutScene());
        newCut.setCsCode(newCutInfo.getCsCode());
        newCut.setAnimator(newCutInfo.getAnimator());
        newCut.setStartDate(newCutInfo.getStartDate() != null ? newCutInfo.getStartDate() : null);
        newCut.setEndDate(newCutInfo.getEndDate() != null ? newCutInfo.getEndDate() : null);
        cutsRepository.save(newCut);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCut);
    }

    @DeleteMapping("/cutscenes/{csCode}/cuts/{cutName}")
    public ResponseEntity<Void> deleteCut(@PathVariable String csCode, @PathVariable String cutName) {
        cutsService.deleteCut(csCode, cutName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cutscenes/{csCode}/cuts/{cutName}")
    public ResponseEntity<Void> updateCut(@PathVariable String csCode, @PathVariable String cutName, @RequestBody Cuts editedCut) {
        List<Cuts>cutChanged = cutsRepository.findByCsCodeAndCutName(csCode, cutName);
        if(!cutChanged.isEmpty()) {
            for(Cuts existingCut : cutChanged) {
                existingCut.setCutName(editedCut.getCutName());
                existingCut.setCutStatus(editedCut.getCutStatus());
                existingCut.setAnimator(editedCut.getAnimator());
                existingCut.setStartDate(editedCut.getStartDate());
                existingCut.setEndDate(editedCut.getEndDate());
                cutsRepository.save(existingCut);
            }
            return ResponseEntity.ok().build();
        } else {
            return  ResponseEntity.notFound().build();
        }
    }
}
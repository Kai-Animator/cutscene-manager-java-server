package com.example.javaserver.controller;

import com.example.javaserver.model.Cuts;
import com.example.javaserver.model.Cutscenes;
import com.example.javaserver.repository.CutsRepository;
import com.example.javaserver.repository.CutscenesRepository;
import com.example.javaserver.service.CutscenesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping
public class CutscenesController {
    private final CutscenesRepository cutscenesRepository;
    private final CutsRepository cutsRepository;
    private final CutscenesService cutscenesService;

    public CutscenesController(CutscenesRepository cutscenesRepository, CutsRepository cutsRepository, CutscenesService cutscenesService) {
        this.cutscenesRepository = cutscenesRepository;
        this.cutsRepository = cutsRepository;
        this.cutscenesService = cutscenesService;
    }
    @GetMapping("/cutscenes")
    public ResponseEntity getAllCutscenes() {
        return ResponseEntity.ok(this.cutscenesRepository.findAll());
    }

    @GetMapping("/cutscenes/{csCode}")
    public ResponseEntity getCutSceneInfo(@PathVariable String csCode) {
        Optional<Cutscenes> optionalCutScene = cutscenesRepository.findById(csCode);
        if (optionalCutScene.isPresent()) {
            return ResponseEntity.ok(optionalCutScene.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/cutscenes")
    public ResponseEntity createNewCutscene(@RequestBody Cutscenes newCutsceneInfo) {
        Optional<Cutscenes> optionalCutScene = cutscenesRepository.findById(newCutsceneInfo.getCsCode());
        int cutNumber = newCutsceneInfo.getTotalCuts();
        if(optionalCutScene.isPresent()) {
            return ResponseEntity.ok("no good!");
        } else {
            Cutscenes newCutscene = new Cutscenes();
            newCutscene.setCsCode(newCutsceneInfo.getCsCode());
            newCutscene.setCsName(newCutsceneInfo.getCsName());
            newCutscene.setDirector(newCutsceneInfo.getDirector());
            newCutscene.setDueDate(newCutsceneInfo.getDueDate() != null ? newCutsceneInfo.getDueDate() : null);
            newCutscene.setEndDate(newCutsceneInfo.getEndDate() != null ? newCutsceneInfo.getEndDate() : null);
            newCutscene.setStartDate(newCutsceneInfo.getStartDate());
            newCutscene.setSceneStatus(newCutsceneInfo.getSceneStatus());
            Cutscenes savedCutscene = cutscenesRepository.save(newCutscene);

            for (int i = 1; i <= newCutsceneInfo.getTotalCuts(); i++) {
                Cuts newCut = new Cuts();
                newCut.setCsCode(newCutsceneInfo.getCsCode());
                newCut.setCutName(i < 10 ? "0" + i + "xa" : i + "xa");
                newCut.setStartDate(null);
                newCut.setEndDate(null);
                newCut.setAnimator(newCutsceneInfo.getDirector());
                newCut.setCutStatus("Not Started");
                cutsRepository.save(newCut);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCutscene);
        }
    }

    @DeleteMapping("/cutscenes/{csCode}")
    public ResponseEntity<Void> deleteCutscene(@PathVariable String csCode) {
        cutscenesService.deleteCutscene(csCode);
        return ResponseEntity.noContent().build();
    }
}

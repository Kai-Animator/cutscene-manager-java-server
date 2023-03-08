package com.example.javaserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Cutscenes {
    @Id
    @Column(name = "cs_code")
    private String csCode;
    @Column(name = "cs_name")
    private String csName;
    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @Column(name = "due_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    @Column(name = "director", nullable = false)
    private String director;
    @Column(name = "scene_status", nullable = false)
    private String sceneStatus;
    @OneToMany(mappedBy = "cutScene", cascade = CascadeType.ALL)
    private List<Cuts> cuts;
    public Cutscenes() {
    }

    private Integer totalCuts;

    public Cutscenes(String csCode, String csName, LocalDate startDate, LocalDate endDate, LocalDate dueDate, String director, String sceneStatus) {
        this.csCode = csCode;
        this.csName = csName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.director = director;
        this.sceneStatus = sceneStatus;
    }

    public Integer getTotalCuts() {
        return totalCuts;
    }

    public void setTotalCuts(Integer totalCuts) {
        this.totalCuts = totalCuts;
    }

    public String getCsCode() {
        return csCode;
    }

    public void setCsCode(String csCode) {
        this.csCode = csCode;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSceneStatus() {
        return sceneStatus;
    }

    public void setSceneStatus(String sceneStatus) {
        this.sceneStatus = sceneStatus;
    }
}

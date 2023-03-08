package com.example.javaserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "cuts")
public class Cuts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cut_id")
    private Integer cutId;

    @Column(name = "cut_name", nullable = false)
    private String cutName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "cs_code", nullable = false)
    private String csCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cs_code", referencedColumnName = "cs_code", insertable = false,updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cutscenes cutScene;

    @Column(name = "animator")
    private String animator;

    @Column(name = "cut_status", nullable = false)
    private String cutStatus;

    public Cuts() {
    }

    public Cuts(Integer cutId, String cutName, LocalDate startDate, LocalDate endDate, String csCode, Cutscenes cutScene, String animator, String cutStatus) {
        this.cutId = cutId;
        this.cutName = cutName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.csCode = csCode;
        this.cutScene = cutScene;
        this.animator = animator;
        this.cutStatus = cutStatus;
    }

    public Integer getCutId() {
        return cutId;
    }

    public void setCutId(Integer cutId) {
        this.cutId = cutId;
    }

    public String getCutName() {
        return cutName;
    }

    public void setCutName(String cutName) {
        this.cutName = cutName;
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

    public String getCsCode() {
        return csCode;
    }

    public void setCsCode(String csCode) {
        this.csCode = csCode;
    }

    public Cutscenes getCutScene() {
        return cutScene;
    }

    public void setCutScene(Cutscenes cutScene) {
        this.cutScene = cutScene;
    }

    public String getAnimator() {
        return animator;
    }

    public void setAnimator(String animator) {
        this.animator = animator;
    }

    public String getCutStatus() {
        return cutStatus;
    }

    public void setCutStatus(String cutStatus) {
        this.cutStatus = cutStatus;
    }
}

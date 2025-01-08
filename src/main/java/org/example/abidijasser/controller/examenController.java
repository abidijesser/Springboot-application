package org.example.abidijasser.controller;

import org.example.abidijasser.entity.*;
import org.example.abidijasser.service.serviceExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class examenController {

    @Autowired
    private serviceExam serviceExam;

    @PostMapping( "/addClinique")
    public Clinique addClinique(@RequestBody Clinique clinique){
        return serviceExam.addClinique(clinique);
    }

    @PostMapping( "/addPatient")
    public Patient addPatient(@RequestBody  Patient patient){
        return serviceExam.addPatient(patient);
    }

    @PostMapping( "/addMedecinAndAssignToClinique/{cliniqueId}")
    public Medecin addMedecinAndAssignToClinique(@RequestBody  Medecin medecin,@PathVariable Long cliniqueId){
        return serviceExam.addMedecinAndAssignToClinique(medecin, cliniqueId);
    }

    @PostMapping( "/addRDVAndAssignMedAndPatient/{idMedecin}/{idPatient}")
    public void addRDVAndAssignMedAndPatient(@RequestBody  RendezVous rdv, @PathVariable Long idMedecin, @PathVariable Long idPatient) {
        serviceExam.addRDVAndAssignMedAndPatient(rdv, idMedecin, idPatient);
    }

    @GetMapping( "/getRendezVousByCliniqueAndSpecialite/{idClinique}/{specialite}")
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(@PathVariable  Long idClinique,@PathVariable Specialite specialite) {
        return serviceExam.getRendezVousByCliniqueAndSpecialite(idClinique, specialite);
    }

    @GetMapping( "/getNbrRendezVousMedecin/{idMedecin}")
    public int getNbrRendezVousMedecin(@PathVariable Long idMedecin) {
        return serviceExam.getNbrRendezVousMedecin(idMedecin);
    }

    @GetMapping( "/getRevenueMedecin/{idMedecin}/{startDate}/{endDate}")
    public int getRevenueMedecin(@PathVariable Long idMedecin
            , @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate
            , @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        return serviceExam.getRevenueMedecin(idMedecin, startDate, endDate);
    }



}

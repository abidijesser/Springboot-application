package org.example.abidijasser.service;

import org.example.abidijasser.entity.*;

import java.util.Date;
import java.util.List;

public interface serviceExam {

    public Clinique addClinique(Clinique clinique);

    public Medecin addMedecinAndAssignToClinique(Medecin medecin, Long cliniqueId);

    public Patient addPatient(Patient patient);

    public void addRDVAndAssignMedAndPatient(RendezVous rdv, Long idMedecin, Long idPatient);

    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long idClinique, Specialite specialite);

    public int getNbrRendezVousMedecin(Long idMedecin);

    int getRevenueMedecin(Long idMedecin, Date startDate, Date endDate);

}

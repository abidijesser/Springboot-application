package org.example.abidijasser.service;

import lombok.extern.slf4j.Slf4j;
import org.example.abidijasser.entity.*;
import org.example.abidijasser.repository.CliniqueRepository;
import org.example.abidijasser.repository.MedecinRepository;
import org.example.abidijasser.repository.PatientRepository;
import org.example.abidijasser.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class serviceExamImpl implements serviceExam {

    @Autowired
    private CliniqueRepository cliniqueRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Override
    public Clinique addClinique(Clinique clinique) {
        return cliniqueRepository.save(clinique);
    }

    @Override
    public Medecin addMedecinAndAssignToClinique(Medecin medecin, Long cliniqueId) {

        Clinique c=cliniqueRepository.findById(cliniqueId).orElse(null);

        if (c != null) {

            if(c.getListMedecins().isEmpty()){
                List<Medecin> medecins= new ArrayList<>();
                medecins.add(medecin);
                c.setListMedecins(medecins);
            }
            else {
                c.getListMedecins().add(medecin);
            }

        }
        else {
            throw new IllegalArgumentException("Clinique with ID " + cliniqueId + " does not exist");
        }

        medecinRepository.save(medecin);
        return medecin ;
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void addRDVAndAssignMedAndPatient(RendezVous rdv, Long idMedecin, Long idPatient){

        Patient patient=patientRepository.findById(idPatient).orElse(null);
        Medecin medecin=medecinRepository.findById(idMedecin).orElse(null);

        if(patient!=null && medecin!=null){
            rdv.setPatient(patient);
            rdv.setMedecins(medecin);

            rendezVousRepository.save(rdv);
        }
        else {
            throw new IllegalArgumentException("l'un des IDs est incorrect");
        }
    }

    @Override
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(Long idClinique, Specialite specialite){
        Clinique clinique=cliniqueRepository.findById(idClinique).orElse(null);

        List<RendezVous> list=rendezVousRepository.findAll();
        List<RendezVous> resultat=new ArrayList<>();

        for(RendezVous r:list){
            if(r.getMedecins().getListClinique()!=null){
                for(Clinique c:r.getMedecins().getListClinique()){
                    if(clinique.equals(c) && r.getMedecins().getSpecialite().equals(specialite)){
                        resultat.add(r);
                    }
                }
            }

        }

        return resultat;
    }

    @Override
    public int getNbrRendezVousMedecin(Long idMedecin) {
        Medecin medecin=medecinRepository.findById(idMedecin).orElse(null);
        List<RendezVous> rdv=rendezVousRepository.findAll();
        int i=0;
        for(RendezVous r:rdv){
            if(r.getMedecins().equals(medecin)){
                i++;
            }
        }
        return i;
    }



    @Scheduled(cron = "*/30 * * * * *")
    public void retrieveRendezVous(){
        List<RendezVous> list=rendezVousRepository.findAll();
        for(RendezVous r:list){
            if(r.getDateRDV().after(new Date())){
                log.info("La liste des RendezVous :" +r.getDateRDV()+ "Medecin :" +r.getMedecins().getNomMedecin()+ "Patient "+r.getPatient().getNomPatient() );

            }
        }
    }

    @Override
    public int getRevenueMedecin(Long idMedecin, Date startDate, Date endDate) {

        Medecin medecin=medecinRepository.findById(idMedecin).orElse(null);
        List<RendezVous> rdv=rendezVousRepository.findAll();
        int i=0;
        int x=0;
        if(medecin != null){
            x=medecin.getPrixConsultation();
            for(RendezVous r:rdv){
                if(r.getMedecins().equals(medecin) && r.getDateRDV().after(startDate) && r.getDateRDV().before(endDate)){
                    i++;
                }
            }
        }
        return x*i;
    }


}

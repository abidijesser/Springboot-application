package org.example.abidijasser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedecin;

    private String nomMedecin;
    private int telephone;
    private int prixConsultation;

    @Enumerated(EnumType.STRING)
    //enregistrer les enums dans la base de donnees en tant que chaine de caracteres
    private  Specialite specialite;

    @ManyToMany(mappedBy = "listMedecins")
    @JsonIgnore
    private List<Clinique> listClinique;

    @OneToMany(mappedBy = "medecins")
    @JsonIgnore
    private List<RendezVous> listRendezVous;



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

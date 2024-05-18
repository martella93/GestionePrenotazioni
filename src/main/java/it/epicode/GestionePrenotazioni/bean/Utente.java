package it.epicode.GestionePrenotazioni.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Utente {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "utente")
    private Set<Prenotazione> prenotazioni;
}

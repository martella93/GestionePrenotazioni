package it.epicode.GestionePrenotazioni.bean;

import it.epicode.GestionePrenotazioni.enums.Tipo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Postazione {

    @Id
    @GeneratedValue
    private int id;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private int numMaxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private Set<Prenotazione> prenotazioni;
}

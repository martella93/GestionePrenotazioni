package it.epicode.GestionePrenotazioni.repository;

import it.epicode.GestionePrenotazioni.bean.Postazione;
import it.epicode.GestionePrenotazioni.bean.Prenotazione;
import it.epicode.GestionePrenotazioni.bean.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    List<Prenotazione> findByPostazioneAndData(Postazione postazione, LocalDate data);

    List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate dataPrenotazione);
}

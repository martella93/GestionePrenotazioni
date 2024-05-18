package it.epicode.GestionePrenotazioni.service;

import it.epicode.GestionePrenotazioni.bean.Postazione;
import it.epicode.GestionePrenotazioni.bean.Prenotazione;
import it.epicode.GestionePrenotazioni.bean.Utente;
import it.epicode.GestionePrenotazioni.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public void inserisciPrenotazione(Prenotazione prenotazione){
        prenotazioneRepository.save(prenotazione);
    }

    public void cancellaPrenotazione(int id){
        prenotazioneRepository.deleteById(id);
    }

    public void creaPrenotazione(Postazione postazione, Utente utente, LocalDate data) {
        List<Prenotazione> prenotazioniUtente = prenotazioneRepository.findByUtenteAndData(utente, data);
        if (!prenotazioniUtente.isEmpty()) {
            System.out.println("L'utente ha già una prenotazione per questa data: " + data);
            return;
        }

        if (!isPostazioneDisponibile(postazione, data)) {
            System.out.println("La postazione non è disponibile per la data specificata: " + data);
            return;
        }


        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setPostazione(postazione);
        prenotazione.setUtente(utente);
        prenotazione.setData(data);
        prenotazioneRepository.save(prenotazione);
        System.out.println("Prenotazione creata con successo per la data: " + data);
    }



    public boolean isPostazioneDisponibile(Postazione postazione, LocalDate dataPrenotazione) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByPostazioneAndData(postazione, dataPrenotazione);

        if (!prenotazioni.isEmpty()) {
            System.out.println("La postazione non è disponibile per l'intera giornata.");
            return false;
        }

        System.out.println("La postazione è disponibile per l'intera giornata.");
        return true;
    }




}

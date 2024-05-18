package it.epicode.GestionePrenotazioni.service;

import it.epicode.GestionePrenotazioni.bean.Prenotazione;
import it.epicode.GestionePrenotazioni.bean.Utente;
import it.epicode.GestionePrenotazioni.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneService prenotazioneService;

    public void inserisciUtente(Utente utente){
        utenteRepository.save(utente);
    }
    public void cancellaUtente(int id){
        utenteRepository.deleteById(id);
    }
    public Utente creaUtente(String nome,String username, String email) {
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setNome(nome);
        utente.setEmail(email);
        return utenteRepository.save(utente);
    }

    public Utente trovaUtentePerUsername(String username) {
        Optional<Utente> utenteOpt = utenteRepository.findByUsername(username);
        return utenteOpt.orElse(null);
    }
}

package it.epicode.GestionePrenotazioni.service;

import it.epicode.GestionePrenotazioni.bean.Edificio;
import it.epicode.GestionePrenotazioni.bean.Postazione;
import it.epicode.GestionePrenotazioni.enums.Tipo;
import it.epicode.GestionePrenotazioni.repository.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    public void inserisciPostazione(Postazione postazione){
        postazioneRepository.save(postazione);
    }
    public void cancellaPostazione(int id){
        postazioneRepository.deleteById(id);
    }
    public Postazione creaPostazione(String descrizione, Tipo tipo, int numMaxOccupanti, Edificio edificio){
        Postazione postazione = new Postazione();
        postazione.setDescrizione(descrizione);
        postazione.setTipo(tipo);
        postazione.setNumMaxOccupanti(numMaxOccupanti);
        postazione.setEdificio(edificio);
        return postazioneRepository.save(postazione);
    }

    public List<Postazione> ricercaPostazioniPerTipoECitta(String citta, Tipo tipo) {
        return postazioneRepository.findByEdificioCittaAndTipo(citta, tipo);
    }


    public List<Postazione> getPostazioniByEdificio(Edificio edificio) {
        return postazioneRepository.findByEdificio(edificio);
    }

}

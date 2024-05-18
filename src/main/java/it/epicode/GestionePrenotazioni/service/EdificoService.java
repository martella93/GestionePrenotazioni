package it.epicode.GestionePrenotazioni.service;

import it.epicode.GestionePrenotazioni.bean.Edificio;
import it.epicode.GestionePrenotazioni.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificoService {

    @Autowired
    private EdificioRepository edificioRepository;

    private void inserisciEdificio(Edificio edificio){
        edificioRepository.save(edificio);
    }

    private void cancellaEdificio(int id){
        edificioRepository.deleteById(id);
    }

    public Edificio creaEdificio(String nome, String indirizzo, String citta) {
        Edificio edificio = new Edificio();
        edificio.setNome(nome);
        edificio.setIndirizzo(indirizzo);
        edificio.setCitta(citta);
        return edificioRepository.save(edificio);
    }
    public List<String> getAllCitta() {
        return edificioRepository.findAllCitta();
    }
    public List<Edificio> getEdificiByCitta(String citta) {
        return edificioRepository.findByCitta(citta);
    }

}

package it.epicode.GestionePrenotazioni.repository;

import it.epicode.GestionePrenotazioni.bean.Edificio;
import it.epicode.GestionePrenotazioni.bean.Postazione;
import it.epicode.GestionePrenotazioni.enums.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Integer> {

    List<Postazione> findByEdificioCittaAndTipo(String citta, Tipo tipo);


    List<Postazione> findByEdificio(Edificio edificio);


}

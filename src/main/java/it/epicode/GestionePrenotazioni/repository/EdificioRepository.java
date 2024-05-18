package it.epicode.GestionePrenotazioni.repository;

import it.epicode.GestionePrenotazioni.bean.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
    @Query("SELECT DISTINCT e.citta FROM Edificio e")
    List<String> findAllCitta();

    List<Edificio> findByCitta(String citta);
}

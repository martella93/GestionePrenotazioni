package it.epicode.GestionePrenotazioni;

import it.epicode.GestionePrenotazioni.bean.Edificio;
import it.epicode.GestionePrenotazioni.bean.Postazione;
import it.epicode.GestionePrenotazioni.bean.Utente;
import it.epicode.GestionePrenotazioni.enums.Tipo;
import it.epicode.GestionePrenotazioni.service.EdificoService;
import it.epicode.GestionePrenotazioni.service.PostazioneService;
import it.epicode.GestionePrenotazioni.service.PrenotazioneService;
import it.epicode.GestionePrenotazioni.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private EdificoService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;

    @Override
    public void run(String... args) throws Exception {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(GestionePrenotazioniApplication.class);

//        Edificio edificio = edificioService.creaEdificio("Posta", "via liborio romano","Bari");
//        Edificio edificio1 = edificioService.creaEdificio("Banca", "via dante aligheri","Roma");
//        Edificio edificio2 = edificioService.creaEdificio("Posta", "via san giovanni","Lecce");
//        Edificio edificio3 = edificioService.creaEdificio("Posta", "via della via","Milano");
//        Edificio edificio4 = edificioService.creaEdificio("Banca", "via della spiga","Bari");
//        Edificio edificio5 = edificioService.creaEdificio("Banca", "via marco rossi","Verona");
//
//
//
//        Postazione postazione = postazioneService.creaPostazione("A1", Tipo.PRIVATO,5, edificio);
//        Postazione postazione1 = postazioneService.creaPostazione("A4", Tipo.OPENSPACE,50, edificio1);
//        Postazione postazione2 = postazioneService.creaPostazione("A250", Tipo.SALA_RIUNIONI,20, edificio2);
//        Postazione postazione3 = postazioneService.creaPostazione("A15", Tipo.PRIVATO,10, edificio);
//        Postazione postazione4 = postazioneService.creaPostazione("A65", Tipo.PRIVATO,5, edificio);


        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Effettua una prenotazione (nuovo utente)");
            System.out.println("2. Effettua una prenotazione (utente registrato)");
            System.out.println("3. Ricerca postazioni per città e tipo");
            System.out.println("4. Esci");

            int choice;
            while (true) {
                System.out.print("Inserisci la tua scelta: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    break;
                } else {
                    System.out.println("Input non valido. Inserisci un numero.");
                    scanner.next(); // consume invalid input
                }
            }
            switch (choice) {
                case 1:
                    effettuaPrenotazioneNuovoUtente(scanner);
                    break;
                case 2:
                    effettuaPrenotazioneUtenteRegistrato(scanner);
                    break;
                case 3:
                    ricercaPostazioniPerCittaETipo(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }

        scanner.close();
    }


    private void effettuaPrenotazioneNuovoUtente(Scanner scanner) {
        System.out.println("Inserisci il nome dell'utente:");
        String nomeUtente = scanner.nextLine();
        System.out.println("Inserisci l'username:");
        String username = scanner.nextLine();
        System.out.println("Inserisci l'email:");
        String email = scanner.nextLine();

        Utente utente = utenteService.creaUtente(nomeUtente, username, email);
        effettuaPrenotazioneConUtente(scanner, utente);
    }
    private void effettuaPrenotazioneUtenteRegistrato(Scanner scanner) {
        System.out.println("Inserisci l'username dell'utente registrato:");
        String username = scanner.nextLine();

        Utente utente = utenteService.trovaUtentePerUsername(username);
        if (utente != null) {
            System.out.println("Utente trovato: " + utente.getNome());
            effettuaPrenotazioneConUtente(scanner, utente);
        } else {
            System.out.println("Utente non trovato. Riprova.");
        }
    }

    private void effettuaPrenotazioneConUtente(Scanner scanner, Utente utente) {
        System.out.println("Scegli la città dove vuoi prenotare");
        System.out.println("Città disponibili:");
        List<String> cittaDisponibili = edificioService.getAllCitta();
        for (String citta : cittaDisponibili) {
            System.out.println("- " + citta);
        }
        System.out.println("Inserisci il nome della città dove vuoi prenotare: ");
        String citta = scanner.nextLine();

        System.out.println("Edifici disponibili nella città " + citta + ":");
        List<Edificio> edificiInCitta = edificioService.getEdificiByCitta(citta);
        for (Edificio edificio : edificiInCitta) {
            System.out.println("- " + edificio.getNome() + " (" + edificio.getIndirizzo() + ")");
        }

        System.out.println("Inserisci il nome dell'edificio in cui vuoi prenotare: ");
        String nomeEdificio = scanner.nextLine();

        Edificio edificioScelto = null;
        for (Edificio edificio : edificiInCitta) {
            if (edificio.getNome().equalsIgnoreCase(nomeEdificio)) {
                edificioScelto = edificio;
                break;
            }
        }
        if (edificioScelto != null) {
            List<Postazione> postazioni = postazioneService.getPostazioniByEdificio(edificioScelto);
            if (!postazioni.isEmpty()) {
                System.out.println("Postazioni disponibili nell'edificio:");
                for (int i = 0; i < postazioni.size(); i++) {
                    Postazione postazione = postazioni.get(i);
                    System.out.println((i + 1) + ". Descrizione: " + postazione.getDescrizione() +
                            ", Tipo: " + postazione.getTipo() +
                            ", Numero massimo occupanti: " + postazione.getNumMaxOccupanti());
                }

                int numeroPostazione = getIntInput(scanner);
                if (numeroPostazione >= 1 && numeroPostazione <= postazioni.size()) {
                    Postazione postazioneScelta = postazioni.get(numeroPostazione - 1);

                    System.out.println("Inserisci la data della prenotazione (AAAA-MM-GG): ");
                    String dataPrenotazioneStr = scanner.nextLine();
                    LocalDate dataPrenotazione = LocalDate.parse(dataPrenotazioneStr);

                    try {
                        prenotazioneService.creaPrenotazione(postazioneScelta, utente, dataPrenotazione);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
        }
    }

    private int getIntInput(Scanner scanner) {
        System.out.print("Inserisci il numero della postazione desiderata: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Input non valido. Inserisci un numero.");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private void ricercaPostazioniPerCittaETipo(Scanner scanner) {
        System.out.println("Scegli la città dove vuoi cercare le postazioni");
        System.out.println("Città disponibili:");
        List<String> cittaDisponibili = edificioService.getAllCitta();
        for (String citta : cittaDisponibili) {
            System.out.println("- " + citta);
        }
        System.out.print("Inserisci il nome della città: ");
        String citta = scanner.nextLine();

        System.out.println("Scegli il tipo di postazione:");
        for (Tipo tipo : Tipo.values()) {
            System.out.println("- " + tipo);
        }
        System.out.print("Inserisci il tipo di postazione: ");
        String tipoStr = scanner.nextLine();
        Tipo tipo;
        try {
            tipo = Tipo.valueOf(tipoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo non valido. Riprova.");
            return;
        }

        List<Postazione> postazioni = postazioneService.ricercaPostazioniPerTipoECitta(citta, tipo);
        if (!postazioni.isEmpty()) {
            System.out.println("Postazioni disponibili:");
            for (Postazione postazione : postazioni) {
                System.out.println("Descrizione: " + postazione.getDescrizione() +
                        ", Tipo: " + postazione.getTipo() +
                        ", Numero massimo occupanti: " + postazione.getNumMaxOccupanti() +
                        ", Edificio: " + postazione.getEdificio().getNome() +
                        " (" + postazione.getEdificio().getIndirizzo() + ")");
            }
        } else {
            System.out.println("Nessuna postazione disponibile per la città e il tipo specificati.");
        }
    }



}





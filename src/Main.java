import Core.ContoCorrente;
import Core.Movimento;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import GUI.*;


public class Main {
  public static void main(String[] args) {
    System.out.println("[TEST] Inizio test creazione movimenti");
    Movimento t1 = new Movimento(100, "Entrata di prova", LocalDateTime.now());
    Movimento t2 = new Movimento(-100, "Uscita di prova", LocalDateTime.now());
    System.out.println("1:");
    System.out.println(" " + t1);
    System.out.println("2:");
    System.out.println(" " + t2);

    System.out.println("\n\n");
    System.out.println("-----------------------");
    System.out.println("[TEST] Inizio test salvataggio dati su file");
    // Crea un nuovo conto corrente
    ContoCorrente cc = new ContoCorrente(123456, 1000.0f, "Mario Rossi");

    // Aggiunge alcuni movimenti al conto corrente
    cc.addMovimento(new Movimento(100.0f, "Pagamento bolletta", LocalDateTime.now()));
    cc.addMovimento(new Movimento(-50.0f, "Prelevamento bancomat", LocalDateTime.now()));

    // Scrive i dati del conto corrente su file
    try {
      cc.writeToFile("conto_corrente.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("-----------------------");

    System.out.println("\n\n");
    System.out.println("-----------------------");
    System.out.println("[TEST] Inizio test caricamento dati da file");
    // Crea un nuovo conto corrente vuoto
    ContoCorrente cc2 = new ContoCorrente(0, 0.0f, "");

    // Legge i dati del conto corrente da file
    try {
      cc2.readFromFile("conto_corrente.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Stampa i dati del conto corrente letto da file
    System.out.println("Numero CC: " + cc2.getNumeroCC());
    System.out.println("Saldo attuale: " + cc2.getSaldoAttuale());
    System.out.println("Intestatario: " + cc2.getIntestatario());
    for (int i = 0; i < cc2.getContatoreMovimenti(); i++) {
      Movimento movimento = cc2.getMovimento(i);
      System.out.println("Movimento " + i + ": " + movimento.getAmount() + " (" + movimento.getDescription() + ") " + movimento.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    System.out.println("-----------------------");

    // Init GUI
    MainFrame main = new MainFrame();
    main.setVisible(true);
  }
}

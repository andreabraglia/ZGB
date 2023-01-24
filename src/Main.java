import Core.ContoCorrente;
import Core.Movimento;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import GUI.*;

import javax.swing.*;


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
    cc.addMovimento(new Movimento(-100.0f, "Pagamento bolletta", LocalDateTime.now()));
    cc.addMovimento(new Movimento(150.0f, "Addebito stipendio", LocalDateTime.now()));
    cc.addMovimento(new Movimento(-50.0f, "Prelevamento bancomat", LocalDateTime.now()));

    // Scrive i dati del conto corrente su file

    System.out.println("-----------------------");

    System.out.println("\n\n");
    System.out.println("-----------------------");

    // Init GUI
    MainFrame main = null;
    try {
      main = new MainFrame(cc);
      main.setVisible(true);
////      cc.readFromFile("conto_corrente_data.dataa");
    } catch (ParseException error) {
      JOptionPane.showMessageDialog(main, error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
//      error.printStackTrace();
    }
  }
}

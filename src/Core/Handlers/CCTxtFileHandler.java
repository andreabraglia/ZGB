package Core.Handlers;

import Core.ContoCorrente;
import Core.Movimento;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe che gestisce la scrittura e la lettura di un file di testo
 */
public class CCTxtFileHandler implements FileHandler {

  /**
   * Legge un file di testo e restituisce un oggetto ContoCorrente
   *
   * @param fileName      Il nome del file da cui leggere.
   * @param separator     Il separatore da usare per separare i campi nel file.
   * @param contoCorrente Il conto corrente da caricare.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  protected static void writeToFile(String fileName, String separator, ContoCorrente contoCorrente) throws IOException {
    // Apre il file in modalità di scrittura
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

    // Scrive il numero del conto corrente
    writer.write(Integer.toString(contoCorrente.getNumeroCC()));
    writer.newLine();

    // Scrive il saldo attuale del conto corrente
    writer.write(Float.toString(contoCorrente.getSaldoAttuale()));
    writer.newLine();

    // Scrive il intestatario del conto corrente
    writer.write(contoCorrente.getIntestatario());
    writer.newLine();

    ArrayList<Movimento> movimenti = contoCorrente.getMovimenti();

    // Scrive i movimenti del conto corrente
    for (Movimento movimento : movimenti) {
      writer.write(Float.toString(movimento.getAmount()));
      writer.write(separator);
      writer.write(movimento.getDescription());
      writer.write(separator);
      writer.write(movimento.getDate().format(Movimento.FORMATTER));
      writer.newLine();
    }

    // Chiude il file
    writer.close();
  }

  /**
   * Scrive un oggetto ContoCorrente su un file di testo
   *
   * @param fileName      Il nome del file su cui scrivere.
   * @param separator     Il separatore da usare per separare i campi nel file.
   * @param contoCorrente Il conto corrente da scrivere.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  protected static void readFromFile(String fileName, String separator, ContoCorrente contoCorrente) throws IOException {
    // Apre il file in modalità di lettura
    BufferedReader reader = new BufferedReader(new FileReader(fileName));

    // Legge il numero del conto corrente
    int numeroCC = Integer.parseInt(reader.readLine());
    contoCorrente.setNumeroCC(numeroCC);

    // Legge il saldo attuale del conto corrente
    float saldoIniziale = Float.parseFloat(reader.readLine());
    contoCorrente.setSaldoIniziale(saldoIniziale);

    // Legge il intestatario del conto corrente
    String intestatario = reader.readLine();
    contoCorrente.setIntestatario(intestatario);

    // Legge i movimenti del conto corrente
    String line;

    while ((line = reader.readLine()) != null) {
      String[] parts = line.split("\\" + separator);

      float amount = Float.parseFloat(parts[0]);
      String description = parts[1];
      LocalDateTime date = LocalDateTime.parse(parts[2], Movimento.FORMATTER);
      Movimento movimento = new Movimento(amount, description, date);

      contoCorrente.addMovimento(movimento);
    }

    // Chiude il file
    reader.close();
  }

  /**
   * Scrive un oggetto ContoCorrente su un file di testo
   *
   * @param fileName      Il nome del file su cui scrivere.
   * @param contoCorrente Il conto corrente da scrivere.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  public void write(String fileName, ContoCorrente contoCorrente) throws IOException {
    System.out.println("Salvataggio in corso...");
    writeToFile(fileName, "|", contoCorrente);
  }

  /**
   * Legge un file di testo e restituisce un oggetto ContoCorrente
   *
   * @param fileName      Il nome del file su cui scrivere.
   * @param contoCorrente Il conto corrente da scrivere.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  public void read(String fileName, ContoCorrente contoCorrente) throws IOException {
    System.out.println("Lettura in corso...");
    readFromFile(fileName, "|", contoCorrente);
  }
}

package Core.Handlers;

import Core.ContoCorrente;

import java.io.IOException;

/**
 * Classe che gestisce il salvataggio e il caricamento di un conto corrente in formato CSV.
 */
public class CCCsvFileHandler extends CCTxtFileHandler implements FileHandler {

  /**
   * Sovrascrive il metodo {@link CCTxtFileHandler#read(String, ContoCorrente)}.
   * Scrive il conto corrente su un file CSV.
   *
   * @param fileName      Il nome del file su cui scrivere.
   * @param contoCorrente Il conto corrente da scrivere.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  @Override
  public void write(String fileName, ContoCorrente contoCorrente) throws IOException {
    writeToFile(fileName, ",", contoCorrente);
  }

  /**
   * Sovrascrive il metodo {@link CCTxtFileHandler#read(String, ContoCorrente)}.
   * Carica un conto corrente da un file CSV.
   *
   * @param fileName      Il nome del file da cui caricare.
   * @param contoCorrente Il conto corrente da caricare.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  @Override
  public void read(String fileName, ContoCorrente contoCorrente) throws IOException {
    readFromFile(fileName, ",", contoCorrente);
  }
}

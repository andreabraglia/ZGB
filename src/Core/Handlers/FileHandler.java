package Core.Handlers;

import Core.ContoCorrente;

import java.io.IOException;

/**
 * Interfaccia che gestisce il salvataggio e il caricamento di un conto corrente.
 */
public interface FileHandler {

  /**
   * Scrive il conto corrente su un file.
   *
   * @param fileName      Il nome del file su cui scrivere.
   * @param contoCorrente Il conto corrente da scrivere.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  void write(String fileName, ContoCorrente contoCorrente) throws IOException;

  /**
   * Carica un conto corrente da un file.
   *
   * @param fileName      Il nome del file da cui caricare.
   * @param contoCorrente Il conto corrente da caricare.
   *
   * @throws IOException Se si verifica un errore di I/O.
   */
  void read(String fileName, ContoCorrente contoCorrente) throws IOException;
}

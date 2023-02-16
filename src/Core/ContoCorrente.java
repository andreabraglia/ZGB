package Core;

import Core.Handlers.CCCsvFileHandler;
import Core.Handlers.CCTxtFileHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe che rappresenta un conto corrente
 */
public class ContoCorrente {
  /**
   * Numero del conto corrente
   */
  private int numeroCC;

  /**
   * Saldo iniziale del conto corrente
   */
  private float saldoIniziale;

  /**
   * Lista dei movimenti del conto corrente
   */
  private final ArrayList<Movimento> movimenti;

  /**
   * Contatore dei movimenti
   */
  private int contatoreMovimenti;

  /**
   * Intestatario del conto corrente
   */
  private String intestatario;

  /**
   * Handler per il file TXT
   */
  private final CCTxtFileHandler txtFileHandler = new CCTxtFileHandler();

  /**
   * Handler per il file CSV
   */
  private final CCCsvFileHandler csvFileHandler = new CCCsvFileHandler();

  /**
   * Costruttore del conto corrente
   */
  public ContoCorrente() {
    this(0, 0f, "");
  }

  /**
   * Costruttore del conto corrente
   *
   * @param numeroCC      Numero del conto corrente
   * @param saldoIniziale Saldo iniziale del conto corrente
   * @param intestatario  Intestatario del conto corrente
   */
  public ContoCorrente(int numeroCC, float saldoIniziale, String intestatario) {
    this.numeroCC = numeroCC;
    this.movimenti = new ArrayList<>();
    this.intestatario = intestatario;
    this.contatoreMovimenti = 0;

    this.saldoIniziale = saldoIniziale;
    if (saldoIniziale != 0f) {
      System.out.println("AGGIORNO SALDO: " + saldoIniziale);
      Movimento primoMov = new Movimento(saldoIniziale, "Saldo iniziale", LocalDateTime.now());
      addMovimento(primoMov);
    }
  }

  /**
   * Calcola il saldo attuale del conto corrente
   *
   * @return Saldo attuale
   */
  public float getSaldoAttuale() {
    float saldo = 0;
    for (Movimento movimento : this.movimenti) {
      saldo += movimento.getAmount();
    }

    return saldo;
  }

  /**
   * Aggiunge un movimento al conto corrente
   *
   * @param movimento Movimento da aggiungere {@link Movimento}
   */
  public void addMovimento(Movimento movimento) {
    System.out.println("\nAggiunto movimento: " + movimento);
    movimenti.add(movimento);
    contatoreMovimenti++;
  }

  /**
   * Stampa i movimenti
   */
  public void printMov() {
    for (Movimento mov : this.movimenti) {
      System.out.println(mov);
    }
  }

  /**
   * Stampa il conto corrente
   */
  public void print() {
    System.out.println("Numero CC: " + this.numeroCC);
    System.out.println("Saldo Iniziale: " + this.saldoIniziale);
    System.out.println("Saldo Attuale: " + this.getSaldoAttuale());
    System.out.println("Contatore Movimenti: " + this.contatoreMovimenti);
    System.out.println("Movimenti: ");
    this.printMov();
  }

  /**
   * Legge i dati del conto corrente da un file TXT
   *
   * @param filename Nome del file
   *
   * @throws IOException Eccezione di I/O
   */
  public void readFromTXTFile(String filename) throws IOException {
    txtFileHandler.read(filename, this);
  }

  /**
   * Scrive i dati del conto corrente su un file TXT
   *
   * @param filename Nome del file
   *
   * @throws IOException Eccezione di I/O
   */
  public void writeToTXTFile(String filename) throws IOException {
    txtFileHandler.write(filename, this);
  }

  /**
   * Legge i dati del conto corrente da un file CSV
   *
   * @param filename Nome del file
   *
   * @throws IOException Eccezione di I/O
   */
  public void readFromCSVFile(String filename) throws IOException {
    csvFileHandler.read(filename, this);
  }

  /**
   * Scrive i dati del conto corrente su un file CSV
   *
   * @param filename Nome del file
   *
   * @throws IOException Eccezione di I/O
   */
  public void writeToCSVFile(String filename) throws IOException {
    csvFileHandler.write(filename, this);
  }

  /**
   * Restituisce il numero del conto corrente
   *
   * @return Numero del conto corrente
   */
  public int getNumeroCC() {
    return numeroCC;
  }

  /**
   * Imposta il numero del conto corrente
   *
   * @param numeroCC Numero del conto corrente
   */
  public void setNumeroCC(int numeroCC) {
    this.numeroCC = numeroCC;
  }

  /**
   * Restituisce il saldo iniziale del conto corrente
   *
   * @return Saldo iniziale del conto corrente
   */
  public float getSaldoIniziale() {
    return saldoIniziale;
  }

  /**
   * Imposta il saldo iniziale del conto corrente
   *
   * @param saldoIniziale Saldo iniziale del conto corrente
   */
  public void setSaldoIniziale(float saldoIniziale) {
    this.saldoIniziale = saldoIniziale;
    Movimento movimento = new Movimento(saldoIniziale, "Saldo iniziale", LocalDateTime.now());
    movimenti.add(movimento);
    contatoreMovimenti++;
  }

  /**
   * Restituisce il numero di movimenti
   *
   * @return Numero di movimenti
   */
  public int getContatoreMovimenti() {
    return contatoreMovimenti;
  }

  /**
   * Imposta l'intestatario del conto corrente
   *
   * @param intestatario Intestatario del conto corrente
   */
  public void setIntestatario(String intestatario) {
    this.intestatario = intestatario;
  }

  /**
   * Restituisce l'intestatario del conto corrente
   *
   * @return Intestatario del conto corrente
   */
  public String getIntestatario() {
    return this.intestatario;
  }

  /**
   * Restituisce il movimento in posizione i
   *
   * @param i Posizione del movimento
   *
   * @return Movimento in posizione i
   */
  public Movimento getMovimento(int i) {
    if (i < 0 || i >= this.contatoreMovimenti) {
      return null;
    }

    return (this.movimenti.get(i));
  }

  /**
   * Restituisce true se il conto corrente è vuoto
   *
   * @return true se il conto corrente è vuoto altrimenti false
   */
  public boolean isEmpty() {
    return (this.intestatario.equals("") && this.numeroCC == 0);
  }

  /**
   * Elimina il movimento in posizione i
   *
   * @param index Posizione del movimento
   *
   * @return True se il movimento è stato eliminato altrimenti false
   */
  public boolean deleteMovimento(int index) {
    if (index < 0 || index >= this.contatoreMovimenti) {
      return false;
    }

    this.movimenti.remove(index);

    this.contatoreMovimenti--;

    return true;
  }

  /**
   * Restituisce la lista dei movimenti
   *
   * @return La lista dei movimenti
   */
  public ArrayList<Movimento> getMovimenti() {
    return this.movimenti;
  }

  /**
   * Restituisce il numero di movimenti
   *
   * @return Il numero di movimenti
   */
  public int getNumeroMovimenti() {
    return this.contatoreMovimenti;
  }
}
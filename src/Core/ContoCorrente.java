package Core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;


public class ContoCorrente {
  private int numeroCC;
  private float saldoIniziale;
  private Movimento[] movimenti;
  private int contatoreMovimenti;
  private String intestatario;

  public ContoCorrente(int numeroCC, float saldoIniziale, String intestatario) {
    this.numeroCC = numeroCC;
    this.movimenti = new Movimento[100];
    this.intestatario = intestatario;
    this.contatoreMovimenti = 0;

    this.saldoIniziale = saldoIniziale;
    if (saldoIniziale != 0) {
      this.addMovimento(new Movimento(saldoIniziale, "Saldo iniziale", LocalDateTime.now()));
    }
  }

  public float getSaldoAttuale() {
    float saldo = this.saldoIniziale;
    for (int i = 0; i < this.contatoreMovimenti; i++) {
      saldo += this.movimenti[i].getAmount();
    }

    return saldo;
  }

  public void addMovimento(Movimento movimento) {
    if (this.contatoreMovimenti >= this.movimenti.length) {
      Movimento[] newMovimenti = new Movimento[this.movimenti.length + 100];
      IntStream.range(0, this.movimenti.length).forEach(i -> newMovimenti[i] = this.movimenti[i]);
      this.movimenti = newMovimenti;
    }

    movimenti[contatoreMovimenti] = movimento;
    contatoreMovimenti++;
  }

  public void printMov() {
    for (int i = 0; i < this.contatoreMovimenti; i++) {
      System.out.println(
        " " + i + " -> " +
          this.movimenti[i] + "[" + (this.movimenti[i].getAmount() > 0 ? "Versamento" : "Prelievo") + "]"
      );
    }
  }

  public void print() {
    System.out.println("Numero CC: " + this.numeroCC);
    System.out.println("Saldo Iniziale: " + this.saldoIniziale);
    System.out.println("Saldo Attuale: " + this.getSaldoAttuale());
    System.out.println("Contatore Movimenti: " + this.contatoreMovimenti);
    System.out.println("Movimenti: ");
    this.printMov();
  }

  // Metodo per leggere i dati del conto corrente da file
  private void readFromFile(String fileName, String separator) throws IOException {
    // Apre il file in modalità di lettura
    BufferedReader reader = new BufferedReader(new FileReader(fileName));

    // Legge il numero del conto corrente
    numeroCC = Integer.parseInt(reader.readLine());

    // Legge il saldo attuale del conto corrente
    saldoIniziale = Float.parseFloat(reader.readLine());

    // Legge il intestatario del conto corrente
    intestatario = reader.readLine();

    // Legge i movimenti del conto corrente
    String line;
    contatoreMovimenti = 0;

    while ((line = reader.readLine()) != null) {
      String[] parts = line.split("\\" + separator);

      System.out.println("[DEBUG] Line: " + line);
      for (String part : parts) {
        System.out.println("  [DEBUG] Part:" + part);
      }

      float amount = Float.parseFloat(parts[0]);
      String description = parts[1];
      LocalDateTime date = LocalDateTime.parse(parts[2], Movimento.FORMATTER);
      Movimento movimento = new Movimento(amount, description, date);

      addMovimento(movimento);
    }

    // Chiude il file
    reader.close();
  }

  // Metodo per scrivere i dati del conto corrente su file
  private void writeToFile(String fileName, String separator) throws IOException {
    // Apre il file in modalità di scrittura
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

    // Scrive il numero del conto corrente
    writer.write(Integer.toString(numeroCC));
    writer.newLine();

    // Scrive il saldo attuale del conto corrente
    writer.write(Float.toString(saldoIniziale));
    writer.newLine();

    // Scrive il intestatario del conto corrente
    writer.write(intestatario);
    writer.newLine();

    // Scrive i movimenti del conto corrente
    for (int i = 0; i < contatoreMovimenti; i++) {
      Movimento movimento = movimenti[i];
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

  public void readFromTXTFile(String filename) throws IOException {
    this.readFromFile(filename, "|");
  }

  public void writeToTXTFile(String filename) throws IOException {
    this.writeToFile(filename, "|");
  }

  public void readFromCSVFile(String filename) throws IOException {
    this.readFromFile(filename, ",");
  }

  public void writeToCSVFile(String filename) throws IOException {
    this.writeToFile(filename, ",");
  }

  public int getNumeroCC() {
    return numeroCC;
  }

  public void setNumeroCC(int numeroCC) {
    this.numeroCC = numeroCC;
  }

  public float getSaldoIniziale() {
    return saldoIniziale;
  }

  public void setSaldoIniziale(float saldoIniziale) {
    this.saldoIniziale = saldoIniziale;
  }

  public int getContatoreMovimenti() {
    return contatoreMovimenti;
  }

  public void setIntestatario(String intestatario) {
    this.intestatario = intestatario;
  }

  public String getIntestatario() {
    return this.intestatario;
  }

  public Movimento getMovimento(int i) {
    if (i < 0 || i >= this.contatoreMovimenti) {
      return null;
    }

    return this.movimenti[i];
  }

  public boolean isEmpty() {
    return (this.intestatario.equals("") && this.numeroCC == 0);
  }

  public boolean deleteMovimento(int index) {
    if (index < 0 || index >= this.contatoreMovimenti) {
      return false;
    }

    Movimento[] newMovimenti = new Movimento[this.contatoreMovimenti];

    for (int i = 0; i < this.contatoreMovimenti; i++) {
      if (i == index) {
        continue;
      }
      newMovimenti[i > index ? i - 1 : i] = this.movimenti[i];
    }

    this.movimenti = newMovimenti;
    this.contatoreMovimenti--;

    return true;
  }

  public int getNumeroMovimenti() {
    return this.contatoreMovimenti;
  }
}




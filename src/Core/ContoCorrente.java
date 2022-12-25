package Core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ContoCorrente {
  private int numeroCC;
  private float saldoIniziale;
  private Movimento[] movimenti;
  private int contatoreMovimenti;
  private String intestatario;

  public ContoCorrente(int numeroCC, float saldoIniziale, String intestatario) {
    this.numeroCC = numeroCC;
    this.saldoIniziale = saldoIniziale;
    this.intestatario = intestatario;
    this.movimenti = new Movimento[100];
    this.contatoreMovimenti = 0;
  }

  public float getSaldoAttuale() {
    float saldo = this.saldoIniziale;
    for (int i = 0; i < this.contatoreMovimenti; i++) {
      saldo += this.movimenti[i].getAmount();
    }

    return saldo;
  }

  public void addMovimento(Movimento movimento) {
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
  public void readFromFile(String fileName) throws IOException {
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
      // Divide la stringa nelle parti "amount|description|date"
      String[] parts = line.split("\\|");
      float amount = Float.parseFloat(parts[0]);
      String description = parts[1];
      LocalDateTime date = LocalDateTime.parse(parts[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      // Crea il movimento e lo aggiunge al conto corrente
      Movimento movimento = new Movimento(amount, description, date);
      addMovimento(movimento);
    }

    // Chiude il file
    reader.close();
  }

  // Metodo per scrivere i dati del conto corrente su file
  public void writeToFile(String fileName) throws IOException {
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
      writer.write("|");
      writer.write(movimento.getDescription());
      writer.write("|");
      writer.write(movimento.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
      writer.newLine();
    }

    // Chiude il file
    writer.close();
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

  public Movimento[] getMovimenti() {
    return movimenti;
  }

  public void setMovimenti(Movimento[] movimenti) {
    this.movimenti = movimenti;
  }

  public int getContatoreMovimenti() {
    return contatoreMovimenti;
  }

  public void setContatoreMovimenti(int contatoreMovimenti) {
    this.contatoreMovimenti = contatoreMovimenti;
  }

  public void setIntestatario(String intestatario) {
    this.intestatario = intestatario;
  }

  public String getIntestatario() {
    return this.intestatario;
  }

  public Movimento getMovimento(int i) {
    if(i < 0 || i >= this.contatoreMovimenti) {
      return null;
    }

    return this.movimenti[i];
  }
}




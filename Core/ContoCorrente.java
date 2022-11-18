package Core;

import java.util.stream.Stream;

public class ContoCorrente {
  private int numeroCC;
  private int saldoAttuale;
  private int[] movimenti;
  private int contatoreMovimenti;

  /**
   * @param numeroCC
   * @param saldoAttuale
   */
  public ContoCorrente(int numeroCC, int saldoAttuale) {
    this.numeroCC = numeroCC;
    this.saldoAttuale = saldoAttuale;
    this.movimenti = new int[5];
    this.contatoreMovimenti = 0;
  }

  /**
   * @return the saldoAttuale
   */
  public int getSaldoAttuale() {
    return saldoAttuale;
  }

  /**
   * @return the contatoreMovimenti
   */
  public int getContatoreMovimenti() {
    return contatoreMovimenti;
  }

  /**
   * @return the movimenti
   */
  public int[] getMovimenti() {
    return movimenti;
  }

  /**
   * @return the numeroCC
   */
  public int getNumeroCC() {
    return numeroCC;
  }

  /**
   * @param importo
   */
  private void registraMov(int importo) {
    if (movimenti.length == contatoreMovimenti) {
      int[] temp = new int[movimenti.length * 2];
      System.arraycopy(movimenti, 0, temp, 0, movimenti.length);
      this.movimenti = temp;
    }

    movimenti[contatoreMovimenti] = importo;
    contatoreMovimenti++;
  }

  /**
   * @param importo
   * @return
   */
  public boolean prelievo(int importo) {
    if (importo > this.saldoAttuale) {
      return false;
    }

    this.saldoAttuale -= importo;
    this.registraMov(-importo);
    return true;
  }

  /**
   * @param importo
   * @return
   */
  public boolean versamento(int importo, ContoCorrente cc2) {
    boolean prel = cc2.prelievo(importo);
    if (importo < 0 || !prel) {
      return false;
    }

    this.saldoAttuale += importo;
    this.registraMov(importo);

    return true;
  }

  /**
   *
   */
  public void printMov() {
    for (int i = 0; i < this.contatoreMovimenti; i++) {
      System.out.println(" " + i + " -> " + this.movimenti[i] + "["
          + (this.movimenti[i] > 0 ? "Versamento" : "Prelievo") + "]");
    }
  }

  /**
   *
   */
  public void print() {
    System.out.println("Numero CC: " + this.numeroCC);
    System.out.println("Saldo Attuale: " + this.saldoAttuale);
    System.out.println("Contatore Movimenti: " + this.contatoreMovimenti);
    System.out.println("Movimenti: ");
    this.printMov();
  }
}

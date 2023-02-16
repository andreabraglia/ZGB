package Core.Handlers;

import Core.ContoCorrente;
import Core.Movimento;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe che gestisce il salvataggio automatico del conto corrente
 */
public class AutoSaverHandler extends Thread {
  /**
   * Il conto corrente da salvare automaticamente
   */
  private final ContoCorrente contoCorrente;

  /**
   * La directory dove salvare il file di salvataggio automatico
   */
  private static final String directory = ".backup/";

  /**
   * Il nome del file di salvataggio automatico
   */
  private static final String fileName = "autoSave.tmp";

  /**
   * Il file di salvataggio automatico
   */
  private final File autoSaveFile = new File(directory + fileName);

  /**
   * Il timer che gestisce il salvataggio automatico
   */
  private final Timer timer;

  /**
   * Costruttore della classe AutoSaver
   *
   * @param contoCorrente il conto corrente da salvare automaticamente
   */
  public AutoSaverHandler(ContoCorrente contoCorrente) {
    this.contoCorrente = contoCorrente;

    int interval = 1000 * 60;

    this.timer = new Timer(interval, event -> this.run());
  }

  /**
   * Funzione che avvia il salvataggio automatico
   */
  public void start() {
    timer.start();
  }

  /**
   * Funzione che esegue il salvataggio automatico del conto corrente
   */
  @Override
  public void run() {
    System.out.println("\n[DEBUG] [" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(Movimento.DATE_FORMAT + ":ss")) + "] Salvataggio automatico in corso...");
    System.out.println("  [DEBUG] File: " + autoSaveFile.getAbsolutePath());

    try {
      Path path = Path.of(directory);
      if (!Files.isDirectory(path)) {
        Files.createDirectory(path);
      }


      FileOutputStream fileOS = new FileOutputStream(autoSaveFile);
      ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
      ArrayList<Movimento> movimentiList = contoCorrente.getMovimenti();

      Movimento[] movimenti = movimentiList.toArray(Movimento[]::new);

      objectOS.writeObject(contoCorrente.getIntestatario());
      objectOS.writeObject(contoCorrente.getNumeroCC());
      objectOS.writeObject(contoCorrente.getSaldoIniziale());
      objectOS.writeObject(movimenti);
    } catch (Exception error) {
      JOptionPane.showMessageDialog(null, "Errore durante il salvataggio automatico del conto corrente:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
      error.printStackTrace();
    }
  }

  /**
   * Funzione che restituisce il path del file di salvataggio automatico
   *
   * @return il path del file
   */
  public static String getAutoSaveFile() {
    return (directory + fileName);
  }

  /**
   * Funzione che legge il file di salvataggio automatico e carica il contenuto nel conto corrente
   */
  public void readAutoSaveFile() {
    try {
      FileInputStream fileIS = new FileInputStream(autoSaveFile);
      ObjectInputStream objectIS = new ObjectInputStream(fileIS);

      contoCorrente.setIntestatario((String) objectIS.readObject());
      contoCorrente.setNumeroCC((Integer) objectIS.readObject());
      contoCorrente.setSaldoIniziale((Float) objectIS.readObject());
      Movimento[] movimenti = (Movimento[]) objectIS.readObject();

      for (Movimento movimento : movimenti) {
        if(movimenti[0] == movimento) {
          continue;
        }
        
        contoCorrente.addMovimento(movimento);
      }

    } catch (Exception error) {
      JOptionPane.showMessageDialog(null, "Errore durante l'importazione del salvataggio automatico del conto corrente:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
      error.printStackTrace();
    }
  }

}

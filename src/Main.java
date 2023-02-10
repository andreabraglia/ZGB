import Core.AutoSaver;
import Core.ContoCorrente;

import java.nio.file.Files;
import java.nio.file.Path;

import GUI.*;

import javax.swing.*;


/**
 * Main class
 */
public class Main {

  /**
   * Metodo principale che viene eseguito all'avvio del programma
   * @param args argomenti iniziali
   */
  public static void main(String[] args) {
    // Crea un nuovo conto corrente
    ContoCorrente cc = new ContoCorrente();

    System.out.println("-----------------------");

    System.out.println("\n\n");
    System.out.println("-----------------------");

    // Init GUI
    MainFrame main = null;
    try {
//      if (Files.exists(Path.of(AutoSaver.getAutoSaveFile()))) {
//        cc.readFromTXTFile(AutoSaver.getAutoSaveFile());
//      }

      main = new MainFrame(cc);
      main.setVisible(true);
      AutoSaver autoSaver = new AutoSaver(cc);
//      autoSaver.start();
    } catch (Exception error) {
      JOptionPane.showMessageDialog(main, error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
      error.printStackTrace();
    }
  }
}

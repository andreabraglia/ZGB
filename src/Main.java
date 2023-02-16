import Core.Handlers.AutoSaverHandler;
import Core.ContoCorrente;

import GUI.*;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Main class
 */
public class Main {

  /**
   * Metodo principale che viene eseguito all'avvio del programma
   *
   * @param args argomenti iniziali
   */
  public static void main(String[] args) {
    // Crea un nuovo conto corrente
    ContoCorrente cc = new ContoCorrente();

    // Init GUI
    MainFrame main = null;
    try {
      AutoSaverHandler autoSaver = new AutoSaverHandler(cc);
      String autoSaveFile = AutoSaverHandler.getAutoSaveFile();
      if (Files.exists(Path.of(autoSaveFile))) {
        autoSaver.readAutoSaveFile();
      }

      main = new MainFrame(cc);
      main.setVisible(true);

      autoSaver.start();
    } catch (Exception error) {
      JOptionPane.showMessageDialog(main, error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
      error.printStackTrace();
    }
  }
}

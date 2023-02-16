package GUI.BasicComponents.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Classe che estende {@link JFileChooser} per permettere di salvare un file di testo o csv
 * con un nome specificato dall'utente.
 * Se l'estensione non viene specificata, viene aggiunta automaticamente.
 * Se il file esiste già, viene chiesto all'utente se sovrascriverlo.
 */
public class Exporter extends JFileChooser {

  /**
   * Estensione del file da salvare.
   */
  private String extension;

  /**
   * Costruttore di default.
   */
  public Exporter() {
    super();
    setFileFilter(new FileFilter() {
      @Override
      public boolean accept(File f) {
        return (
          f.isDirectory()
            && !f.getName().isEmpty()
        );
      }

      @Override

      public String getDescription() {
        return "Scegli un file di testo (*.txt) o un file csv (*.csv)";
      }
    });
  }

  /**
   * Sovrascrive il metodo {@link JFileChooser#approveSelection()} per aggiungere l'estensione
   * al file se non è stata specificata, e per chiedere all'utente se sovrascrivere il file
   * se esiste già.
   */
  @Override
  public void approveSelection() {
    File file = getSelectedFile();

    if (!file.getAbsolutePath().endsWith(this.extension)) {
      System.out.println("[DEBUG] L'estensione non è stata specificata, aggiungo .txt");
      System.out.println("[DEBUG] Il percorso del file è: " + file.getAbsolutePath());
      System.out.println("[DEBUG] Il nome del file è: " + file.getName() + " -> " + file.getAbsolutePath().endsWith(".txt"));


      file = new File(file.getAbsolutePath() + "." + this.extension);
    }

    setSelectedFile(file);

    if (file.exists()) {
      int result = JOptionPane.showConfirmDialog(this, "Il file esiste già, vuoi sovrascriverlo?", "Attenzione", JOptionPane.YES_NO_CANCEL_OPTION);
      switch (result) {
        case JOptionPane.YES_OPTION -> {
          super.approveSelection();
          return;
        }

        case JOptionPane.NO_OPTION, JOptionPane.CLOSED_OPTION -> {
          return;
        }

        case JOptionPane.CANCEL_OPTION -> {
          cancelSelection();
          return;
        }
      }
    }

    super.approveSelection();
  }

  /**
   * Imposta l'estensione del file da salvare.
   *
   * @param extension Estensione del file da salvare.
   */
  public void setExtension(String extension) {
    if (extension.isEmpty() || !extension.matches("txt|csv")) {
      throw new IllegalArgumentException("L'estensione deve essere 'txt' o 'csv'");
    }

    this.extension = extension;
  }
}

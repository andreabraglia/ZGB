package GUI.BasicComponents;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileSaverChooser extends JFileChooser {

  private String extension;

  public FileSaverChooser() {
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
        case JOptionPane.YES_OPTION: {
          super.approveSelection();
          return;
        }
        case JOptionPane.NO_OPTION:
        case JOptionPane.CLOSED_OPTION: {
          return;
        }
        case JOptionPane.CANCEL_OPTION: {
          cancelSelection();
          return;
        }
      }
    }

    super.approveSelection();
  }

  public void setExtension(String extension) {
    if (extension.isEmpty() || !extension.matches("txt|csv")) {
      throw new IllegalArgumentException("L'estensione deve essere 'txt' o 'csv'");
    }

    this.extension = extension;
  }
}

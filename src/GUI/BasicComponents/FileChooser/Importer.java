package GUI.BasicComponents.FileChooser;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe per la selezione di un file per caricare un conto corrente
 */
public class Importer {

  /**
   * File selezionato
   */
  private File selectedFile;

  /**
   * Costruttore
   *
   * @param extension estensione del file
   */
  public Importer(String extension) {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("File ." + extension, extension);
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
    }
  }

  /**
   * Restituisce il file selezionato
   *
   * @return the selectedFile
   */
  public String getSelectedFile() {
    return selectedFile.getAbsolutePath();
  }
}

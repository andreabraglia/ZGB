package GUI.BasicComponents;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileSaverChooser extends JFileChooser {

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

    if (!file.getName().endsWith(".txt") || !file.getName().endsWith(".csv")) {
      file = new File(file.getAbsolutePath() + ".txt");
    }

    setSelectedFile(file);
    super.approveSelection();
  }

  public void setExtension(String extension) {
    if (extension.isEmpty() || !extension.matches("txt|csv")) {
      throw new IllegalArgumentException("L'estensione deve essere 'txt' o 'csv'");
    }
  }
}

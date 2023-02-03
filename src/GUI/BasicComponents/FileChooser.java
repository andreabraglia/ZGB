package GUI.BasicComponents;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
  private File selectedFile;

  public FileChooser(String extension) {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("File ." + extension, extension);
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
    }
  }

  public String getSelectedFile() {
    return selectedFile.getAbsolutePath();
  }
}

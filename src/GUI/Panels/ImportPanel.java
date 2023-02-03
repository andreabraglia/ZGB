package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.FileChooser;
import GUI.BasicComponents.Panel;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import java.awt.*;

import static GUI.Styles.Dimensions.GAP;

public class ImportPanel extends CenteredPanel {
  public ImportPanel(ContoCorrente contoCorrente) {
    super(true);

    Panel mainPanel = new Panel(Colors.WHITE, true);

    JLabel titolo = new JLabel("Importa conto corrente da file");
    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );
    titolo.setFont(titleFont);

    JLabel pathLabel = new JLabel("Inserisci il percorso del file da importare");

    JButton importButtonCSV = new JButton("Importa come CSV");
    importButtonCSV.addActionListener(e -> importHandler(mainPanel, "csv", contoCorrente));

    JButton importButtonTXT = new JButton("Importa come TXT");
    importButtonTXT.addActionListener(e -> importHandler(mainPanel, "txt", contoCorrente));

    Panel buttonPanel = new Panel(Colors.WHITE, true);
    buttonPanel.add(importButtonCSV);
    buttonPanel.add(importButtonTXT);

    mainPanel.add(titolo);
    mainPanel.add(Box.createRigidArea(new Dimension(0, GAP.getDimension())));

    mainPanel.add(pathLabel);
    mainPanel.add(new JSeparator(JSeparator.HORIZONTAL));
    mainPanel.add(buttonPanel);

    add(mainPanel);
  }

  private void importHandler(Panel mainPanel, String extension, ContoCorrente contoCorrente) {
    FileChooser fileChooser = new FileChooser(extension);

    try {
      if (extension.equals("csv")) {
        contoCorrente.readFromCSVFile(fileChooser.getSelectedFile());
      } else if (extension.equals("txt")) {
        contoCorrente.readFromTXTFile(fileChooser.getSelectedFile());
      }

      contoCorrente.print();
      JOptionPane.showMessageDialog(mainPanel, "Caricamento del file '" + fileChooser.getSelectedFile() + "' avvenuto con successo", "Info", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception error) {
      JOptionPane.showMessageDialog(mainPanel, "Errore durante la lettura dei dati da file:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
      error.printStackTrace();
    }
  }
}
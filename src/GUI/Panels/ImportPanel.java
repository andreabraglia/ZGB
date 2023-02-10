package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.FileChooser;
import GUI.BasicComponents.Panel;
import GUI.Enums.Colors;
import GUI.Enums.Dimensions;

import javax.swing.*;
import java.awt.*;

import static GUI.Enums.Dimensions.GAP;
import static GUI.Enums.UtilsComponents.DIVIDER;
import static GUI.Enums.UtilsComponents.SPACER;

/**
 * Pannello per l'importazione di un conto corrente da file
 */
public class ImportPanel extends CenteredPanel {

  /**
   * Costruttore del pannello che lo inizializza,
   * aggiunge i componenti al suo interno, definendone anche la logica
   * @param contoCorrente Il conto corrente
   */
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
    mainPanel.add(SPACER.getComponent());

    mainPanel.add(pathLabel);
    mainPanel.add(DIVIDER.getComponent());
    mainPanel.add(buttonPanel);

    add(mainPanel);
  }

  /**
   * Gestisce l'importazione di un file CSV o TXT
   * @param mainPanel Il pannello principale
   * @param extension L'estensione del file da importare
   * @param contoCorrente Il conto corrente
   */
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
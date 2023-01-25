package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.FileChooser;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static GUI.Styles.Dimensions.GAP;

public class ExportPanel extends CenteredPanel  {
  public ExportPanel(ContoCorrente contoCorrente) {
    super(true);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(new Color(Colors.WHITE.getHex()));

    JLabel titolo = new JLabel("Esporta conto corrente su file");
    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );
    titolo.setFont(titleFont);

    mainPanel.add(titolo);
    mainPanel.add(Box.createRigidArea(new Dimension(0, GAP.getDimension())));

    JLabel pathLabel = new JLabel("Inserisci il percorso del file da importare");
    JButton importButton = new JButton("Esporta");
    importButton.addActionListener(
      event -> {
        FileChooser fileChooser = new FileChooser();
        try {
          contoCorrente.writeToTXTFile(fileChooser.getSelectedFile());
        } catch (IOException error) {
          JOptionPane.showMessageDialog(mainPanel, "Errore durante la lettura dei dati da file:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
          error.printStackTrace();
        }
      }
    );

    mainPanel.add(pathLabel);
    mainPanel.add(importButton);

    add(mainPanel);
  }
}
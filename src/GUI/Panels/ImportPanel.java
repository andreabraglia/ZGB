package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.FileChooser;
import GUI.BasicComponents.Panel;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

    mainPanel.add(titolo);
    mainPanel.add(Box.createRigidArea(new Dimension(0, GAP.getDimension())));

    JLabel pathLabel = new JLabel("Inserisci il percorso del file da importare");


    JButton importButtonCSV = new JButton("Importa come CSV");
    importButtonCSV.addActionListener(e -> {
        FileChooser fileChooser = new FileChooser();

        try {
          contoCorrente.readFromCSVFile(fileChooser.getSelectedFile());
          contoCorrente.print();
        } catch (IOException error) {
          JOptionPane.showMessageDialog(mainPanel, "Errore durante la lettura dei dati da file:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
          error.printStackTrace();
        }
      }
    );

    JButton importButtonTXT = new JButton("Importa come TXT");
    importButtonTXT.addActionListener(e -> {
        FileChooser fileChooser = new FileChooser();
        try {
          contoCorrente.readFromTXTFile(fileChooser.getSelectedFile());
          contoCorrente.print();
        } catch (IOException error) {
          JOptionPane.showMessageDialog(mainPanel, "Errore durante la lettura dei dati da file:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
          error.printStackTrace();
        }
      }
    );

    mainPanel.add(pathLabel);

    Panel buttonPanel = new Panel(Colors.WHITE, true);
    buttonPanel.add(importButtonCSV);
    buttonPanel.add(importButtonTXT);

    mainPanel.add(buttonPanel);
    add(mainPanel);
  }
}
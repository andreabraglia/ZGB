package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.FileSaverChooser;
import GUI.BasicComponents.Panel;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static GUI.Styles.Dimensions.GAP;

public class ExportPanel extends CenteredPanel {
  public ExportPanel(ContoCorrente contoCorrente) {
    super(true);

    Panel mainPanel = new Panel(Colors.WHITE, true);

    JLabel titolo = new JLabel("Esporta conto corrente su file");
    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );
    titolo.setFont(titleFont);

    mainPanel.add(titolo);
    mainPanel.add(Box.createRigidArea(new Dimension(0, GAP.getDimension())));

    JLabel pathLabel = new JLabel("Inserisci il percorso del file da importare");
    JButton exportButtonTXT = new JButton("Esporta come TXT");
    exportButtonTXT.addActionListener(
      event -> {
        FileSaverChooser fileChooser = new FileSaverChooser();
        fileChooser.setExtension("txt");

        if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          System.out.println("[DEBUG] Il luogo scelto è: " + file.getAbsolutePath());

          try {
            contoCorrente.writeToTXTFile(file.getAbsolutePath());
          } catch (IOException error) {
            JOptionPane.showMessageDialog(mainPanel, "Errore durante la scrittura dei dati su file:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
          }

        }
      }
    );

    JButton exportButtonCSV = new JButton("Esporta come CSV");
    exportButtonCSV.addActionListener(
      event -> {
        FileSaverChooser fileChooser = new FileSaverChooser();
        fileChooser.setExtension("csv");

        if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          System.out.println("[DEBUG] Il luogo scelto è: " + file.getAbsolutePath());

          try {
            contoCorrente.writeToCSVFile(file.getAbsolutePath());
          } catch (IOException error) {
            JOptionPane.showMessageDialog(mainPanel, "Errore durante la scrittura dei dati su file:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
          }

        }
      }
    );

    Panel buttonPanel = new Panel(Colors.WHITE, true);
    buttonPanel.add(exportButtonCSV);
    buttonPanel.add(exportButtonTXT);

    mainPanel.add(pathLabel);
    mainPanel.add(buttonPanel);
    add(mainPanel);
  }
}
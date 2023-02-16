package GUI.Panels;

import Core.ContoCorrente;
import Core.Handlers.PrinterHandler;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.FileChooser.Exporter;
import GUI.BasicComponents.Panel;
import GUI.Enums.Colors;
import GUI.Enums.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import static GUI.Enums.Dimensions.GAP;

/**
 * Panello che estende {@link CenteredPanel} e serve
 * per l'esportazione su file e la stampa del conto corrente,
 *
 * @see CenteredPanel
 */
public class ExportPanel extends CenteredPanel {

  /**
   * Costruttore del pannello che lo inizializza,
   * aggiunge i componenti al suo interno, definendone anche la logica
   *
   * @param contoCorrente Riferimento del conto corrente da esportare
   */
  public ExportPanel(ContoCorrente contoCorrente) {
    super(true);

    Panel mainPanel = new Panel(Colors.WHITE, true);

    JLabel titolo = new JLabel("Esporta conto corrente su file");
    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );
    titolo.setFont(titleFont);

    JLabel pathLabel = new JLabel("Clicca uno dei tasti per esportare il conto corrente su di un file");
    JButton exportButtonTXT = new JButton("Esporta come TXT");
    exportButtonTXT.addActionListener(event -> exportHandler(mainPanel, "txt", contoCorrente));

    JButton exportButtonCSV = new JButton("Esporta come CSV");
    exportButtonCSV.addActionListener(event -> exportHandler(mainPanel, "csv", contoCorrente));

    JButton printButton = new JButton("Stampa");
    printButton.addActionListener(event -> {
      try {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new PrinterHandler(contoCorrente));
        try {
          job.print();
        } catch (PrinterException e) {
          e.printStackTrace();
        }

      } catch (Exception error) {
        JOptionPane.showMessageDialog(mainPanel, "Errore durante la stampa del conto corrente:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
      }
    });

    Panel buttonContainer = new Panel(Colors.WHITE, true);
    buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
    buttonContainer.add(exportButtonCSV);
    buttonContainer.add(exportButtonTXT);
    buttonContainer.add(printButton);

    mainPanel.add(titolo);
    mainPanel.add(Box.createRigidArea(new Dimension(0, GAP.getDimension())));

    mainPanel.add(pathLabel);
    mainPanel.add(new JSeparator(JSeparator.HORIZONTAL));
    mainPanel.add(buttonContainer);

    add(mainPanel);
  }

  /**
   * Metodo che gestisce l'esportazione del conto corrente su file
   *
   * @param mainPanel     Pannello principale
   * @param extension     Estensione del file da esportare
   * @param contoCorrente Conto corrente da esportare
   */
  private void exportHandler(Panel mainPanel, String extension, ContoCorrente contoCorrente) {
    Exporter fileChooser = new Exporter();
    fileChooser.setExtension(extension);

    if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      System.out.println("[DEBUG] Il luogo scelto è: " + file.getAbsolutePath());

      try {
        if (extension.equals("txt")) {
          contoCorrente.writeToTXTFile(file.getAbsolutePath());
        } else {
          contoCorrente.writeToCSVFile(file.getAbsolutePath());
        }

        JOptionPane.showMessageDialog(mainPanel, "Esportazione del file '" + file.getAbsolutePath() + "' avvenuta con successo", "Info", JOptionPane.INFORMATION_MESSAGE);
      } catch (Exception error) {
        JOptionPane.showMessageDialog(mainPanel, "Errore durante la scrittura dei dati su file:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        error.printStackTrace();
      }
    }

  }
}
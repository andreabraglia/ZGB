package GUI.Panels;

import Core.ContoCorrente;
import Core.PrinterHandler;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.FileSaverChooser;
import GUI.BasicComponents.Panel;
import GUI.BasicComponents.Table.MovimentiTable;
import GUI.BasicComponents.Table.MovimentiTableModel;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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

    JLabel pathLabel = new JLabel("Inserisci il percorso del file da importare");
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

  private void exportHandler(Panel mainPanel, String extension, ContoCorrente contoCorrente) {
    FileSaverChooser fileChooser = new FileSaverChooser();
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
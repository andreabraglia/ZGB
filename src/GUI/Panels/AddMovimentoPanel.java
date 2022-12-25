package GUI.Panels;

import Core.ContoCorrente;
import Core.Movimento;
import GUI.BasicComponents.Button;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.DatePicker;
import GUI.Styles.Colors;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;

public class AddMovimentoPanel extends CenteredPanel {
  private JTextField importoField;
  private JTextField descrizioneField;

  private DatePicker datePicker;
  public AddMovimentoPanel(ContoCorrente contoCorrente) {
    // Imposta il layout del pannello
    super(true);

    // Crea un nuovo pannello per i campi di input
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(new Color(Colors.WHITE.getHex()));

    // Aggiunge la label "Importo"
    JLabel importoLabel = new JLabel("Importo:");
    importoField = new JTextField(10);
    mainPanel.add(importoLabel);
    mainPanel.add(importoField);


    // Aggiunge il campo per la descrizione
    JLabel descrizioneLabel = new JLabel("Descrizione:");
    descrizioneField = new JTextField(20);
    mainPanel.add(descrizioneLabel);
    mainPanel.add(descrizioneField);

    // Aggiunge il campo oer la data
    JLabel dateLabel = new JLabel("Data:");
    datePicker = new DatePicker();
    mainPanel.add(dateLabel);
    mainPanel.add(datePicker);

    // Aggiunge il bottone "Aggiungi movimento"
    JButton aggiungiButton = new JButton("Aggiungi movimento");
    aggiungiButton.addActionListener(e -> {
      String importoStringa = importoField.getText();
      String descrizione = descrizioneField.getText();
      LocalDateTime data = LocalDateTime.now();

      Movimento movimento = new Movimento(Float.parseFloat(importoStringa), descrizione, data);

      contoCorrente.addMovimento(movimento);

      importoField.setText("");
      descrizioneField.setText("");
    });

    mainPanel.add(aggiungiButton);

    add(mainPanel);
  }
}

package GUI.Panels;

import Core.ContoCorrente;
import Core.Movimento;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.DatePicker;
import GUI.Styles.Colors;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO:
//  - Aggiungi lo spacing giusto
//  - Allinea i componenti in modo corretto

public class AddMovimentoPanel extends CenteredPanel {
  private final JTextField importoField;

  private final JTextField descrizioneField;

  private final DatePicker datePicker;

  public AddMovimentoPanel(ContoCorrente contoCorrente, JTable table, JFrame parent) {
    // Imposta il layout del pannello
    super(false);

    // Crea un nuovo pannello per i campi di input
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(new Color(Colors.WHITE.getHex()));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Aggiunge la label "Importo"
    JLabel importoLabel = new JLabel("Importo:");
    importoField = new JTextField(10);
    ((AbstractDocument) importoField.getDocument()).setDocumentFilter(new DocumentFilter() {
      final Pattern regEx = Pattern.compile("\\d*");

      @Override
      public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        Matcher matcher = regEx.matcher(text);
        if (!matcher.matches()) {
          return;
        }

        super.replace(fb, offset, length, text, attrs);
      }
    });

    importoLabel.setAlignmentX(LEFT_ALIGNMENT);
    mainPanel.add(importoLabel);
    mainPanel.add(importoField);


    // Aggiunge il campo per la descrizione
    JLabel descrizioneLabel = new JLabel("Descrizione:");
    descrizioneField = new JTextField(20);
    descrizioneLabel.setAlignmentX(LEFT_ALIGNMENT);
    descrizioneLabel.setBackground(new Color(Colors.DANGER.getHex()));
    mainPanel.add(descrizioneLabel);
    mainPanel.add(descrizioneField);

    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    // Aggiunge il campo oer la data
    datePicker = new DatePicker(Colors.WHITE);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(datePicker);

    // Aggiunge il bottone "Aggiungi movimento"
    JButton aggiungiButton = new JButton("Aggiungi movimento");
    aggiungiButton.addActionListener(e -> {
      String importoStringa = importoField.getText();
      String descrizione = descrizioneField.getText();
      LocalDateTime data = datePicker.getSelectedDateTime();

      if (importoStringa.equals("") || descrizione.equals("")) {
        JOptionPane.showMessageDialog(this, "Compila tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
        return;
      }

      Movimento movimento = new Movimento(Float.parseFloat(importoStringa), descrizione, data);

      contoCorrente.addMovimento(movimento);

      importoField.setText("");
      descrizioneField.setText("");
      table.updateUI();
      setVisible(false);
      parent.setVisible(false);
    });

    mainPanel.add(aggiungiButton);
    add(mainPanel);
  }
}

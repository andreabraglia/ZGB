package GUI.Panels;

import Core.ContoCorrente;
import Core.Movimento;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.DatePicker;
import GUI.BasicComponents.Panel;
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
import static GUI.Styles.Dimensions.GAP;

public class AddMovimentoPanel extends CenteredPanel {
  private final JTextField importoField;

  private final JTextField descrizioneField;

  private final DatePicker datePicker;

  public AddMovimentoPanel(ContoCorrente contoCorrente, JTable table, JFrame parent) {
    // Imposta il layout del pannello
    super(false);

    // Crea un nuovo pannello per i campi di input
    Panel mainPanel = new Panel(Colors.WHITE, true);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    Component gap = Box.createRigidArea(new Dimension(0, GAP.getDimension() * 2));

    // Aggiunge la label "Importo"
    Panel importoPanel = new Panel(Colors.WHITE);
    importoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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

    importoPanel.add(importoLabel);
    mainPanel.add(importoPanel);
    mainPanel.add(importoField);


    // Aggiunge il campo per la descrizione
    Panel descrizionePanel = new Panel(Colors.WHITE);
    descrizionePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel descrizioneLabel = new JLabel("Descrizione:");
    descrizioneField = new JTextField(20);

    descrizionePanel.add(descrizioneLabel);
    mainPanel.add(descrizionePanel);
    mainPanel.add(descrizioneField);

    mainPanel.add(gap);

    // Aggiunge il campo oer la data
    datePicker = new DatePicker(Colors.WHITE);
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

    mainPanel.add(gap);
    mainPanel.add(aggiungiButton);
    add(mainPanel);
  }
}

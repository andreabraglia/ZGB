package GUI.Panels;

import Core.ContoCorrente;
import Core.Movimento;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.DatePicker;
import GUI.BasicComponents.Panel;
import GUI.Enums.Colors;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static GUI.Enums.UtilsComponents.SPACER;

/**
 * Pannello per l'aggiunta di un movimento, estende {@link CenteredPanel}
 * e contiene i campi per l'inserimento dei dati del movimento
 *
 * @see CenteredPanel
 */
public class AddMovimentoPanel extends CenteredPanel {

  /**
   * Campo per inserire l'importo del movimento
   *
   * @see JTextField
   */
  private final JTextField importoField;

  /**
   * Campo per inserire la descrizione del movimento
   *
   * @see JTextField
   */
  private final JTextField descrizioneField;

  /**
   * Campo per inserire la data del movimento
   *
   * @see DatePicker
   */
  private final DatePicker datePicker;

  /**
   * Costruttore del pannello che lo inizializza,
   * aggiunge i componenti al suo interno, definendone anche la logica
   *
   * @param contoCorrente Riferimento del conto corrente a cui aggiungere il movimento
   * @param parent        Riferimento del finestra utilizzata per la visualizzazione del pannello
   * @param callback      Funzione da eseguire al termine dell'aggiunta del movimento
   */
  public AddMovimentoPanel(ContoCorrente contoCorrente, JFrame parent, Callable<Void> callback) {
    // Imposta il layout del pannello
    super(false);

    // Crea un nuovo pannello per i campi di input
    Panel mainPanel = new Panel(Colors.WHITE, true);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    Component gap = SPACER.getComponent();

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

    JComboBox<String> tipoImporto = new JComboBox<>(new String[]{ "Entrata", "Uscita" });
    importoPanel.add(tipoImporto);
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
      String importoStringa = (Objects.equals(tipoImporto.getSelectedItem(), "Uscita") ? "-" : "") + importoField.getText();
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
      contoCorrente.print();
      setVisible(false);
      try {
        callback.call();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }

      parent.setVisible(false);
    });

    mainPanel.add(gap);
    mainPanel.add(aggiungiButton);
    add(mainPanel);
  }
}

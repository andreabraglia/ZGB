package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Panel;
import GUI.Enums.Colors;
import GUI.Enums.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;


/**
 * Pannello per la creazione di un nuovo conto corrente, estende {@link CenteredPanel}
 * e contiene i campi per l'inserimento dei dati del conto corrente
 */
public class CreationAccountPanel extends CenteredPanel {

  /**
   * Campo per inserire il numero del conto corrente
   *
   * @see JTextField
   */
  private final JTextField numeroCCField;

  /**
   * Campo per inserire il saldo iniziale del conto corrente
   *
   * @see JTextField
   */
  private final JTextField saldoAttualeField;

  /**
   * Campo per inserire l'intestatario del conto corrente
   *
   * @see JTextField
   */
  private final JTextField intestatarioField;

  /**
   * Costruttore del pannello che lo inizializza,
   * aggiunge i componenti al suo interno, definendone anche la logica
   *
   * @param contoCorrente Riferimento del conto corrente da creare
   */
  public CreationAccountPanel(ContoCorrente contoCorrente, Callable<Void> navigateTo) {
    super(true);

    // Crea un nuovo pannello per i campi di input
    Panel mainPanel = new Panel(Colors.WHITE, true);

    JButton createButton = new JButton("Crea conto corrente");
    JLabel titolo = new JLabel("Crea un nuovo conto corrente");
    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );
    titolo.setFont(titleFont);
    mainPanel.add(titolo);

    // Aggiunge i campi di input per le proprietà del conto corrente
    mainPanel.add(new JLabel("Numero conto corrente:"));
    numeroCCField = new JTextField(contoCorrente.getNumeroCC() + "");
    mainPanel.add(numeroCCField);

    mainPanel.add(new JLabel("Saldo iniziale:"));
    saldoAttualeField = new JTextField(String.valueOf(contoCorrente.getSaldoIniziale()));
    mainPanel.add(saldoAttualeField);

    mainPanel.add(new JLabel("Intestatario:"));
    intestatarioField = new JTextField(contoCorrente.getIntestatario());
    mainPanel.add(intestatarioField);

    mainPanel.add(createButton);

    // Aggiunge il pannello con i campi di input alla finestra
    add(mainPanel);

    // Crea il listener del bottone per creare il conto corrente
    createButton.addActionListener(e -> {
      // Legge i valori inseriti nei campi di input
      int numeroCC = Integer.parseInt(numeroCCField.getText());
      float saldoIniziale = Float.parseFloat(saldoAttualeField.getText());
      String intestatario = intestatarioField.getText();
      if (numeroCC == contoCorrente.getNumeroCC() || intestatario.equals(contoCorrente.getIntestatario())) {
        JOptionPane.showMessageDialog(
          this,
          "Inseirsci correttamente i dati!",
          "Errore",
          JOptionPane.ERROR_MESSAGE
        );
        return;
      }
      contoCorrente.setNumeroCC(numeroCC);
      contoCorrente.setSaldoIniziale(saldoIniziale);
      contoCorrente.setIntestatario(intestatario);

      // Crea il conto corrente utilizzando i valori inseriti nei campi di input
      System.out.println("[DEBUG] Conto corrente creato con successo!");
      contoCorrente.print();

      JOptionPane.showMessageDialog(
        this,
        "Conto corrente creato con successo!",
        "Successo",
        JOptionPane.INFORMATION_MESSAGE
      );

      try {
        navigateTo.call();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    });
  }
}



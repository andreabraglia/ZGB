package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CreationAccountPanel extends CenteredPanel {
  // Campi di input per le proprietà del conto corrente
  private final JTextField numeroCCField;

  private final JTextField saldoAttualeField;

  private final JTextField intestatarioField;


  public CreationAccountPanel(ContoCorrente contoCorrente) {
    super(true);

    // Crea un nuovo pannello per i campi di input
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(Colors.WHITE.getHex()));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

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
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Legge i valori inseriti nei campi di input
        contoCorrente.setNumeroCC(Integer.parseInt(numeroCCField.getText()));
        contoCorrente.setSaldoIniziale(Float.parseFloat(saldoAttualeField.getText()));
        contoCorrente.setIntestatario(intestatarioField.getText());

        // Crea il conto corrente utilizzando i valori inseriti nei campi di input
        System.out.println("Conto corrente creato con successo!");
        System.out.println("Numero conto corrente: " + contoCorrente.getNumeroCC());
        System.out.println("Saldo attuale: " + contoCorrente.getSaldoIniziale());
        System.out.println("Intestatario: " + contoCorrente.getIntestatario());
      }
    });
  }
}



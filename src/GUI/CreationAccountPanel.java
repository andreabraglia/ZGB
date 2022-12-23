package GUI;


//import javax.swing.JPanel;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class CreationAccountPanel extends CenteredPanel {
  // Proprietà del conto corrente
  private int numeroCC;
  private double saldoAttuale;
  private String intestatario;

  // Campi di input per le proprietà del conto corrente
  private JTextField numeroCCField;
  private JTextField saldoAttualeField;
  private JTextField intestatarioField;

  public CreationAccountPanel() {
    // Crea un nuovo pannello per i campi di input
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(new LineBorder(Color.BLUE, 3));
    JButton createButton = new JButton("Crea conto corrente");

    // Aggiunge i campi di input per le proprietà del conto corrente
    mainPanel.add(new JLabel("Numero conto corrente:"));

    numeroCCField = new JTextField();
    mainPanel.add(numeroCCField);
    mainPanel.add(new JLabel("Saldo attuale:"));

    saldoAttualeField = new JTextField();
    mainPanel.add(saldoAttualeField);
    mainPanel.add(new JLabel("Intestatario:"));

    intestatarioField = new JTextField();
    mainPanel.add(intestatarioField);

    mainPanel.add(createButton);

    // Aggiunge il pannello con i campi di input alla finestra
    add(mainPanel);

    // Crea il listener del bottone per creare il conto corrente
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Legge i valori inseriti nei campi di input
        numeroCC = Integer.parseInt(numeroCCField.getText());
        saldoAttuale = Double.parseDouble(saldoAttualeField.getText());
        intestatario = intestatarioField.getText();

        // Crea il conto corrente utilizzando i valori inseriti nei campi di input
        System.out.println("Conto corrente creato con successo!");
        System.out.println("Numero conto corrente: " + numeroCC);
        System.out.println("Saldo attuale: " + saldoAttuale);
        System.out.println("Intestatario: " + intestatario);
      }
    });
  }
}



package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends Frame {
  public MainFrame() {
    super("Home", 500, 500, 300, 100);
    // Imposta il layout del pannello utilizzando BorderLayout
    setLayout(new BorderLayout());

    // Crea una navbar
    Navbar navbar = new Navbar();

    // Crea una tabella
    String[] columnNames = {"Nome", "Cognome", "Età"};
    Object[][] data = {
        {"Mario", "Rossi", 30},
        {"Anna", "Bianchi", 25},
        {"Luca", "Verdi", 35}
    };

    JTable table = new JTable(data, columnNames);

    // Aggiunge la navbar al primo riquadro del pannello
    add(navbar, BorderLayout.WEST);

    // Aggiunge la tabella al riquadro sinistro del pannello
    // utilizzando un JScrollPane per gestire la visualizzazione delle righe
    if(1 == 1) {
      CreationAccountPanel createAccountPanel = new CreationAccountPanel();
//      JScrollPane scrollPane = new JScrollPane(createAccountPanel);
      add(createAccountPanel, BorderLayout.CENTER);
    } else {
      JScrollPane scrollPane = new JScrollPane(table);
      add(scrollPane, BorderLayout.CENTER);
    }
  }
}
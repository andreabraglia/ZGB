package GUI;

import Core.*;
import GUI.BasicComponents.Frame;
import GUI.Panels.MovimentiPanel;
import GUI.Panels.NavbarPanel;
import GUI.Styles.Colors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;

public class MainFrame extends Frame {
  public MainFrame() {
    super("Home", 500, 500, 300, 100);
    // Imposta il layout del pannello utilizzando BorderLayout
    setLayout(new BorderLayout());


    // Dati fake
    ContoCorrente cc = new ContoCorrente(123456, 1000.0f, "Mario Rossi");
    cc.addMovimento(new Movimento(100.0f, "Pagamento bolletta", LocalDateTime.now()));
    cc.addMovimento(new Movimento(-50.0f, "Prelevamento bancomat", LocalDateTime.now()));


    int paddingValue = 5;
    Border padding = BorderFactory.createEmptyBorder(paddingValue, paddingValue, paddingValue, paddingValue);
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBorder(padding);
    mainPanel.setBackground(new Color(Colors.LIGHT.getHex()));
    add(mainPanel);
    MovimentiPanel movimentiPanel = new MovimentiPanel(cc);



    // Crea una navbar
    NavbarPanel navbar = new NavbarPanel(mainPanel, movimentiPanel, cc);


    // Dati tabella


    // Aggiunge la navbar al primo riquadro del pannello
    mainPanel.add(navbar, BorderLayout.WEST);

    // Aggiunge la tabella al riquadro sinistro del pannello
    // utilizzando un JScrollPane per gestire la visualizzazione delle righe
//    if(1 == 2) {
//      CreationAccountPanel createAccountPanel = new CreationAccountPanel();
//      mainPanel.add(createAccountPanel, BorderLayout.CENTER);
//    } else {
//      mainPanel.add(movimentiPanel, BorderLayout.CENTER);
//    }
//    setContentPane(movimentiPanel);
  }
}
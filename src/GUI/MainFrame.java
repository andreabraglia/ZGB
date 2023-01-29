package GUI;

import Core.*;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Frame;
import GUI.BasicComponents.Panel;
import GUI.Panels.CreationAccountPanel;
import GUI.Panels.MovimentiPanel;
import GUI.Panels.NavbarPanel;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;


public class MainFrame extends Frame {
  public MainFrame(ContoCorrente cc) throws ParseException {
    super("Home", 500, 500, 300, 100);

    // Imposta il layout del pannello
    setLayout(new BorderLayout());

    Border padding = BorderFactory.createEmptyBorder(Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension());

    Panel mainPanel = new Panel(Colors.LIGHT, new BorderLayout());
    mainPanel.setBorder(padding);
    add(mainPanel);

    // Controlla se il conto corrente è vuoto
    // e nel caso crea un nuovo pannello per la creazione di un nuovo conto
    CenteredPanel contentPanel;
    if (!cc.isEmpty()) {
      contentPanel = new MovimentiPanel(cc);
    } else {
      contentPanel = new CreationAccountPanel(cc);
    }

    mainPanel.add(contentPanel, BorderLayout.CENTER);

    // Crea una navbar
    NavbarPanel navbar = new NavbarPanel(mainPanel, contentPanel, cc);

    // Aggiunge la navbar al primo riquadro del pannello
    mainPanel.add(navbar, BorderLayout.WEST);
  }
}
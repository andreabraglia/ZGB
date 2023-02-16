package GUI;

import Core.*;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Frame;
import GUI.BasicComponents.Panel;
import GUI.Panels.CreationAccountPanel;
import GUI.Panels.MovimentiPanel;
import GUI.Panels.NavbarPanel;
import GUI.Enums.Colors;
import GUI.Enums.Dimensions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Classe che crea la finestra principale della GUI
 */
public class MainFrame extends Frame {

  /**
   * Riferimento alla navbar {@link NavbarPanel}
   */
  NavbarPanel navbar;

  /**
   * Costruttore della classe che inzializza la finestra principale
   *
   * @param cc Conto corrente
   *
   */
  public MainFrame(ContoCorrente cc)  {
    super("Home", 500, 500, 300, 100);

    // Imposta il layout del pannello
    setLayout(new BorderLayout());

    Border padding = BorderFactory.createEmptyBorder(Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension());

    Panel mainPanel = new Panel(Colors.LIGHT, new BorderLayout());
    mainPanel.setBorder(padding);
    add(mainPanel);

    // Controlla se il conto corrente è vuoto
    // e nel caso mostra un pannello per la creazione di un nuovo conto
    CenteredPanel contentPanel;
    if (!cc.isEmpty()) {
      contentPanel = new MovimentiPanel(cc);
    } else {
      contentPanel = new CreationAccountPanel(cc, () -> {
        navbar.navigateTo("Home");
        return null;
      });
    }

    mainPanel.add(contentPanel, BorderLayout.CENTER);

    // Crea una navbar
    NavbarPanel navbar = new NavbarPanel(mainPanel, contentPanel, cc);
    this.navbar = navbar;
    // Aggiunge la navbar al primo riquadro del pannello
    mainPanel.add(navbar, BorderLayout.WEST);
  }
}
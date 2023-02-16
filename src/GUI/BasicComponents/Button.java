package GUI.BasicComponents;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 * Classe che estende JButton per creare un bottone personalizzato
 */
public class Button extends JButton {

  /**
   * Costruttore del bottone
   *
   * @param text testo del bottone
   */
  public Button(String text) {
    super(text);

    // Imposta il colore di sfondo del bottone
    setBackground(Color.LIGHT_GRAY);

    // Imposta il colore del testo del bottone
    setForeground(Color.BLACK);

    // Imposta il bordo del bottone
    setBorderPainted(false);

    // Imposta il tipo di carattere del testo del bottone
    setFont(new Font("Arial", Font.BOLD, 16));

    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    MouseListener mouseListener = new MouseAdapter() {
      /**
       * Handler per quando il mouse entra nel bottone, il testo del bottone viene sottolineato
       *
       * @param evt evento del mouse
       */
      @Override
      public void mouseEntered(MouseEvent evt) {
        Component button = evt.getComponent();
        Font font = button.getFont();
        Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        button.setFont(font.deriveFont(attributes));
      }

      /**
       * Handler per quando il mouse esce dal bottone, il testo del bottone non viene più sottolineato
       *
       * @param evt evento del mouse
       */
      @Override
      public void mouseExited(MouseEvent evt) {
        Component button = evt.getComponent();
        Font font = button.getFont();
        Map<TextAttribute, ?> attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, null);
        button.setFont(font.deriveFont(attributes));
      }
    };

    addMouseListener(mouseListener);
  }
}

package GUI.BasicComponents;

import GUI.Enums.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che definisce un panel custom
 */
public class Panel extends JPanel {

  /**
   * Costruttore di default
   *
   * @param color colore del panel
   */
  public Panel(Colors color) {
    setBackground(new Color(color.getHex()));
  }

  /**
   * Costruttore con layout
   *
   * @param color  colore del panel
   * @param layout layout del panel
   */
  public Panel(Colors color, LayoutManager layout) {
    this(color);
    setLayout(layout);
  }

  /**
   * Costruttore con layout e bordo
   *
   * @param color      colore del panel
   * @param isCentered booleano che indica se il panel è centrato verticalmente
   */
  public Panel(Colors color, boolean isCentered) {
    this(color);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    if (isCentered) {
      add(Box.createVerticalGlue());
    }
  }
}

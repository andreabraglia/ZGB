package GUI.Enums;

import javax.swing.*;
import java.awt.*;

import static GUI.Enums.Dimensions.GAP;

/**
 * Enum che definisce i componenti utili nella GUI
 */
public enum UtilsComponents {
  /**
   * Spazio tra i componenti
   */
  SPACER(Box.createRigidArea(new Dimension(0, GAP.getDimension()))),

  /**
   * Linea divisoria tra i componenti
   */
  DIVIDER(new JSeparator(JSeparator.HORIZONTAL));

  /**
   * Componente
   */
  private final Component component;

  /**
   * Costruttore dell'enum
   *
   * @param component Componente da aggiungere
   */
  UtilsComponents(Component component) {
    this.component = component;
  }

  /**
   * Restituisce il componente
   *
   * @return Componente
   */
  public Component getComponent() {
    return component;
  }
}

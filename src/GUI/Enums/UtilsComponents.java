package GUI.Enums;

import javax.swing.*;
import java.awt.*;

import static GUI.Enums.Dimensions.GAP;

public enum UtilsComponents {
  SPACER(Box.createRigidArea(new Dimension(0, GAP.getDimension()))),
  DIVIDER(new JSeparator(JSeparator.HORIZONTAL));

  private final Component component;

  UtilsComponents(Component component) {
    this.component = component;
  }

  public Component getComponent() {
    return component;
  }
}

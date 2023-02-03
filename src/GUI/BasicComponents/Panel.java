package GUI.BasicComponents;

import GUI.Styles.Colors;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

  public Panel(Colors color) {
    setBackground(new Color(color.getHex()));
  }

  public Panel(Colors color, LayoutManager layout) {
    this(color);
    setLayout(layout);
  }

  public Panel(Colors color, boolean isCentered) {
    this(color);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    if (isCentered) {
      add(Box.createVerticalGlue());
    }
  }
}

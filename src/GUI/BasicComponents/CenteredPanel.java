package GUI.BasicComponents;

import GUI.Styles.Colors;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CenteredPanel extends JPanel {
  protected int pixels = 3;
  private final boolean isShadowed;
  GridBagConstraints constraints;

  public CenteredPanel() {
    super();
    this.constraints = new GridBagConstraints();
    this.isShadowed = false;

    setLayout(new GridBagLayout());

    if (isShadowed) {
      Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
      this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));

      setBorder(border);
    }

  }

  public CenteredPanel(boolean isShadowed) {
    this.constraints = new GridBagConstraints();
    this.isShadowed = isShadowed;

    setLayout(new GridBagLayout());

    if (isShadowed) {
      Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
      this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));

      setBorder(border);
    }
  }

  @Override
  public Component add(Component comp) {
    super.add(comp, constraints);
    return comp;
  }

  @Override
  protected void paintComponent(Graphics g) {

    if (this.isShadowed) {
      int shade = 0;
      int topOpacity = 80;
      for (int i = 0; i < pixels; i++) {
        g.setColor(new Color(shade, shade, shade, ((topOpacity / pixels) * i)));
        g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
      }

      setBackground(new Color(Colors.WHITE.getHex()));

      return;
    }

    super.paintComponent(g);
  }
}

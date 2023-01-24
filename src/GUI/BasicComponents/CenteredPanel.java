package GUI.BasicComponents;

import GUI.Styles.Colors;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CenteredPanel extends JPanel {
  protected int pixels = 3;
  private boolean isShadowed;
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

  public CenteredPanel(GridBagConstraints constraints, boolean isShadowed) {
    this.constraints = constraints;
    setLayout(new GridBagLayout());
    setBorder(new LineBorder(Color.GREEN, 3));
  }

  public CenteredPanel(GridBagConstraints constraints) {
    this.constraints = constraints;
    setLayout(new GridBagLayout());
    setBorder(new LineBorder(Color.GREEN, 3));
  }

  @Override
  public Component add(Component comp) {
    super.add(comp, constraints);
    return comp;
  }

  @Override
  protected void paintComponent(Graphics g) {
    System.out.println("[DEBUG] Painting" + g);

    if (this.isShadowed) {
      System.out.println("[DEBUG] Painting shadows");
      int shade = 0;
      int topOpacity = 80;
      for (int i = 0; i < pixels; i++) {
        g.setColor(new Color(shade, shade, shade, ((topOpacity / pixels) * i)));
        g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
      }

      setBackground(new Color(Colors.WHITE.getHex()));

      return;
    }

    System.out.println("[DEBUG] Painting graphic");
    super.paintComponent(g);
  }
}

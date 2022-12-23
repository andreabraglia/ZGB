package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CenteredPanel extends JPanel {
  GridBagConstraints constraints;
  public CenteredPanel() {
    this.constraints = new GridBagConstraints();
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
}

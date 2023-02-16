package GUI.BasicComponents;

import GUI.Enums.Colors;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Un pannello che permette di centrare i componenti all'interno di esso.
 */
public class CenteredPanel extends JPanel {

  /**
   * Il numero di pixel che separano il bordo del pannello dal bordo del componente.
   */
  protected int pixels = 3;

  /**
   * Se il pannello deve avere un bordo con ombreggiatura.
   */
  private final boolean isShadowed;

  /**
   * Le impostazioni di posizionamento del componente.
   */
  GridBagConstraints constraints;

  /**
   * Crea un pannello con un bordo senza ombreggiatura.
   */
  public CenteredPanel() {
    super();
    this.constraints = new GridBagConstraints();
    this.isShadowed = false;

    setLayout(new GridBagLayout());
  }

  /**
   * Crea un pannello con un bordo con ombreggiatura.
   *
   * @param isShadowed Se il pannello deve avere un bordo con ombreggiatura.
   */
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

  /**
   * Aggiunge un componente al pannello.
   *
   * @param comp Il compoennte da aggiungere
   *
   * @return Il componente aggiunto
   */
  @Override
  public Component add(Component comp) {
    super.add(comp, constraints);
    return comp;
  }

  /**
   * Metodo che disegna il pannello
   *
   * @param g L'oggetto grafico
   */
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

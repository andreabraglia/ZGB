package GUI;

import java.awt.*;

public class Graphic {
  public static void main(String[] args) {
    // crea un nuovo JFrame inizialmente invisibile
    // con titolo "Esempio 1"
    Panel panel = new Panel();
    Frame frame = new Frame("Esempio 4", 0);
    frame.add(panel);
    frame.setVisible(true);

  }
}

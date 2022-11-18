package GUI;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    System.out.println("Painting");

    g.setColor(Color.red); // imposto il colore
    g.fillRect(20, 20, 100, 80); // un rettangolo pieno
    // in posizione 20,20 e dimensioni 100,80
    g.setColor(Color.blue); // cambio colore
    g.drawRect(30, 30, 80, 60);// un rettangolo vuoto
    g.setColor(Color.black);
    g.drawString("ciao", 50, 60); // disegno una stringa
  }
}

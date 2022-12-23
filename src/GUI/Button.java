package GUI;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Button extends JButton {
  public Button(String text) {
    super(text);

    // Imposta il colore di sfondo del bottone
    setBackground(Color.LIGHT_GRAY);

    // Imposta il colore del testo del bottone
    setForeground(Color.BLACK);

    // Imposta il bordo del bottone
    setBorderPainted(false);

    // Imposta il tipo di carattere del testo del bottone
    setFont(new Font("Arial", Font.BOLD, 16));
  }
}

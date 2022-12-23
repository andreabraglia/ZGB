package GUI;

import javax.swing.*;

public class Navbar extends CenteredPanel {
  // Costruttore della classe Navbar
  public Navbar() {
    // Crea i bottoni "Elimina", "Aggiungi" e "Home"
    Button deleteButton = new Button("Elimina");
    Button addButton = new Button("Aggiungi");
    Button homeButton = new Button("Home");

    // Crea un pannello in cui verranno inseriti i bottoni
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(homeButton);
    panel.add(addButton);
    panel.add(deleteButton);

    // Aggiunge il pannello al pannello principale
    add(panel);
  }
}
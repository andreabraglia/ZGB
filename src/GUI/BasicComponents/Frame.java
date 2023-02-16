package GUI.BasicComponents;

import javax.swing.*;

/**
 * Classe per la creazione di una finestra
 */
public class Frame extends JFrame {

  /**
   * Costruttore della classe
   *
   * @param title Titolo della finestra
   * @param optSettings Opzionali, se specificati definiscono le dimensioni e la posizione della finestra
   */
  public Frame(String title, Integer... optSettings) {
    super(title);

    int w = optSettings.length == 1 && optSettings[0] != 0 ? optSettings[0] : 650;
    int h = optSettings.length == 2 && optSettings[1] != 0 ? optSettings[1] : 650;
    int x = optSettings.length == 3 && optSettings[2] != 0 ? optSettings[1] : 400;
    int y = optSettings.length == 4 && optSettings[3] != 0 ? optSettings[1] : 200;

    setBounds(x, y, w, h);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}

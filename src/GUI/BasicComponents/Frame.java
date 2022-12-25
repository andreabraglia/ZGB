package GUI.BasicComponents;

import javax.swing.*;

public class Frame extends JFrame {
  public Frame() {
    this("");
  }

  public Frame(String title, Integer... optSettings) {
    super(title);

    int w = optSettings.length == 1 && optSettings[0] != 0 ? optSettings[0] : 600;
    int h = optSettings.length == 2 && optSettings[1] != 0 ? optSettings[1] : 600;
    int x = optSettings.length == 3 && optSettings[2] != 0 ? optSettings[1] : 400;
    int y = optSettings.length == 4 && optSettings[3] != 0 ? optSettings[1] : 200;

    System.out.println("[INFO] Frame:");
    System.out.printf(" W: %d  |  H: %d \n", w, h);
    System.out.printf(" X: %d  |  Y: %d \n\n", x, y);

    setBounds(x, y, w, h);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}

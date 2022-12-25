package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;

import javax.swing.*;

public class ImportPanel extends CenteredPanel {
  public ImportPanel(ContoCorrente contoCorrente) {
    super(true);

    add(new JLabel("Import conto corrente"));
  }
}
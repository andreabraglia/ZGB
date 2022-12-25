package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;

import javax.swing.*;

public class ExportPanel extends CenteredPanel {
  public ExportPanel(ContoCorrente contoCorrente) {
    super(true);

    add(new JLabel("Export conto corrente"));
  }
}
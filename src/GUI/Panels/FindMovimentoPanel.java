package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;

import javax.swing.*;

public class FindMovimentoPanel extends CenteredPanel {
  public FindMovimentoPanel(ContoCorrente contoCorrente) {
    super(true);

    add(new JLabel("Find Movimento"));
  }
}
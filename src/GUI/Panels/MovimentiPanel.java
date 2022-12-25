package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.MovimentiTableModel;
import GUI.Styles.Colors;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class MovimentiPanel extends CenteredPanel {
  public MovimentiPanel(ContoCorrente contoCorrente) {
    super(true);

    // Crea un nuovo pannello per i campi di input
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(new Color(Colors.WHITE.getHex()));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel header = new JPanel();

    header.setBackground(new Color(Colors.WHITE.getHex()));
    header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

    JLabel titolo = new JLabel(contoCorrente.getIntestatario());
    JLabel numeroConto = new JLabel(" - Numero conto: " + contoCorrente.getNumeroCC());

    Font titleFont = new Font(titolo.getFont().getName(), titolo.getFont().getStyle(), 25);
    Font subtitleFont = new Font(titolo.getFont().getName(), titolo.getFont().getStyle(), 15);

    titolo.setFont(titleFont);
    titolo.setAlignmentX(0);
    numeroConto.setFont(subtitleFont);
    numeroConto.setAlignmentX(0);

    header.add(titolo);
    header.add(numeroConto);


    JLabel saldo = new JLabel("Saldo attuale: " + contoCorrente.getSaldoAttuale() + " €", SwingConstants.CENTER);
    saldo.setFont(subtitleFont);
    saldo.setAlignmentX(0);

    mainPanel.add(header);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(saldo);

    JTable table = new JTable(new MovimentiTableModel(contoCorrente, saldo));
    TableColumn importoColumn = table.getColumnModel().getColumn(0);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    importoColumn.setPreferredWidth((int) (table.getPreferredSize().width / 2.5));
    importoColumn.setCellRenderer(centerRenderer);

    JScrollPane scrollPane = new JScrollPane(table);
    mainPanel.add(scrollPane);
    add(mainPanel);
  }
}

package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Panel;
import GUI.BasicComponents.Table.MovimentiTable;
import GUI.BasicComponents.Table.MovimentiTableModel;
import GUI.BasicComponents.Table.TableFilter;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.util.Collections;

import static GUI.Styles.Dimensions.GAP;

public class MovimentiPanel extends CenteredPanel {
  public MovimentiPanel(ContoCorrente contoCorrente) throws ParseException {
    super(true);

    Panel mainPanel = new Panel(Colors.WHITE, true);
    Panel header = new Panel(Colors.WHITE, true);
    Component spacer = Box.createRigidArea(new Dimension(0, GAP.getDimension()));
    JLabel titolo = new JLabel(contoCorrente.getIntestatario());
    JLabel numeroConto = new JLabel(" - Numero conto: " + contoCorrente.getNumeroCC());
    JLabel saldo = new JLabel("Saldo attuale: " + contoCorrente.getSaldoAttuale() + " €", SwingConstants.CENTER);

    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );

    Font subtitleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.SUBTITLE_FONT_SIZE.getDimension()
    );

    titolo.setFont(titleFont);
    titolo.setAlignmentX(1f);
    titolo.setFont(titolo.getFont().deriveFont(
      Collections.singletonMap(
        TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD))
    );
    header.add(titolo);

    numeroConto.setFont(subtitleFont);
    numeroConto.setAlignmentX(0.8f);
    header.add(numeroConto);


    saldo.setFont(subtitleFont);
    saldo.setAlignmentX(.78f);
    header.add(saldo);

    mainPanel.add(header);
    mainPanel.add(new JSeparator(JSeparator.HORIZONTAL));
    mainPanel.add(spacer);

    Panel filterPanel = new Panel(Colors.WHITE);
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));
    JLabel filterLabel = new JLabel("Filtra per testo: ");
    JTextField filterField = new JTextField(10);
    JButton filterButton = new JButton("Cerca");

    MovimentiTable table = new MovimentiTable(new MovimentiTableModel(contoCorrente, saldo));

    TableFilter filter = new TableFilter(table.getTable());

    filterButton.addActionListener(e -> filterTable(filterField, filter));
    filterField.addActionListener(e -> filterTable(filterField, filter));

    filterPanel.add(filterLabel);
    filterPanel.add(filterField);
    filterPanel.add(filterButton);
    mainPanel.add(filterPanel);
    mainPanel.add(spacer);


    mainPanel.add(table.getScrollPane());

    Panel buttonsPanel = new Panel(Colors.WHITE);
    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

    Button addMovimentoButton = new Button("Aggiungi");
    addMovimentoButton.addActionListener(e -> {
      JFrame frame = new JFrame("Aggiungi movimento");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setResizable(false);

      frame.setLocationRelativeTo(this);
      frame.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
      frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      frame.setResizable(false);

      Panel panel = new Panel(Colors.WHITE);
      Border padding = BorderFactory.createEmptyBorder(Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension());
      panel.setBorder(padding);
      panel.add(new AddMovimentoPanel(contoCorrente, table.getTable(), frame));

      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
    });

    Button deleteMovimentoButton = new Button("Elimina");
    deleteMovimentoButton.addActionListener(e -> {
      int selectedRow = table.getTable().getSelectedRow();
      System.out.println("[DEBUG] Selected ROW: " + selectedRow);
      if (selectedRow != -1) {
        boolean isDone = contoCorrente.deleteMovimento(selectedRow);
        if (isDone) {
          JOptionPane.showMessageDialog(this, "Movimento eliminato con successo\n");
          contoCorrente.printMov();
          table.getTable().updateUI();
        } else {
          JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione del movimento");
        }
      }
    });

    buttonsPanel.add(deleteMovimentoButton);
    buttonsPanel.add(addMovimentoButton);
    mainPanel.add(buttonsPanel);

    add(mainPanel);
  }

  private void filterTable(JTextField filterField, TableFilter filter) {
    String filterString = filterField.getText();
    if (filterString.isEmpty()) {
      filter.removeHighlight();
      return;
    }

    filter.highlightByString(filterString);
  }
}

package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Panel;
import GUI.BasicComponents.Table.MovimentiTable;
import GUI.BasicComponents.Table.MovimentiTableModel;
import GUI.BasicComponents.Table.TableFilter;
import GUI.Enums.Colors;
import GUI.Enums.Dimensions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Collections;

import static GUI.Enums.UtilsComponents.*;

/**
 * Pannello per la visualizzazione, il filtraggio tramite una stringa, l'aggiunta e la rimozione
 * dei movimenti di un conto corrente
 */
public class MovimentiPanel extends CenteredPanel {

  /**
   * Costruttore del pannello che lo inizializza,
   * aggiunge i componenti al suo interno, definendone anche la logica
   *
   * @param contoCorrente Il conto corrente
   *
   */
  public MovimentiPanel(ContoCorrente contoCorrente) {
    super(true);

    Panel mainPanel = new Panel(Colors.WHITE, true);
    Panel header = new Panel(Colors.WHITE, true);

    Component spacer = SPACER.getComponent();

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
    mainPanel.add(DIVIDER.getComponent());
    mainPanel.add(spacer);

    Panel filterPanel = new Panel(Colors.WHITE);
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));
    JLabel filterLabel = new JLabel("Filtra per testo: ");
    JTextField filterField = new JTextField(10);
    JButton filterButton = new JButton("Cerca");

    MovimentiTableModel tableModel = new MovimentiTableModel(contoCorrente, saldo);
    MovimentiTable table = new MovimentiTable(tableModel);

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
      panel.add(new AddMovimentoPanel(contoCorrente, frame, () -> {
        this.updateComponents(table, tableModel, filter);
        return null;
      }));

      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
    });

    Button deleteMovimentoButton = new Button("Elimina");
    deleteMovimentoButton.addActionListener(e -> {
      int selectedRow = table.getTable().getSelectedRow();
      System.out.println("[DEBUG] Selected ROW: " + selectedRow);

      if (selectedRow == -1) {
        return;
      }
      boolean isDone = contoCorrente.deleteMovimento(selectedRow);

      if (isDone) {
        JOptionPane.showMessageDialog(this, "Movimento eliminato con successo\n");
        contoCorrente.printMov();
        updateComponents(table, tableModel, filter);
      } else {
        JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione del movimento");
      }
    });

    buttonsPanel.add(deleteMovimentoButton);
    buttonsPanel.add(addMovimentoButton);
    mainPanel.add(buttonsPanel);

    add(mainPanel);

  }

  /**
   * Filtra la tabella in base al testo inserito nel campo di testo
   *
   * @param filterField {@link JTextField} Campo di testo
   * @param filter      {@link TableFilter} Oggetto che si occupa del filtraggio
   */
  private void filterTable(JTextField filterField, TableFilter filter) {
    String filterString = filterField.getText();
    if (filterString.isEmpty()) {
      filter.removeHighlight();
      return;
    }

    filter.highlightByString(filterString);
  }

  /**
   * Aggiorna i componenti del pannello
   *
   * @param table      {@link MovimentiTable} Tabella dei movimenti
   * @param tableModel {@link MovimentiTableModel} Modello della tabella
   * @param filter     {@link TableFilter} Oggetto che si occupa del filtraggio
   */
  private void updateComponents(MovimentiTable table, MovimentiTableModel tableModel, TableFilter filter) {
    table.getTable().updateUI();
    tableModel.fireTableDataChanged();
    filter.update();
  }
}

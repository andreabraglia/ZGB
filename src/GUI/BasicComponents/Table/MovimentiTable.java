package GUI.BasicComponents.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


/**
 * La classe che contiene la tabella dei movimenti
 */
public class MovimentiTable {
  /**
   * La tabella che continue i movimenti
   */
  private final JTable table;

  /**
   * Lo scroll pane che contiene la tabella
   */
  private final JScrollPane scrollPane;

  /**
   * Costruttore della classe
   *
   * @param model Il modello della tabella
   */
  public MovimentiTable(MovimentiTableModel model) {
    this.table = new JTable(model);

    TableColumn dateColumn = table.getColumnModel().getColumn(MovimentiTableModel.getColumnIndex("Data"));
    dateColumn.setCellEditor(new DateCellEditor());

    TableColumn importoColumn = table.getColumnModel().getColumn(MovimentiTableModel.getColumnIndex("Importo"));
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    importoColumn.setPreferredWidth((int) (table.getPreferredSize().width / 2.5));
    importoColumn.setCellRenderer(centerRenderer);

    this.scrollPane = new JScrollPane(table);
  }

  /**
   * Restituisce lo scroll pane che contiene la tabella
   *
   * @return Lo scroll pane che contiene la tabella
   */
  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  /**
   * Restituisce la tabella dei movimenti
   *
   * @return La tabella
   */
  public JTable getTable() {
    return table;
  }
}

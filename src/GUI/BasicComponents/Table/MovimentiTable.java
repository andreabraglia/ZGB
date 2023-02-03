package GUI.BasicComponents.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.text.ParseException;


public class MovimentiTable {
  private final JTable table;

  private final JScrollPane scrollPane;

  public MovimentiTable(MovimentiTableModel model) throws ParseException {
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

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

  public JTable getTable() {
    return table;
  }
}

package GUI.BasicComponents.Table;

import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class TableFilter {
  private final TableRowSorter<TableModel> sorter;

  public TableFilter(JTable table) {
    this.sorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(sorter);
  }

  public void filterByString(String filterText) {
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterText, 0, 1, 2));
  }

  public void filterByCol(String text, int column) {
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, column));
  }

  public void filterByYear() {
  }

  public void filterByMonth() {
  }

  public void filterByDay() {
  }

  public void filterByDate() {
  }
}

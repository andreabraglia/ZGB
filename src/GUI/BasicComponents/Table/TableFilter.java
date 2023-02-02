package GUI.BasicComponents.Table;

import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class TableFilter {
  private final TableRowSorter<TableModel> sorter;
  private final JTable table;


  public TableFilter(JTable table) {
    this.table = table;
    this.sorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(sorter);
  }

  public void highlightRow(int row) {
    table.setRowSelectionInterval(row, row);
  }

  public void highlightRows(int[] columns) {
    for (int column : columns) {
      table.setColumnSelectionInterval(column, column);
    }
  }

  public void filterByString(String filterText) {
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterText));
  }

  public void filterByCol(String text, int column) {
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, column));
  }

  public void filterByYear(String year) {
    filterByCol(".*/.*/" + year, MovimentiTableModel.getColumnIndex("Data"));
  }

  public void filterByMonth(String month) {
    if (month.length() == 1) {
      month = "0" + month;
    }
    filterByCol(".*/" + month + "/.*", MovimentiTableModel.getColumnIndex("Data"));
  }

  public void filterByDay(String day) {
    if (day.length() == 1) {
      day = "0" + day;
    }

    filterByCol(day + "/.*/.*", MovimentiTableModel.getColumnIndex("Data"));
  }
}

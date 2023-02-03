package GUI.BasicComponents.Table;

import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.time.temporal.WeekFields;
import java.util.stream.IntStream;

public class TableFilter {
  private final TableRowSorter<TableModel> sorter;
  private final JTable table;

  private int clickedRow = -1;

  public TableFilter(JTable table) {
    this.table = table;
    this.sorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(sorter);
  }

  public void highlightRow(int row) {
    table.setRowSelectionInterval(row, row);
  }

  public void removeHighlight() {
    table.clearSelection();
  }

  public void highlightRows(ArrayList<Integer> columns) {
    int j = 0;

    for (int column : columns) {
      if(j > this.clickedRow) {
        highlightRow(column);
        clickedRow++;
        return;
      }

      j++;
    }
  }

  public void filterByString(String filterText) {
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterText));
  }

  public void highlightByString(String filterText) {
    filterText = filterText.toLowerCase();

    int maxRows = table.getRowCount();
    int maxColumns = table.getColumnCount();
    ArrayList<Integer> rows = new ArrayList<>();

    for (int i = 0; i < maxRows; i++) {
      for (int j = 0; j < maxColumns; j++) {
        String cellValue = table.getValueAt(i, j).toString();

        if (cellValue.toLowerCase().contains(filterText)) {
          System.out.println("Trovato: " + cellValue);
          rows.add(i);
          break;
        }
      }
    }

    highlightRows(rows);
  }

  public void filterByCol(String text, int column) {
    sorter.setRowFilter(RowFilter.regexFilter(text, column));
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

  public void filterByWeek(String week) {
    int maxWeeks = Calendar.getInstance().getWeeksInWeekYear();
    int weekNumber = Integer.parseInt(week);

    if (weekNumber > maxWeeks) {
      weekNumber = maxWeeks;
    }

    Locale locale = Locale.getDefault();
    LocalDate date = LocalDate.now().with(WeekFields.of(locale).weekOfWeekBasedYear(), weekNumber);
    LocalDate startOfWeek = date.with(WeekFields.of(locale).dayOfWeek(), 1);
    LocalDate endOfWeek = date.with(WeekFields.of(locale).dayOfWeek(), 7);

    StringBuilder regex = new StringBuilder("(");

    for (int i = 1; i <= 7; i++) {
      regex.append((date.with(WeekFields.of(locale).dayOfWeek(), i)).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(i == 7 ? "" : "|");
    }

    regex.append(")");


    System.out.println("\n\n[DEBUG] (FilterByWeek) Week: " + startOfWeek + " - " + endOfWeek);
    System.out.println("[DEBUG] (FilterByWeek) Regex: " + regex);


    filterByCol(String.valueOf(regex), MovimentiTableModel.getColumnIndex("Data"));
  }
}

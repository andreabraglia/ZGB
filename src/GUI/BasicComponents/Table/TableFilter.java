package GUI.BasicComponents.Table;

import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.time.temporal.WeekFields;

/**
 * Classe che permette di filtrare una tabella in base a una stringa.
 */
public class TableFilter {
  /**
   * Oggetto che permette di filtrare la tabella.
   */
  private final TableRowSorter<TableModel> sorter;

  /**
   * Tabella da filtrare.
   */
  private final JTable table;

  /**
   * Numero di volte che si effettua la stessa ricerca.
   */
  private int clickedRow = -1;

  /**
   * Stringa di ricerca precedente.
   */
  private String previousSearch = "";

  /**
   * Costruttore della classe.
   *
   * @param table Tabella da filtrare.
   */
  public TableFilter(JTable table) {
    this.table = table;
    this.sorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(sorter);
  }

  /**
   * Metodo che permette di evidenziare una riga della tabella.
   *
   * @param row Riga da evidenziare.
   */
  public void highlightRow(int row) {
    table.setRowSelectionInterval(row, row);
  }

  /**
   * Metodo che permette di rimuovere l'evidenziazione di una riga.
   */
  public void removeHighlight() {
    table.clearSelection();
  }

  /**
   * Metodo che permette di evidenziare più righe della tabella.
   *
   * @param columns Righe da evidenziare.
   */
  public void highlightRows(ArrayList<Integer> columns) {
    int j = 0;

    for (int column : columns) {
      if (j > this.clickedRow) {
        highlightRow(column);
        clickedRow++;
        return;
      }

      j++;
    }
  }

  /**
   * Metodo che di filtrare tramite una data stringa
   *
   * @param filterText Stringa da filtrare.
   */
  public void filterByString(String filterText) {
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterText));
  }

  /**
   * Metodo che di evidenziare le righe tramite una data stringa
   *
   * @param filterText Stringa da filtrare.
   */
  public void highlightByString(String filterText) {
    filterText = filterText.toLowerCase();

    if(!filterText.equals(previousSearch)) {
      clickedRow = -1;
      previousSearch = filterText;
    }

    int maxRows = table.getRowCount();
    int maxColumns = table.getColumnCount();
    ArrayList<Integer> rows = new ArrayList<>();

    for (int i = 0; i < maxRows; i++) {
      for (int j = 0; j < maxColumns; j++) {
        String cellValue = table.getValueAt(i, j).toString();

        if (cellValue.toLowerCase().contains(filterText)) {
          rows.add(i);
          break;
        }
      }
    }

    highlightRows(rows);
  }

  /**
   * Metodo che permette di filtrare tramite una data stringa e una colonna.
   *
   * @param text   Stringa da filtrare.
   * @param column Colonna da filtrare.
   */
  public void filterByCol(String text, int column) {
    sorter.setRowFilter(RowFilter.regexFilter(text, column));
  }

  /**
   * Metodo che permette di filtrare per anno
   *
   * @param year Stringa che denota un anno.
   */
  public void filterByYear(String year) {
    filterByCol(".*/.*/" + year, MovimentiTableModel.getColumnIndex("Data"));
  }

  /**
   * Metodo che permette di filtrare per mese
   *
   * @param month Stringa che denota un mese.
   */
  public void filterByMonth(String month) {
    if (month.length() == 1) {
      month = "0" + month;
    }
    filterByCol(".*/" + month + "/.*", MovimentiTableModel.getColumnIndex("Data"));
  }

  /**
   * Metodo che permette di filtrare per giorno
   *
   * @param day Stringa che denota un giorno.
   */
  public void filterByDay(String day) {
    if (day.length() == 1) {
      day = "0" + day;
    }

    filterByCol(day + "/.*/.*", MovimentiTableModel.getColumnIndex("Data"));
  }

  /**
   * Metodo che permette di filtrare per il numero della settimana
   * dell'anno attuale
   *
   * @param week Stringa che denota una settimana
   */
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

  /**
   * Metodo che aggiorna il sorter({@link TableFilter#sorter})
   */
  public void update() {
    sorter.modelStructureChanged();
  }
}

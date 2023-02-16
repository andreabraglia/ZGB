package GUI.BasicComponents.Table;

import Core.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Table model per la tabella dei movimenti
 */
public class MovimentiTableModel extends AbstractTableModel implements TableModelListener {

  /**
   * Nomi delle colonne
   */
  private static final String[] columnNames = { "Importo", "Descrizione", "Data" };

  /**
   * Label per l'importo totale
   */
  private final JLabel totalAmountLabel;

  /**
   * Conto corrente
   */
  private final ContoCorrente cc;

  /**
   * Costruttore
   *
   * @param cc               Conto corrente
   * @param totalAmountLabel Label per l'importo totale
   */
  public MovimentiTableModel(ContoCorrente cc, JLabel totalAmountLabel) {
    this.cc = cc;
    this.totalAmountLabel = totalAmountLabel;
    this.addTableModelListener(this);
  }

  /**
   * Restituisce il numero di righe
   *
   * @return Numero di righe
   */
  @Override
  public int getRowCount() {
    return cc.getContatoreMovimenti();
  }

  /**
   * Restituisce il numero di colonne.
   * Override del metodo della classe astratta {@link AbstractTableModel}
   *
   * @return Numero di colonne
   */
  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  /**
   * Restituisce il nome della colonna.
   * Override del metodo della classe astratta {@link AbstractTableModel}
   *
   * @param columnIndex Indice della colonna
   *
   * @return Nome della colonna
   */
  @Override
  public String getColumnName(int columnIndex) {
    return columnNames[columnIndex];
  }

  /**
   * Restituisce la classe della colonna.
   * Override del metodo della classe astratta {@link AbstractTableModel}
   *
   * @param columnIndex Indice della colonna
   *
   * @return Classe della colonna
   */
  @Override
  public Class<?> getColumnClass(int columnIndex) {
    if (columnIndex == 0) {
      return Float.class;
    } else if (columnIndex == 2) {
      return LocalDateTime.class;
    }

    return String.class;
  }

  /**
   * Restituisce se la cella è modificabile.
   * Override del metodo della classe astratta {@link AbstractTableModel}
   *
   * @param rowIndex    Indice della riga
   * @param columnIndex Indice della colonna
   *
   * @return Se la cella è modificabile
   */
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return true;
  }

  /**
   * Restituisce il valore della cella.
   * Override del metodo della classe astratta {@link AbstractTableModel}
   *
   * @param rowIndex    Indice della riga
   * @param columnIndex Indice della colonna
   *
   * @return Valore della cella
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Movimento movimento = cc.getMovimento(rowIndex);
    if (movimento == null) {
      return null;
    }

    if (columnIndex == 0) {
      return movimento.getAmount();
    } else if (columnIndex == 1) {
      return movimento.getDescription();
    } else {
      return movimento.getDate().format(Movimento.FORMATTER);
    }
  }

  /**
   * Imposta il valore della cella.
   * Override del metodo della classe astratta {@link AbstractTableModel}
   *
   * @param value       Valore da impostare
   * @param rowIndex    Indice della riga
   * @param columnIndex Indice della colonna
   */
  @Override
  public void setValueAt(Object value, int rowIndex, int columnIndex) throws IllegalArgumentException {
    Movimento movimento = cc.getMovimento(rowIndex);

    try {

      switch (columnIndex) {
        case 0 -> {
          // Importo
          movimento.setAmount((float) value);
        }
        case 1 -> {
          // Descrizione
          movimento.setDescription((String) value);
        }
        case 2 -> {
          // Data
          DateTimeFormatter formatter = Movimento.FORMATTER;
          movimento.setDate(LocalDateTime.parse((String) value, formatter));
        }
        default -> {
          throw new IllegalArgumentException("Invalid column index");
        }
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Errore: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
    }

    fireTableCellUpdated(rowIndex, columnIndex);
    fireTableDataChanged();
  }

  /**
   * Handler dell'evento del cambiamento della tabella
   *
   * @param event {@link TableModelEvent} che notifica al TableModel che un evento è avvenuto
   */
  @Override
  public void tableChanged(TableModelEvent event) {
    System.out.println("[DEBUG] Table changed");
    updateTotalAmount();
  }

  /**
   * Aggiorna l'importo totale dei movimenti
   */
  private void updateTotalAmount() {
    System.out.println("[DEBUG] Update total amount label");
    // Calcola l'importo totale
    float totalAmount = cc.getSaldoAttuale();

    // Aggiorna il label con l'importo totale
    totalAmountLabel.setText("Importo totale: " + totalAmount + "€");
  }

  /**
   * Restituisce l'indice della colonna
   *
   * @param rowName Nome della colonna
   *
   * @return Indice della colonna
   */
  public static int getColumnIndex(String rowName) {
    return java.util.Arrays.asList(columnNames).indexOf(rowName);
  }
}
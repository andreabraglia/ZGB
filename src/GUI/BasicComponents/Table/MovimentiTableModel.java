package GUI.BasicComponents.Table;

import Core.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovimentiTableModel extends AbstractTableModel implements TableModelListener {
  private static final String[] columnNames = { "Importo", "Descrizione", "Data" };

  private final JLabel totalAmountLabel;

  private final ContoCorrente cc;

  public MovimentiTableModel(ContoCorrente cc, JLabel totalAmountLabel) {
    this.cc = cc;
    this.totalAmountLabel = totalAmountLabel;
    this.addTableModelListener(this);
  }

  @Override
  public int getRowCount() {
    return cc.getContatoreMovimenti();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return columnNames[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    if (columnIndex == 0) {
      return Float.class;
    } else if (columnIndex == 2) {
      return LocalDateTime.class;
    }

    return String.class;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return true;
  }

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

  @Override
  public void setValueAt(Object value, int rowIndex, int columnIndex) {
    Movimento movimento = cc.getMovimento(rowIndex);

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

    fireTableCellUpdated(rowIndex, columnIndex);
    fireTableDataChanged();
  }

  @Override
  public void tableChanged(TableModelEvent e) {
    System.out.println("[DEBUG] Table changed");
    updateTotalAmount();
  }

  private void updateTotalAmount() {
    System.out.println("[DEBUG] Update total amount label");
    // Calcola l'importo totale
    float totalAmount = cc.getSaldoAttuale();

    // Aggiorna il label con l'importo totale
    totalAmountLabel.setText("Importo totale: " + totalAmount + "€");
  }

  public static int getColumnIndex(String rowName) {
    return java.util.Arrays.asList(columnNames).indexOf(rowName);
  }
}
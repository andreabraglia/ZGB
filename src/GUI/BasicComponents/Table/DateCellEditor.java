package GUI.BasicComponents.Table;

import Core.Movimento;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

/**
 * Classe che implementa un editor di celle per la tabella che permette di inserire una data nel formato dd/mm/yyyy
 */
public class DateCellEditor extends DefaultCellEditor {

  /**
   * Campo di testo formattato per la data in formato dd/mm/yyyy {@link Movimento#MASK_DATE_FORMAT}
   */
  private final JFormattedTextField formattedTextField;

  /**
   * Costruttore
   */
  public DateCellEditor() {
    super(new JFormattedTextField());

    formattedTextField = (JFormattedTextField) getComponent();
    try {
      MaskFormatter dateMask = new MaskFormatter(Movimento.MASK_DATE_FORMAT);
      DefaultFormatterFactory dateMaskFormat = new DefaultFormatterFactory(dateMask);

      formattedTextField.setFormatterFactory(dateMaskFormat);
      formattedTextField.setValue(null);
      formattedTextField.setHorizontalAlignment(JFormattedTextField.CENTER);
      formattedTextField.setFocusLostBehavior(JFormattedTextField.PERSIST);
    } catch (ParseException e) {
      JOptionPane.showMessageDialog(null, "Errore nel formato della data", "Errore", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Restituisce il valore del campo di testo formattato
   *
   * @return Il valore del campo di testo formattato
   */
  public Object getCellEditorValue() {
    return formattedTextField.getValue();
  }

  /**
   * Restituisce il componente per l'editor di celle
   *
   * @param table      La tabella
   * @param value      Il valore della cella
   * @param isSelected Se la cella è selezionata
   * @param row        La riga della cella
   * @param column     La colonna della cella
   *
   * @return Il componente per l'editor di celle
   */
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    formattedTextField.setValue(value);
    return formattedTextField;
  }
}

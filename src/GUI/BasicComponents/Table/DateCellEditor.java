package GUI.BasicComponents.Table;

import Core.Movimento;

import java.awt.*;
import java.io.Serial;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class DateCellEditor extends DefaultCellEditor {
  @Serial
  private static final long serialVersionUID = 1L;
  private final JFormattedTextField formattedTextField;

  public DateCellEditor() throws ParseException {
    super(new JFormattedTextField());

    formattedTextField = (JFormattedTextField) getComponent();

    MaskFormatter dateMask = new MaskFormatter(Movimento.MASK_DATE_FORMAT);
    DefaultFormatterFactory dateMaskFormat = new DefaultFormatterFactory(dateMask);

    formattedTextField.setFormatterFactory(dateMaskFormat);
    formattedTextField.setValue(null);
    formattedTextField.setHorizontalAlignment(JFormattedTextField.CENTER);
    formattedTextField.setFocusLostBehavior(JFormattedTextField.PERSIST);
  }

  public Object getCellEditorValue() {
    return formattedTextField.getValue();
  }

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    formattedTextField.setValue(value);
    return formattedTextField;
  }
}

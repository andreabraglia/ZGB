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
  private final JFormattedTextField ftf;
//  private DateTimeFormatter formatter;

  public DateCellEditor() throws ParseException {
    super(new JFormattedTextField());
    ftf = (JFormattedTextField) getComponent();
//    DateFormat format = new SimpleDateFormat(Movimento.DATE_FORMAT);
    MaskFormatter dateMask = new MaskFormatter(Movimento.MASK_DATE_FORMAT);
    DefaultFormatterFactory dateMaskFormat = new DefaultFormatterFactory(dateMask);

    ftf.setFormatterFactory(dateMaskFormat);
    ftf.setValue(null);
    ftf.setHorizontalAlignment(JFormattedTextField.CENTER);
    ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);
  }

  public Object getCellEditorValue() {
    return ftf.getValue();
  }

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    ftf.setValue(value);
    return ftf;
  }
}

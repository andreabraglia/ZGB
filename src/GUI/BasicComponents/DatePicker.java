package GUI.BasicComponents;

import GUI.Styles.Colors;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DatePicker extends JPanel {

  private JComboBox<Integer> yearComboBox;
  private JComboBox<Integer> monthComboBox;
  private JComboBox<Integer> dayComboBox;
  private JSpinner hourSpinner;
  private JSpinner minuteSpinner;

  public DatePicker(Colors bgColor) {
    setLayout(new GridLayout(3, 2));
    setBackground(new Color(bgColor.getHex()));

    // Crea una selezione di anni
    Integer[] years = new Integer[100];
    for (int i = 0; i < 100; i++) {
      years[i] = LocalDate.now().getYear() - i;
    }
    yearComboBox = new JComboBox<>(years);

    // Crea una selezione di mesi
    Integer[] months = new Integer[12];
    for (int i = 1; i <= 12; i++) {
      months[i - 1] = i;
    }
    monthComboBox = new JComboBox<>(months);

    // Crea una selezione di giorni
    Integer[] days = new Integer[31];
    for (int i = 1; i <= 31; i++) {
      days[i - 1] = i;
    }
    dayComboBox = new JComboBox<>(days);

    // Crea uno spinner per le ore
    SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
    hourSpinner = new JSpinner(hourModel);

    // Crea uno spinner per i minuti
    SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
    minuteSpinner = new JSpinner(minuteModel);

    // Aggiunge i componenti al pannello
    add(new JLabel("Anno:"));
    add(yearComboBox);
    add(new JLabel("Mese:"));
    add(monthComboBox);
    add(new JLabel("Giorno:"));
    add(dayComboBox);
    add(new JLabel("Ora:"));
    add(hourSpinner);
    add(new JLabel("Minuti:"));
    add(minuteSpinner);
  }

  public LocalDate getSelectedDate() {
    return LocalDate.of((Integer) yearComboBox.getSelectedItem(),
      (Integer) monthComboBox.getSelectedItem(),
      (Integer) dayComboBox.getSelectedItem());
  }

  public LocalTime getSelectedTime() {
    return LocalTime.of((Integer) hourSpinner.
      getValue(), (Integer) minuteSpinner.getValue());
  }

  public LocalDateTime getSelectedDateTime() {
    return LocalDateTime.of(getSelectedDate(), getSelectedTime());
  }

  public void setSelectedDate(LocalDate date) {
    yearComboBox.setSelectedItem(date.getYear());
    monthComboBox.setSelectedItem(date.getMonthValue());
    dayComboBox.setSelectedItem(date.getDayOfMonth());
  }

  public void setSelectedTime(LocalTime time) {
    hourSpinner.setValue(time.getHour());
    minuteSpinner.setValue(time.getMinute());
  }

}
package GUI.BasicComponents;

import GUI.Enums.Colors;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.*;

public class DatePicker extends JPanel {

  private final JComboBox<Integer> yearComboBox;
  private final JComboBox<Integer> monthComboBox;
  private final JComboBox<Integer> dayComboBox;
  private final JSpinner hourSpinner;
  private final JSpinner minuteSpinner;

  public DatePicker(Colors bgColor) {
    GridLayout layout = new GridLayout(3, 2);
    layout.setHgap(10);
    layout.setVgap(10);
    setLayout(layout);
    setBackground(new Color(bgColor.getHex()));

    // Crea una selezione di anni
    Integer[] years = new Integer[100];
    for (int i = 0; i < 100; i++) {
      years[i] = LocalDate.now().getYear() - i;
    }
    yearComboBox = new JComboBox<>(years);
    yearComboBox.getModel().setSelectedItem(LocalDate.now().getYear());

    // Crea una selezione di mesi
    Integer[] months = new Integer[12];
    for (int i = 1; i <= 12; i++) {
      months[i - 1] = i;
    }
    monthComboBox = new JComboBox<>(months);
    monthComboBox.getModel().setSelectedItem(LocalDate.now().getMonthValue());

    // Crea una selezione di giorni
    Integer[] days = new Integer[31];
    for (int i = 1; i <= 31; i++) {
      days[i - 1] = i;
    }
    dayComboBox = new JComboBox<>(days);
    dayComboBox.getModel().setSelectedItem(LocalDate.now().getDayOfMonth());


    // Crea uno spinner per le ore
    SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
    hourSpinner = new JSpinner(hourModel);
    hourSpinner.getModel().setValue(LocalTime.now().getHour());

    // Crea uno spinner per i minuti
    SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
    minuteSpinner = new JSpinner(minuteModel);
    minuteSpinner.getModel().setValue(LocalTime.now().getMinute());

    // Aggiunge i componenti al pannello
    add(new JLabel("Anno:"));
    add(yearComboBox);
    add(new JLabel("Mese:"));
    add(monthComboBox);
    add(new JLabel("Giorno:"));
    add(dayComboBox);

    add(new JLabel(""));
    add(new JLabel(""));
    add(new JLabel("Ora:"));
    add(hourSpinner);
    add(new JLabel("Minuti:"));
    add(minuteSpinner);
  }

  public LocalDate getSelectedDate() throws NullPointerException {

    return (
      LocalDate.of((Integer) yearComboBox.getSelectedItem(),
        (Integer) monthComboBox.getSelectedItem(),
        (Integer) dayComboBox.getSelectedItem())
    );
  }

  public LocalTime getSelectedTime() {
    return LocalTime.of((Integer) hourSpinner.
      getValue(), (Integer) minuteSpinner.getValue());
  }

  public LocalDateTime getSelectedDateTime() {
    return LocalDateTime.of(getSelectedDate(), getSelectedTime());
  }
}
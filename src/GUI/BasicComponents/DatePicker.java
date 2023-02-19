package GUI.BasicComponents;

import GUI.Enums.Colors;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.*;

/**
 * Clase che crea un date picker.
 */
public class DatePicker extends JPanel {

  /**
   * Combobox per scegliere l'anno
   */
  private final JComboBox<Integer> yearComboBox;

  /**
   * Combobox per scegliere il mese
   */
  private final JComboBox<Integer> monthComboBox;

  /**
   * Combobox per scegliere il giorno
   */
  private final JComboBox<Integer> dayComboBox;

  /**
   * Spinner per scegliere l'ora
   */
  private final JSpinner hourSpinner;

  /**
   * Spinner per scegliere i minuti
   */
  private final JSpinner minuteSpinner;

  /**
   * Costruttore
   *
   * @param bgColor Colore di sfondo del date picker
   */
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

    yearComboBox.addActionListener(e -> handleChange());
    monthComboBox.addActionListener(e -> handleChange());
    dayComboBox.addActionListener(e -> handleChange());

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

  /**
   * Ritorna la data selezionata.
   *
   * @return La data selezionata.
   */
  public LocalDate getSelectedDate() throws NullPointerException {
    return (
      LocalDate.of(
        (Integer) yearComboBox.getSelectedItem(),
        (Integer) monthComboBox.getSelectedItem(),
        (Integer) dayComboBox.getSelectedItem()
      )
    );
  }

  /**
   * Ritorna l'ora e i minuti selezionati
   *
   * @return L'ora selezionata
   */
  public LocalTime getSelectedTime() {
    return LocalTime.of((Integer) hourSpinner.
      getValue(), (Integer) minuteSpinner.getValue());
  }

  /**
   * Ritorna la data, l'ora e i minuti selezionati
   *
   * @return La data e l'ora selezionata
   */
  public LocalDateTime getSelectedDateTime() {
    return LocalDateTime.of(getSelectedDate(), getSelectedTime());
  }

  /**
   * Aggiorna il numero di giorni nel mese in base al mese e all'ann scelto
   */
  public void handleChange() {
    try {
      // Aggiorna il numero di giorni nel mese
      int year = yearComboBox.getSelectedItem() != null ?
        (Integer) yearComboBox.getSelectedItem() :
        LocalDate.now().getYear();
      int month = monthComboBox.getSelectedItem() != null
        ? (Integer) monthComboBox.getSelectedItem()
        : LocalDate.now().getMonthValue();
      int day = dayComboBox.getSelectedItem() != null
        ? (Integer) dayComboBox.getSelectedItem()
        : LocalDate.now().getDayOfMonth();

      int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
      if (day > maxDay) {
        dayComboBox.getModel().setSelectedItem(maxDay);
      }

      Integer[] days = new Integer[maxDay];
      for (int i = 1; i <= maxDay; i++) {
        days[i - 1] = i;
      }

      dayComboBox.setModel(new DefaultComboBoxModel<>(days));

    } catch (Exception exception) {
      JOptionPane.showMessageDialog(null, "Errore nel calcolo del numero di giorni nel mese");
      exception.printStackTrace();
    }
  }
}
package Core;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe Movimento
 */
public class Movimento implements Serializable {
  /**
   * Importo della transazione
   */
  private float amount;

  /**
   * Descrizione della transazione
   */
  private String description;
  /**
   * Data della transazione
   */
  private LocalDateTime date;

  /**
   * Formattatore della data
   */
  public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

  /**
   * Formato della data
   */
  public static String DATE_FORMAT = "dd/MM/yyyy HH:mm";

  /**
   * Formato della data per la maschera
   */
  public static String MASK_DATE_FORMAT = "##/##/#### ##:##";

  /**
   * Costruttore della classe Transazione
   *
   * @param amount      Importo della transazione
   * @param description Descrizione della transazione
   * @param date        Data della transazione
   */
  public Movimento(float amount, String description, LocalDateTime date) {
    this.amount = amount;
    this.description = description;
    this.date = date;
  }

  /**
   * Restituisce l'importo della transazione
   *
   * @return amount
   */
  public float getAmount() {
    return amount;
  }

  /**
   * Restituisce la descrizione della transazione.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets date.
   *
   * @return the date
   */
  public LocalDateTime getDate() {
    return date;
  }

  /**
   * Restituisce una stringa che rappresenta la transazione
   *
   * @return Stringa che rappresenta l'oggetto.
   */
  public String toString() {
    return String.format("Amount: %s\nDescription: %s\nDate: %s", amount, description, (date.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))));
  }

  /**
   * Setta l'importo del movimento.
   *
   * @param amount the amount
   */
  public void setAmount(float amount) {
    this.amount = amount;
  }

  /**
   * Setta la descrizione del movimento.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Setta la data del movimento.
   *
   * @param date the date
   */
  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}

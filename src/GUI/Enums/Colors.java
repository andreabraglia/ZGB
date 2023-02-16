package GUI.Enums;

/**
 * Enum che definisce la palette di colori dell'applicazione
 */
public enum Colors {
  /**
   * Colore principale
   */
  PRIMARY(0x007bff),

  /**
   * Colore secondario
   */
  SECONDARY(0x6c757d),

  /**
   * Colore per definire azione positiva
   */
  SUCCESS(0x28a745),

  /**
   * Colore per definire azione negativa
   */
  DANGER(0xdc3545),

  /**
   * Colore per definire un avviso
   */
  WARNING(0xfd7e14),

  /**
   * Colore per definire un messaggio informativo
   */
  INFO(0x17a2b8),

  /**
   * Colore per definire un altro tipo di bianco
   */
  LIGHT(0xf8f9fa),

  /**
   * Colore per definire il bianco
   */
  WHITE(0xffffff),

  /**
   * Colore per definire il nero
   */
  DARK(0x343a40);

  /**
   * Hex del colore
   */
  private final int hex;

  /**
   * Costruttore dell'enum
   *
   * @param hex Hex del colore
   */
  Colors(int hex) {
    this.hex = hex;
  }

  /**
   * Getter dell'hex del colore
   *
   * @return Restituisce l'hex del colore come intero
   */
  public int getHex() {
    return hex;
  }
}

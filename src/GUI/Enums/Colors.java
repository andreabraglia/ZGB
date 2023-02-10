package GUI.Enums;

/**
 * Enum che definisce la palette di colori dell'applicazione
 */
public enum Colors {
  PRIMARY(0x007bff),
  SECONDARY(0x6c757d),
  SUCCESS(0x28a745),
  DANGER(0xdc3545),
  WARNING(0xfd7e14),
  INFO(0x17a2b8),
  LIGHT(0xf8f9fa),
  WHITE(0xffffff),
  DARK(0x343a40);

  private final int hex;

  /**
   * Costruttore dell'enum
   * @param hex
   */
  Colors(int hex) {
    this.hex = hex;
  }

  /**
   * Getter dell'hex del colore
   * @return Restituisce l'hex del colore come intero
   */
  public int getHex() {
    return hex;
  }
}

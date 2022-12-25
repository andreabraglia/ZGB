package GUI.Styles;

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

  Colors(int hex) {
    this.hex = hex;
  }

  public int getHex() {
    return hex;
  }
}

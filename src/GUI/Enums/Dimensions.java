package GUI.Enums;

/*+
 * Enum che definisce le dimensioni dell'applicazione
 */
public enum Dimensions {
  TITLE_FONT_SIZE(25),
  SUBTITLE_FONT_SIZE(15),
  GAP(10),
  PADDING(5);

  /**
   * Dimensione dell'elemento
   */
  private final int dimension;

  Dimensions(int i) {
    this.dimension = i;
  }

  /**
   * Restituisce la dimensione dell'elemento
   * @return Dimensione dell'elemento come intero
   */
  public int getDimension() {
    return dimension;
  }
}

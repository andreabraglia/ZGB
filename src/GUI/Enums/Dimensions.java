package GUI.Enums;


/**
 * Enum che definisce le dimensioni dell'applicazione
 */
public enum Dimensions {

  /**
   * Dimensione del titolo
   */
  TITLE_FONT_SIZE(25),

  /**
   * Dimensione del sottotitolo
   */
  SUBTITLE_FONT_SIZE(15),

  /**
   * Dimensione del gap tra i componenti
   */
  GAP(10),

  /**
   * Dimensione del padding
   */
  PADDING(5);

  /**
   * Dimensione dell'elemento
   */
  private final int dimension;

  /**
   * Costruttore dell'enum
   *
   * @param i Dimensione dell'elemento
   */
  Dimensions(int i) {
    this.dimension = i;
  }

  /**
   * Restituisce la dimensione dell'elemento
   *
   * @return Dimensione dell'elemento come intero
   */
  public int getDimension() {
    return dimension;
  }
}

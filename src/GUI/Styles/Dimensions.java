package GUI.Styles;

public enum Dimensions {
  TITLE_FONT_SIZE(25),
  SUBTITLE_FONT_SIZE(15),
  GAP(10),
  PADDING(5);

  private final int dimension;

  Dimensions(int i) {
    this.dimension = i;
  }

  public int getDimension() {
    return dimension;
  }
}

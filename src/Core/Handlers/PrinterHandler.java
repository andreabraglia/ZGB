package Core.Handlers;

import Core.ContoCorrente;
import Core.Movimento;

import java.awt.*;
import java.awt.print.PageFormat;

import java.awt.print.Printable;
import java.time.format.DateTimeFormatter;

/**
 * Classe che implementa {@link Printable} e serve
 * per la stampa del conto corrente
 *
 * @see Printable
 */
public class PrinterHandler implements Printable {
  /**
   * Il conto corrente da stampare
   */
  private final ContoCorrente contoCorrente;

  /**
   * Costruttore
   *
   * @param contoCorrente Il conto corrente da stampare
   */
  public PrinterHandler(ContoCorrente contoCorrente) {
    this.contoCorrente = contoCorrente;
  }

  /**
   * Stampa il conto corrente
   *
   * @param graphics   L'ogetto grafico
   * @param pageFormat Il formato della pagina
   * @param pageIndex  L'indice della pagina
   *
   * @return Il risultato della stampa
   */
  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
    if (pageIndex != 0) {
      return Printable.NO_SUCH_PAGE;
    }

    Graphics2D g2d = (Graphics2D) graphics;
    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

    String header = "- ZGB -";
    g2d.drawString(header, (int) pageFormat.getImageableWidth() / 2 - header.length() * 2, 10);


    String[] ccInfo = new String[]{
      "Intestatario: " + contoCorrente.getIntestatario(),
      "Numero conto corrente: " + contoCorrente.getNumeroCC(),
      "Saldo iniziale: " + contoCorrente.getSaldoIniziale(),
      "Saldo attuale: " + contoCorrente.getSaldoAttuale(),
      "Movimenti: " + contoCorrente.getNumeroMovimenti(),
      "",
    };

    int line = 0;
    for (String info : ccInfo) {
      graphics.drawString(info, (int) pageFormat.getImageableX(), (int) pageFormat.getImageableY() + 20 + (15 * (line + 1)));
      line++;
    }

    int nMovimenti = contoCorrente.getNumeroMovimenti();
    for (int i = 0; i < nMovimenti; i++, line++) {
      Movimento mov = contoCorrente.getMovimento(i);
      graphics.drawString(
        String.format("Somma: %s\t Descrizione: %s - %s", mov.getAmount(), mov.getDescription(), mov.getDate().format(DateTimeFormatter.ofPattern(Movimento.DATE_FORMAT))),
        (int) pageFormat.getImageableX(),
        (int) pageFormat.getImageableY() + 20 + (15 * (line + 1))
      );
    }


    return PAGE_EXISTS;
  }
}


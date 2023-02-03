package Core;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

import java.awt.print.Printable;
import java.time.format.DateTimeFormatter;

public class PrinterHandler implements Printable {
  private final ContoCorrente contoCorrente;

  public PrinterHandler(ContoCorrente contoCorrente) {
    this.contoCorrente = contoCorrente;
  }

  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
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


import Core.Transazione;
import Core.TransactionType;
import GUI.Graphic;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Graphic.main(args);
        Transazione t1 = new Transazione(100, TransactionType.Entrata, "Entrata di prova", LocalDateTime.now());
        Transazione t2 = new Transazione(-100, TransactionType.Spesa, "Uscita di prova", LocalDateTime.now());
        System.out.println(t1);
        System.out.println("-----------------------");
        System.out.println(t2);
    }
}

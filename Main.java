import Core.*;
import GUI.*;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Graphic.main(args);
        Transazione t1 = new Transazione(100, TransactionType.Entrata, "Ciao", LocalDateTime.now());
        System.out.println(t1.toString());
    }
}

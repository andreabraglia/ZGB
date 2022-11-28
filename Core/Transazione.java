package Core;

import java.time.LocalDateTime;

/**
 * Classe Transazione.
 */
public class Transazione {


    /**
     * Importo della transazione
     */
    private int amount;

    /**
     * Tipo della transazione
     */
    private TransactionType type;
    /**
     * Descrizione della transazione
     */
    private String description;
    /**
     * Data della transazione
     */
    private LocalDateTime date;

    /**
     * Costruttore della classe Transazione
     *
     * @param amount      Importo della transazione
     * @param type        Tipo della transazione
     * @param description Descrizione della transazione
     * @param date        Data della transazione
     */
    public Transazione(int amount, TransactionType type, String description, LocalDateTime date) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    /**
     * Restituisce l'importo della transazione
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Restituisce il tipo di transazione
     *
     * @return the type
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Restituisce la descrizione della transazione.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Restituisce una stringa che rappresenta la transazione
     *
     * @return Stringa che rappresenta l'oggetto.
     */
    public String toString() {
        return String.format("Amount: %s\nType: %s\nDescription: %s\nDate: %s", amount, type, description, (date.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))));
    }
}

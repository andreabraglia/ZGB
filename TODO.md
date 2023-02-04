- [ ] Creare documentazione
- [x] Gestione del bilancio
  - [x] Mostrare somma totale
  - [x] Modifica voce
    - [x] Aggiungi voce (Default data NOW)
    - [x] Rimuovi voce
    - [x] Modifica voce
  - [x] Visualizza bilancio
    - [x] Filtro per singolo giorno
    - [x] Filtro per settimana
    - [x] Filtro per mese
    - [x] Filtro per anno
- [ ] Salvataggio e caricamento del bilancio
  - [x] Salvataggio su file specificandone il nome
    - [x] <strong>+2 pt</strong> Nel caso in cui si tenti di salvare il bilancio in un file che esiste già, deve essere chiesto
      all’utente se desidera sovrascrivere il file esistente
    - [x] <strong>+5 pt</strong> Si implementi un meccanismo di salvataggio automatico basato su un thread che periodicamente salva le informazioni in un file temporaneo
  - [x] Caricamento da file specificandone il nome
- [x] Ricerca di informazioni nel bilancio
  - [x] La ricerca si basa su testo libero che può essere una parte del testo contenuto in una voce
    La ricerca deve evidenziare la prima cella/riga che contiene il testo cercato; l’applicazione deve permettere
    all’utente di continuare la ricerca per evidenziare man mano le celle/righe successive che rispondono ai
    requisiti (ad esempio, tramite un bottone “successivo”, o usando lo stesso bottone della ricerca).
- [x] Esportazione del bilancio
  - [x] Esportazione in formato CSV
  - [x] Esportazione in formato TXT (formato testo, in cui ogni voce è rappresentata da una riga in un file, e ogni elemento della voce è
    separato da un separatore ad esempio, spazio o tabulazione)
  - [x] <strong>+1 pt</strong> Si dia all’utente la possibilità di stampare il bilancio. Si sfruttino le classi di libreria Java
     per stampare tramite una delle stampanti configurate dal sistema operativo

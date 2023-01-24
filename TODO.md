- [ ] Sviluppo multithread
- [ ] Creare documentazione
- [ ] Gestione del bilancio
  - [x] Mostrare somma totale
  - [x] Modifica voce
    - [x]  Aggiungi voce (Default data NOW)
    - [x] Rimuovi voce
    - [x] Modifica voce
  - [ ] Visualizza bilancio
    - Filtro per singolo giorno
    - Filtro per settimana
    - Filtro per mese
    - Filtro per anno
- [ ] Salvataggio e caricamento del bilancio
  - [ ] Salvataggio su file specificandone il nome, nel caso in cui si tenti di salvare il bilancio in un file che esiste già, deve essere chiesto
    all’utente se desidera sovrascrivere il file esistente
  - [ ] Si implementi un meccanismo di salvataggio automatico basato su un thread che
     periodicamente salva le informazioni in un file temporaneo
  - [x] Caricamento da file specificandone il nome
- [ ] Ricerca di informazioni nel bilancio
  - [ ] La ricerca si basa su testo libero che può essere una parte del testo contenuto in una voce
    La ricerca deve evidenziare la prima cella/riga che contiene il testo cercato; l’applicazione deve permettere
    all’utente di continuare la ricerca per evidenziare man mano le celle/righe successive che rispondono ai
    requisiti (ad esempio, tramite un bottone “successivo”, o usando lo stesso bottone della ricerca).
- [ ] Esportazione del bilancio
  - [ ] Esportazione in formato CSV
  - [ ] Esportazione in formato TXT (formato testo, in cui ogni voce è rappresentata da una riga in un file, e ogni elemento della voce è
    separato da un separatore ad esempio, spazio o tabulazione)
  - [ ] Si dia all’utente la possibilità di stampare il bilancio. Si sfruttino le classi di libreria Java
     per stampare tramite una delle stampanti configurate dal sistema operativo
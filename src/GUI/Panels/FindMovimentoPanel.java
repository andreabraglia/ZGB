package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Panel;
import GUI.BasicComponents.Table.MovimentiTable;
import GUI.BasicComponents.Table.MovimentiTableModel;
import GUI.BasicComponents.Table.TableFilter;
import GUI.Enums.Colors;
import GUI.Enums.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;

import static GUI.Enums.UtilsComponents.SPACER;

/**
 * Pannello per trovare e filtrare i movimenti, estende {@link CenteredPanel}
 *
 * @see CenteredPanel
 */
public class FindMovimentoPanel extends CenteredPanel {

  /**
   * Costruttore del pannello che lo inizializza,
   * aggiunge i componenti al suo interno, definendone anche la logica
   *
   * @param contoCorrente Riferimento del conto corrente su cui andranno impostati i filtri
   *
   */
  public FindMovimentoPanel(ContoCorrente contoCorrente) {
    super(true);

    // Crea un nuovo pannello per i campi di input
    Panel mainPanel = new Panel(Colors.WHITE, true);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    Component spacer = SPACER.getComponent();

    JLabel totalAmountLabel = new JLabel("Total Amount: " + contoCorrente.getSaldoAttuale());
    MovimentiTable table = new MovimentiTable(new MovimentiTableModel(contoCorrente, totalAmountLabel));

    TableFilter filter = new TableFilter(table.getTable());


    Panel filterPanel = new Panel(Colors.WHITE, true);
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));

    String[] options = { "Scegli il tipo di filtro...", "Testo", "Anno", "Mese", "Giorno", "Settimana" };
    JComboBox<String> select = new JComboBox<>(options);
    JTextField filterInput = new JTextField(5);


    select.addActionListener(e -> {
      String selectedOption = (String) select.getSelectedItem();
      System.out.println("[DEBUG] Filtro: " + selectedOption);
    });

    filterInput.addActionListener(e -> handleInputChange(select, filterInput, filter));

    Panel header = new Panel(Colors.WHITE, true);
    JLabel titolo = new JLabel("Cerca movimenti");

    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );

    titolo.setFont(titleFont);
    titolo.setAlignmentX(1f);
    titolo.setFont(titolo.getFont().deriveFont(
      Collections.singletonMap(
        TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD))
    );

    header.add(titolo);
    header.add(spacer);
    header.add(select);
    header.add(filterInput);

    mainPanel.add(header);
    mainPanel.add(new JSeparator(JSeparator.HORIZONTAL));
    mainPanel.add(spacer);
    mainPanel.add(table.getScrollPane());

    add(mainPanel);
  }

  /**
   * Mostra un messaggio di errore in un {@link JOptionPane}
   *
   * @param message Messaggio da mostrare
   */
  private void handleErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Gestisce l'input dell'utente, in base al filtro selezionato nel {@link JComboBox}
   * e al valore del filtro inserito nel {@link JTextField}
   *
   * @param select      {@link JComboBox} Definisce i filtri
   * @param filterInput {@link JTextField} Definisce il valore del filtro
   * @param filter      {@link TableFilter} Riferimento alla tabella su cui applicare il filtro
   */
  private void handleInputChange(JComboBox<String> select, JTextField filterInput, TableFilter filter) {
    String selectedOption = (String) select.getSelectedItem();
    String filterValue = filterInput.getText();

    if (filterValue.isEmpty()) {
      filter.filterByString(".*");
      return;
    }

    if (Objects.requireNonNull(selectedOption).isEmpty()) {
      filter.filterByString(filterValue);
      return;
    }

    int value = 0;
    if (!selectedOption.matches("(Testo)|(Scegli il tipo di filtro...)")) {
      try {
        value = Integer.parseInt(filterValue);
        if (value < 1) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException exception) {
        handleErrorMessage("Il valore inserito deve essere un numero, maggiore o uguale a 1");
        return;
      }
    }

    switch (selectedOption) {
      case "Anno" -> {
        if (value > LocalDate.now().getYear()) {
          handleErrorMessage("L'anno deve essere minore o uguale all'anno corrente");
          return;
        }

        filter.filterByYear(filterValue);
      }
      case "Mese" -> {
        if (value > 12) {
          handleErrorMessage("Il mese deve essere un numero minore di 12 e maggiore di 0");
          return;
        }

        filter.filterByMonth(filterValue);
      }
      case "Giorno" -> {
        if (value > LocalDate.now().getMonth().maxLength()) {
          handleErrorMessage("Il mese deve essere un numero minore di 12 e maggiore di 0");
          return;
        }

        filter.filterByDay(filterValue);
      }
      case "Settimana" -> {
        if (value > 52) {
          handleErrorMessage("La settimana deve essere un numero minore di 52 e maggiore di 0");
          return;
        }


        filter.filterByWeek(filterValue);
      }
      default -> filter.filterByString(filterValue);
    }

    System.out.println("[DEBUG] Il valore è: " + filterValue + " e il filtro è: " + selectedOption);
  }
}
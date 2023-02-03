package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Panel;
import GUI.BasicComponents.Table.MovimentiTable;
import GUI.BasicComponents.Table.MovimentiTableModel;
import GUI.BasicComponents.Table.TableFilter;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.util.Collections;
import java.util.Objects;

import static GUI.Styles.Dimensions.GAP;

public class FindMovimentoPanel extends CenteredPanel {
  public FindMovimentoPanel(ContoCorrente contoCorrente) throws ParseException {
    super(true);

    // Crea un nuovo pannello per i campi di input
    Panel mainPanel = new Panel(Colors.WHITE, true);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    Component spacer = Box.createRigidArea(new Dimension(0, GAP.getDimension()));

    JLabel totalAmountLabel = new JLabel("Total Amount: " + contoCorrente.getSaldoAttuale());
    MovimentiTable table = new MovimentiTable(new MovimentiTableModel(contoCorrente, totalAmountLabel));

//    JTable table = new JTable(new MovimentiTableModel(contoCorrente, totalAmountLabel));
    TableFilter filter = new TableFilter(table.getTable());


    Panel filterPanel = new Panel(Colors.WHITE, true);
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));

    String[] options = { "Scegli il tipo di filtro...", "Testo", "Anno", "Mese", "Giorno", "Settimana" };
    JComboBox<String> select = new JComboBox<>(options);
    JTextField filterInput = new JTextField(5);


    select.addActionListener(e -> {
      String selectedOption = (String) select.getSelectedItem();
      System.out.println("Il valore è: " + selectedOption);
    });

    filterInput.addActionListener(e -> {
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

      switch (selectedOption) {
        case "Anno" -> filter.filterByYear(filterValue);
        case "Mese" -> filter.filterByMonth(filterValue);
        case "Giorno" -> filter.filterByDay(filterValue);
        case "Settimana" -> filter.filterByWeek(filterValue);
        default -> filter.filterByString(filterValue);
      }

      System.out.println("[DEBUG] Il valore è: " + filterValue + " e il filtro è: " + selectedOption);
    });


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
}
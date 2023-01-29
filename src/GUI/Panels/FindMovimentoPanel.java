package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Panel;
import GUI.BasicComponents.Table.MovimentiTableModel;
import GUI.BasicComponents.Table.TableFilter;
import GUI.Styles.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FindMovimentoPanel extends CenteredPanel {
  public FindMovimentoPanel(ContoCorrente contoCorrente) {
    super(true);

    // Crea un nuovo pannello per i campi di input
    Panel mainPanel = new Panel(Colors.WHITE, true);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    mainPanel.add(new JLabel("Find Movimento"));

    JLabel totalAmountLabel = new JLabel("Total Amount: " + contoCorrente.getSaldoAttuale());
    JTable table = new JTable(new MovimentiTableModel(contoCorrente, totalAmountLabel));
    TableFilter filter = new TableFilter(table);
    filter.filterByString("Pagamento");

    JScrollPane scrollPane = new JScrollPane(table);
    mainPanel.add(scrollPane);

    Panel filterPanel = new Panel(Colors.LIGHT);
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.X_AXIS));

    String[] options = {"", "Anno", "Mese", "Giorno", "Data"};
    JComboBox<String> select = new JComboBox<>(options);
    JTextField filterInput = new JTextField();


    select.addActionListener(e -> {
      String selectedOption = (String) select.getSelectedItem();
      String filterValue = filterInput.getText();
      if(filterValue.isEmpty() || Objects.requireNonNull(selectedOption).isEmpty()) {
        filter.filterByString("");
        return;
      }

      switch (selectedOption) {
        case "Anno" -> filter.filterByYear();
        case "Mese" -> filter.filterByMonth();
        case "Giorno" -> filter.filterByDay();
        case "Data" -> filter.filterByDate();
        default -> filter.filterByString("");
      }
      System.out.println("Il valore è: " + selectedOption);
    });


    filterPanel.add(select);
    filterPanel.add(filterInput);

    add(mainPanel);
  }
}
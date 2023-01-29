package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Panel;
import GUI.BasicComponents.Table.DateCellEditor;
import GUI.BasicComponents.Table.MovimentiTableModel;
import GUI.Styles.Colors;
import GUI.Styles.Dimensions;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.util.Collections;

import static GUI.Styles.Dimensions.GAP;

public class MovimentiPanel extends CenteredPanel {
  public MovimentiPanel(ContoCorrente contoCorrente) throws ParseException {
    super(true);

    // Crea un nuovo pannello per i campi di input
    Panel mainPanel = new Panel(Colors.WHITE, true);

    Panel header = new Panel(Colors.WHITE, true);

    JLabel titolo = new JLabel(contoCorrente.getIntestatario());
    JLabel numeroConto = new JLabel(" - Numero conto: " + contoCorrente.getNumeroCC());
    JLabel saldo = new JLabel("Saldo attuale: " + contoCorrente.getSaldoAttuale() + " €", SwingConstants.CENTER);

    Font titleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.TITLE_FONT_SIZE.getDimension()
    );

    Font subtitleFont = new Font(
      titolo.getFont().getName(), titolo.getFont().getStyle(), Dimensions.SUBTITLE_FONT_SIZE.getDimension()
    );

    titolo.setFont(titleFont);
    titolo.setAlignmentX(1f);
    titolo.setFont(titolo.getFont().deriveFont(
      Collections.singletonMap(
        TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD))
    );
    header.add(titolo);

    numeroConto.setFont(subtitleFont);
    numeroConto.setAlignmentX(0.8f);
    header.add(numeroConto);

    saldo.setFont(subtitleFont);
    saldo.setAlignmentX(.78f);
    header.add(saldo);

    mainPanel.add(header);
    mainPanel.add(new JSeparator(JSeparator.HORIZONTAL));
    mainPanel.add(Box.createRigidArea(new Dimension(0, GAP.getDimension())));


    JTable table = new JTable(new MovimentiTableModel(contoCorrente, saldo));

    TableColumn dateColumn = table.getColumnModel().getColumn(2);
    dateColumn.setCellEditor(new DateCellEditor());

    TableColumn importoColumn = table.getColumnModel().getColumn(0);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    importoColumn.setPreferredWidth((int) (table.getPreferredSize().width / 2.5));
    importoColumn.setCellRenderer(centerRenderer);

    JScrollPane scrollPane = new JScrollPane(table);
    mainPanel.add(scrollPane);

    Panel buttonsPanel = new Panel(Colors.WHITE);
    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

    Button addMovimentoButton = new Button("Aggiungi");
    addMovimentoButton.addActionListener(e -> {
      JFrame frame = new JFrame("Aggiungi movimento");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setResizable(false);

      frame.setLocationRelativeTo(this);
      frame.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
      frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      frame.setResizable(false);

      Panel panel = new Panel(Colors.WHITE);
      Border padding = BorderFactory.createEmptyBorder(Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension(), Dimensions.PADDING.getDimension());
      panel.setBorder(padding);
      panel.add(new AddMovimentoPanel(contoCorrente, table, frame));

      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
    });

    Button deleteMovimentoButton = new Button("Elimina");
    deleteMovimentoButton.addActionListener(e -> {
      int selectedRow = table.getSelectedRow();
      System.out.println("[DEBUG] Selected ROW: " + selectedRow);
      if (selectedRow != -1) {
        boolean isDone = contoCorrente.deleteMovimento(selectedRow);
        if (isDone) {
          JOptionPane.showMessageDialog(this, "Movimento eliminato con successo\n");
          contoCorrente.printMov();
          table.updateUI();
        } else {
          JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione del movimento");
        }
      }
    });

    buttonsPanel.add(deleteMovimentoButton);
    buttonsPanel.add(addMovimentoButton);
    mainPanel.add(buttonsPanel);

    add(mainPanel);
  }
}

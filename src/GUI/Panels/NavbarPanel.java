package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Button;
import GUI.BasicComponents.Panel;
import GUI.Enums.Colors;

import javax.swing.*;

import java.awt.*;

/**
 * Pannello per la navigazione tra i vari pannelli
 */
public class NavbarPanel extends CenteredPanel {
  JPanel mainPanel;
  JPanel currentObject;
  ContoCorrente contoCorrente;

  // Costruttore della classe Navbar
  public NavbarPanel(JPanel mainPanel, JPanel currentObject, ContoCorrente contoCorrente) {

    this.mainPanel = mainPanel;
    this.currentObject = currentObject;
    this.contoCorrente = contoCorrente;

    // Crea i bottoni "Elimina", "Aggiungi" e "Home"

    Button homeButton = createButton("Home");
    Button findButton = createButton("Cerca");
    Button importButton = createButton("Importa");
    Button exportButton = createButton("Esporta");

    // Crea un pannello in cui verranno inseriti i bottoni
    Panel panel = new Panel(Colors.LIGHT);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(homeButton);
    panel.add(findButton);
    panel.add(importButton);
    panel.add(exportButton);

    // Aggiunge il pannello al pannello principale
    mainPanel.add(currentObject, BorderLayout.CENTER);
    add(panel);

    setBackground(new Color(Colors.LIGHT.getHex()));
  }

  private Button createButton(String text) {
    Button button = new Button(text);

    button.addActionListener(e -> {
      System.out.println("[DEBUG] Clicked on " + text);
      navigateTo(text);
    });

    return button;
  }


  public void navigateTo(String panelName) {
    System.out.println("[DEBUG] Navigate to " + panelName);
    mainPanel.remove(currentObject);

    System.out.println("[DEBUG] Panel: " + mainPanel);
    System.out.println("[DEBUG] Remove: " + currentObject);

    if (contoCorrente.isEmpty() && !panelName.equals("Importa")) {
      currentObject = new CreationAccountPanel(contoCorrente, () -> {
        navigateTo("Home");
        return null;
      });
      mainPanel.add(currentObject, BorderLayout.CENTER);
      mainPanel.updateUI();
      return;
    }

    try {
      switch (panelName) {
        case "Home" -> {
          // Mostra il pannello principale
          currentObject = new MovimentiPanel(contoCorrente);
        }
        case "Cerca" -> {
          // Mostra il pannello per cercare un elemento
          currentObject = new FindMovimentoPanel(contoCorrente);
        }
        case "Importa" -> {
          // Mostra il pannello per importare un file
          currentObject = new ImportPanel(contoCorrente);
        }
        case "Esporta" -> {
          // Mostra il pannello per esportare un file
          currentObject = new ExportPanel(contoCorrente);
        }
        default -> {
        }
      }
    } catch (Exception error) {
      error.printStackTrace();
      JOptionPane.showMessageDialog(mainPanel, error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
    }

    mainPanel.add(currentObject, BorderLayout.CENTER);
    mainPanel.updateUI();
  }
}
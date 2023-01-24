package GUI.Panels;

import Core.ContoCorrente;
import GUI.BasicComponents.CenteredPanel;
import GUI.BasicComponents.Button;
import GUI.Styles.Colors;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;

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

    Button deleteButton = createButton("Elimina");
    Button addButton = createButton("Aggiungi");
    Button homeButton = createButton("Home");
    Button findButton = createButton("Cerca");
    Button importButton = createButton("Importa");
    Button exportButton = createButton("Esporta");

    // Crea un pannello in cui verranno inseriti i bottoni
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(new Color(Colors.LIGHT.getHex()));
    panel.add(homeButton);
    panel.add(addButton);
    panel.add(deleteButton);
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

    MouseListener mouseListener = new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent evt) {
        System.out.println("[DEBUG] Button: Entered");
        Component button = evt.getComponent();
        Font font = button.getFont();
        Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        button.setFont(font.deriveFont(attributes));
      }

      @Override
      public void mouseExited(MouseEvent evt) {
        System.out.println("[DEBUG] Button: Exited");
        Component button = evt.getComponent();
        Font font = button.getFont();
        Map<TextAttribute, ?> attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, null);
        button.setFont(font.deriveFont(attributes));
      }
    };

    button.addMouseListener(mouseListener);

    button.addActionListener(e -> {
      System.out.println("[DEBUG] Clicked on " + text);
      mainPanel.remove(currentObject);

      System.out.println("[DEBUG] Panel: " + mainPanel);
      System.out.println("[DEBUG] Remove: " + currentObject);

      if (contoCorrente.isEmpty() && !text.equals("Importa")) {
        currentObject = new CreationAccountPanel(contoCorrente);
        mainPanel.add(currentObject, BorderLayout.CENTER);
        mainPanel.updateUI();
        return;
      }

      // Gestisce l'evento di click del bottone
      switch (text) {
        case "Elimina" -> {
          // Mostra il pannello per eliminare un elemento
          // currentObject = new RemoveMovimentoPanel(contoCorrente);
          currentObject = new CenteredPanel(true);
          currentObject.add(new JLabel("Elimina"));
        }
        case "Aggiungi" -> {
          // Mostra il pannello per aggiungere un elemento
          currentObject = new AddMovimentoPanel(contoCorrente);
        }
        case "Home" -> {
          // Mostra il pannello principale
          try {
            currentObject = new MovimentiPanel(contoCorrente);

          } catch (Exception e1) {

            e1.printStackTrace();
          }
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


      mainPanel.add(currentObject, BorderLayout.CENTER);
      mainPanel.updateUI();
    });

    return button;
  }
}
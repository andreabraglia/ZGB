package Core;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class AutoSaver extends Thread {
  private final ContoCorrente contoCorrente;

  private static final String directory = ".backup/";

  private static final String fileName = "autoSave.tmp";


  private File autoSaveFile = new File(directory + fileName);

  private int interval = 1000;

  private Timer timer;

  public AutoSaver(ContoCorrente contoCorrente) {
    this.contoCorrente = contoCorrente;
    this.timer = new Timer(interval, event -> this.run());
  }

  public void start() {
    timer.start();
  }

  @Override
  public void run() {
    System.out.println("\n[DEBUG] [" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(Movimento.DATE_FORMAT + ":ss")) + "] Salvataggio automatico in corso...");
    System.out.println("[DEBUG] File: " + autoSaveFile.getAbsolutePath());

    try {
      Path path = Path.of(directory);
      if (!Files.isDirectory(path)) {
        Files.createDirectory(path);
      }

      contoCorrente.writeToTXTFile(autoSaveFile.getAbsolutePath());
    } catch (Exception error) {
      JOptionPane.showMessageDialog(null, "Errore durante il salvataggio automatico del conto corrente:\n " + error.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
      error.printStackTrace();
    }
  }

//  public void setInterval(int interval) {
//    this.interval = interval;
//  }
//
  public void setAutoSaveFile(String fileName) {
    this.autoSaveFile = new File(directory + fileName);
  }

  public static String getAutoSaveFile() {
    return (directory + fileName);
  }
}

package model;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Notificacion {

    private final String task;
    private final Image image = new ImageIcon(getClass().getResource("../assets/notification.png")).getImage();
    private final TrayIcon trayIcon = new TrayIcon(image, "Aplicación Java");
    private final SystemTray systemtray = SystemTray.getSystemTray();
    private boolean band;
    private Timer timer;

    public Notificacion(String task) {
        this.task = task;
        if (SystemTray.isSupported()) {
            // Configurar trayIcon como lo estabas haciendo

            try {
                systemtray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Error:" + e.getMessage());
            }
        } else {
            System.err.println("Error: SystemTray no es soportado");
        }
    }

    public void showNotification(String text, TrayIcon.MessageType type) {
        trayIcon.displayMessage("Notificación Sistema Java:", text, type);
    }

    public void scheduleNotification(int timeInMilliseconds) {
        band = false;
        timer = new Timer();
        timer.schedule(new MyTimerTask(), timeInMilliseconds);
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (!band) {
                showNotification("¡Atención! Tu tarea " + task + " ha vencido", TrayIcon.MessageType.INFO);
                band = true; // Marcar como notificado para que no se repita la notificación
                timer.cancel(); // Detener el timer si es necesario
            }
        }
    }
}
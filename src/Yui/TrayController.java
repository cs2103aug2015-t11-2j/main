package Yui;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TrayController {
	public static TrayIcon trayIcon;
	Stage stage;
	Scene scene;
	StackPane root;

	public void TrayService(Stage stage) {
		this.stage = stage;
	}

	public static TrayIcon createTrayIcon(final Stage stage) {

		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			java.awt.Image image = Toolkit.getDefaultToolkit().getImage(TrayController.class.getResource(Yui_GUI.ICON_PATH));
			stage.getIcons().add(new Image(Yui_GUI.ICON_PATH));

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (SystemTray.isSupported()) {
								stage.hide();
								showTrayMsg();
							} else {
								System.exit(0);
							}
						}
					});
				}
			});


			ActionListener showListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							stage.show();
							stage.toFront();
						}
					});
				}
			};

			trayIcon = new TrayIcon(image, "Yui");
			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(showListener);
			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getButton() == 1) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								stage.show();
								stage.toFront();
							}
						});
					}
				};
			});


			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println(e);
			}
		}
		return trayIcon;
	}

	public static void showTrayMsg() {
		trayIcon.displayMessage("Yui", "My Master, I am still here~", TrayIcon.MessageType.INFO);
	}
}

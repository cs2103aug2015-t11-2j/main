//@@author A0133992X
package GUI;

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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TrayController {
	private TrayIcon trayIcon;
	Stage stage;
	Scene scene;
	private static TrayController theTrayController;
	
	private TrayController(){
	}
	
	public static TrayController getInstance(){
		if(theTrayController == null){
			theTrayController = new TrayController();
		}
		return theTrayController;
	}

	public TrayIcon createTrayIcon(final Stage stage, String iconPath) {

		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			java.awt.Image image = Toolkit.getDefaultToolkit().getImage(TrayController.class.getResource(iconPath));
			stage.getIcons().add(new Image(iconPath));

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

	public void showTrayMsg() {
		trayIcon.displayMessage("Yui", "My Master, I am still here~", TrayIcon.MessageType.INFO);
	}
}

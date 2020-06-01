package ru.inversionkavkaz.dnrwsadm.utils;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import ru.inversion.fx.form.Alerts;
import ru.inversion.fx.form.JInvFXBrowserController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Alert {
    public static void showError(JInvFXBrowserController parent, String message, Throwable ex){
        ex.printStackTrace();
        Logger.getLogger(parent.getName()).log(Level.SEVERE, null, ex);
        Alerts.error("Ошибка", message, ex.getMessage());
    }

    public static ButtonType yesNoCancel(Object parentWindow, String title, String headerText, String contentText) {
        ButtonType bt =
                Alerts.showAlert(parentWindow, javafx.scene.control.Alert.AlertType.CONFIRMATION, title, headerText, contentText,
                (Object)null, null, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        return bt;
    }

    public static void showError(String title, String message, Throwable e) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());

        VBox dialogPaneContent = new VBox();
        Label label = new Label("Stack Trace:");
        String stackTrace = "";
        for(int i=0; i< e.getStackTrace().length; i++){
            stackTrace+=" " + e.getStackTrace()[i];
        }

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }
}

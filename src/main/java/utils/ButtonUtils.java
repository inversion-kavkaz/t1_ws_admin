package utils;

import javafx.event.EventHandler;
import ru.inversion.fx.form.action.JInvAction;
import ru.inversion.fx.form.controls.JInvButton;
import ru.inversion.fx.form.controls.JInvToolBar;
import ru.inversion.icons.IconDescriptorBuilder;
import ru.inversion.icons.enums.FontAwesome;

public class ButtonUtils {

    public static JInvButton addBtn(JInvToolBar toolBar, String title, String tooltip, EventHandler event) {
        JInvButton jInvButton = new JInvButton(title);
        jInvButton.setText(title);
        jInvButton.setToolTipText(tooltip);
        jInvButton.setOnAction(event);
        toolBar.getItems().add(jInvButton);
        return jInvButton;
    }

    public static JInvButton addBtn(JInvToolBar toolBar, String title, EventHandler event) {
        return addBtn(toolBar, title, title, event);
    }

    public static JInvButton addBtn(JInvToolBar toolBar, String tooltip, FontAwesome iconID, EventHandler event) {
        JInvAction jInvPropAction = new JInvAction();
        jInvPropAction.setToolTip(tooltip);
        jInvPropAction.setHandler(event);
        jInvPropAction.setIcon(new IconDescriptorBuilder(iconID, (String) null).build());
        JInvButton btn = new JInvButton();
        btn.setAction(jInvPropAction);
        toolBar.getItems().add(btn);
        return btn;
    }

}

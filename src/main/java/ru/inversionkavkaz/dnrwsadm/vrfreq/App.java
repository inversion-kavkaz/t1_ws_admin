package ru.inversionkavkaz.dnrwsadm.vrfreq;

import ru.inversion.fx.app.BaseApp;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.fx.form.ViewContext;
import ru.inversion.tc.TaskContext;
import ru.inversionkavkaz.dnrwsadm.vrfreq.controller.ViewVIkVrfReqController;

import java.util.Collections;
import java.util.Map;

public class App extends BaseApp
{
    @Override
    protected void showMainWindow ()
    {
        showVIkVrfReqController (getPrimaryViewContext (), new TaskContext(), Collections.emptyMap ());
    }

    @Override
    public String getAppID ()
    {
        return "ONLINE PROVIDERS PAYMENT VERIFIER";
    }

    public static void main (String[] args)
    {
        launch (args);
    }

    private static void showVIkVrfReqController(ViewContext vc, TaskContext tc, Map<String, Object> p)
    {
        new FXFormLauncher<>(tc, vc, ViewVIkVrfReqController.class)
                .initProperties (p)
                .show ();
    }
}

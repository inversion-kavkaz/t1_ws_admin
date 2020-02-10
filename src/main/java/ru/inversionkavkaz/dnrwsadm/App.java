package ru.inversionkavkaz.dnrwsadm;
import java.util.Collections;
import java.util.Map;
import ru.inversion.fx.form.ViewContext;
import ru.inversion.fx.app.BaseApp;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.tc.TaskContext;
import ru.inversionkavkaz.dnrwsadm.entity.ViewIkOvDnrServiceController;

/**
 *
 * @author  bvv
 * @since   Fri Feb 07 13:00:45 MSK 2020
 */
public class App extends BaseApp
{
    
    @Override
    protected void showMainWindow () 
    {
        showViewIkOvDnrService (getPrimaryViewContext (), new TaskContext (), Collections.emptyMap ());
    }

    @Override
    public String getAppID () 
    {
        return "XXI.PIKOVDNRSERVICE"; 
    }
    
    public static void main (String[] args) 
    {
        launch (args);
    }

    public static void showViewIkOvDnrService (ViewContext vc, TaskContext tc, Map<String, Object> p) 
    {
        new FXFormLauncher<> (tc, vc, ViewIkOvDnrServiceController.class)
            .initProperties (p)
            .show ();
    }
    
}


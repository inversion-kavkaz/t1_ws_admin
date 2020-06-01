package ru.inversionkavkaz.dnrwsadm.controller;

import ru.inversion.fx.app.AppException;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrServiceParam;

/**
 * @author  porche
 * @since   Wed Feb 12 14:46:31 MSK 2020
 */
public class EditIkOvDnrServiceParamController extends JInvFXFormController <PIkOvDnrServiceParam>
{
    public static final String INIT_PROP = "INIT_PROP";
    private String systemName = "";

    @FXML JInvTextField SERVICE_ID;
//    @FXML JInvTextField PNAME;
//    @FXML JInvTextField PVALUE;

//
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init ();
        systemName = (String) getInitProperties().get(INIT_PROP);
    }

    @Override
    protected void afterInit() throws AppException {
        super.afterInit();
        SERVICE_ID.setText(systemName);
    }

    @Override
    protected boolean onOK() {

        return super.onOK();
    }
}


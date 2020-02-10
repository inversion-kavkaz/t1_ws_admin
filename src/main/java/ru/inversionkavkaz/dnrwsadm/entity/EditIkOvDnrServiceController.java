package ru.inversionkavkaz.dnrwsadm.entity;

import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;

/**
 * @author  bvv
 * @since   Fri Feb 07 13:05:59 MSK 2020
 */
public class EditIkOvDnrServiceController extends JInvFXFormController <PIkOvDnrService>
{  
    @FXML JInvTextField CNAME;
//    @FXML JInvLongField IENABLED;
//    @FXML JInvTextField CDESCRIPTION;
//    @FXML JInvTextField SECURITY_TAG;
//    @FXML JInvTextField CURL;

//
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init (); 
    }

    @Override
    protected boolean onOK() {
        CNAME.setText(CNAME.getText().toUpperCase());
        return super.onOK();
    }
}


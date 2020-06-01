package ru.inversionkavkaz.dnrwsadm.ovplat.controller;


import javafx.fxml.FXML;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversionkavkaz.dnrwsadm.ovplat.entity.POvPlat;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;

/**
 *
 * @author  porche
 * @since   Mon May 25 12:25:27 MSK 2020
 */
public class ViewOvPlatController extends JInvFXBrowserController 
{
    @FXML private JInvTable<POvPlat> OV_PLAT;
    @FXML private JInvToolBar toolBar;

 
   
    private final XXIDataSet<POvPlat> dsOV_PLAT = new XXIDataSet<> ();    
//
//
//    
    private void initDataSet () throws Exception 
    {
        dsOV_PLAT.setTaskContext (getTaskContext ());
        dsOV_PLAT.setRowClass (POvPlat.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<POvPlat> dsfx = DSFXAdapter.bind (dsOV_PLAT, OV_PLAT, null, false); 

        dsfx.setEnableFilter (true);
 
                
        initToolBar ();

        OV_PLAT.setToolBar (toolBar);       
//        OV_PLAT.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        OV_PLAT.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
//        OV_PLAT.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
//        OV_PLAT.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        OV_PLAT.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
//
//    
    private void doRefresh () 
    {
        OV_PLAT.executeQuery ();
    }
//
//
//    
    private void initToolBar () 
    {
        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.CREATE, 
                                    ActionFactory.ActionTypeEnum.VIEW,
                                    ActionFactory.ActionTypeEnum.UPDATE,
                                    ActionFactory.ActionTypeEnum.DELETE,
                                    ActionFactory.ActionTypeEnum.REFRESH);
        
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }
//
//
//    
    private void doOperation ( JInvFXFormController.FormModeEnum mode ) 
    {
        POvPlat p = null;

        switch (mode) {
            case VM_INS:
                p = new POvPlat ();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsOV_PLAT.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<POvPlat> (getTaskContext (), getViewContext (), EditOvPlatController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)    
                .modal (true)
                .show ();
    }
//
// 
//
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<POvPlat> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsOV_PLAT.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsOV_PLAT.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsOV_PLAT.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        OV_PLAT.requestFocus ();
    }        
//
//
//    
}


package ru.inversionkavkaz.dnrwsadm.protocol.controller;

import javafx.fxml.FXML;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;


import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.dnrwsadm.protocol.entity.PIkOvDnrServiceProtocol;

/**
 *
 * @author  porche
 * @since   Fri May 22 12:01:34 MSK 2020
 */
public class ViewIkOvDnrServiceProtocolController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkOvDnrServiceProtocol> IK_OV_DNR_SERVICE_PROTOCOL;
    @FXML private JInvToolBar toolBar;

    private final XXIDataSet<PIkOvDnrServiceProtocol> dsIK_OV_DNR_SERVICE_PROTOCOL = new XXIDataSet<> ();    
//
//
//    
    private void initDataSet () throws Exception 
    {
        dsIK_OV_DNR_SERVICE_PROTOCOL.setTaskContext (getTaskContext ());
        dsIK_OV_DNR_SERVICE_PROTOCOL.setRowClass (PIkOvDnrServiceProtocol.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkOvDnrServiceProtocol> dsfx = DSFXAdapter.bind (dsIK_OV_DNR_SERVICE_PROTOCOL, IK_OV_DNR_SERVICE_PROTOCOL, null, false); 

        dsfx.setEnableFilter (true);
 
                
        initToolBar ();

        IK_OV_DNR_SERVICE_PROTOCOL.setToolBar (toolBar);       
        IK_OV_DNR_SERVICE_PROTOCOL.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_OV_DNR_SERVICE_PROTOCOL.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IK_OV_DNR_SERVICE_PROTOCOL.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IK_OV_DNR_SERVICE_PROTOCOL.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_OV_DNR_SERVICE_PROTOCOL.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
//
//    
    private void doRefresh () 
    {
        IK_OV_DNR_SERVICE_PROTOCOL.executeQuery ();
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
        PIkOvDnrServiceProtocol p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkOvDnrServiceProtocol ();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_OV_DNR_SERVICE_PROTOCOL.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkOvDnrServiceProtocol> (getTaskContext (), getViewContext (), EditIkOvDnrServiceProtocolController.class)
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
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkOvDnrServiceProtocol> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_OV_DNR_SERVICE_PROTOCOL.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_OV_DNR_SERVICE_PROTOCOL.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_OV_DNR_SERVICE_PROTOCOL.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_OV_DNR_SERVICE_PROTOCOL.requestFocus ();
    }        
//
//
//    
}


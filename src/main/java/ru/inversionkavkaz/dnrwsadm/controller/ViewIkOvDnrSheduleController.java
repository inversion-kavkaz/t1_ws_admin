package ru.inversionkavkaz.dnrwsadm.controller;

import javafx.fxml.FXML;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrShedule;

/**
 *
 * @author  porche
 * @since   Mon Nov 30 14:53:45 MSK 2020
 */
public class ViewIkOvDnrSheduleController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkOvDnrShedule> IK_OV_DNR_SHEDULE;   
    @FXML private JInvToolBar toolBar;

 
   
    private final XXIDataSet<PIkOvDnrShedule> dsIK_OV_DNR_SHEDULE = new XXIDataSet<> ();
//
//
//    
    private void initDataSet () throws Exception 
    {
        dsIK_OV_DNR_SHEDULE.setTaskContext (getTaskContext ());
        dsIK_OV_DNR_SHEDULE.setRowClass (PIkOvDnrShedule.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkOvDnrShedule> dsfx = DSFXAdapter.bind (dsIK_OV_DNR_SHEDULE, IK_OV_DNR_SHEDULE, null, false); 

        dsfx.setEnableFilter (true);
 
                
        initToolBar ();

        IK_OV_DNR_SHEDULE.setToolBar (toolBar);       
        IK_OV_DNR_SHEDULE.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_OV_DNR_SHEDULE.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IK_OV_DNR_SHEDULE.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IK_OV_DNR_SHEDULE.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_OV_DNR_SHEDULE.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
//
//    
    private void doRefresh () 
    {
        IK_OV_DNR_SHEDULE.executeQuery ();
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
        PIkOvDnrShedule p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkOvDnrShedule ();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_OV_DNR_SHEDULE.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkOvDnrShedule> (getTaskContext (), getViewContext (), EditIkOvDnrSheduleController.class)
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
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkOvDnrShedule> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_OV_DNR_SHEDULE.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_OV_DNR_SHEDULE.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_OV_DNR_SHEDULE.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_OV_DNR_SHEDULE.requestFocus ();
    }        
//
//
//    
}


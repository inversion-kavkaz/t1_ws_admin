package ru.inversionkavkaz.dnrwsadm.controller;

import javafx.fxml.FXML;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrPayLog;
import ru.inversionkavkaz.dnrwsadm.utils.AltPrintReportType;

/**
 * @author  bvv
 * @since   Mon Feb 10 09:58:41 MSK 2020
 */
public class ViewIkOvDnrPayLogController extends JInvFXBrowserController 
{
    public static final String INIT_PROP = "INIT_PROP";
    private String systemName = "";
    @FXML private JInvTable<PIkOvDnrPayLog> IK_OV_DNR_PAY_LOG;
    @FXML private JInvToolBar toolBar;
    @FXML private DSInfoBar IK_OV_DNR_PAY_LOG$MARK;

    private final XXIDataSet<PIkOvDnrPayLog> dsIK_OV_DNR_PAY_LOG = new XXIDataSet<> ();
//
//
//    
    private void initDataSet () throws Exception 
    {
        systemName = (String) getInitProperties().get(INIT_PROP);
        dsIK_OV_DNR_PAY_LOG.setTaskContext (getTaskContext ());
        dsIK_OV_DNR_PAY_LOG.setRowClass (PIkOvDnrPayLog.class);
        dsIK_OV_DNR_PAY_LOG.setWherePredicat(PIkOvDnrPayLog.Columns.CSYSTEM + "='"+ systemName+"'");
    }

//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        initDataSet ();
        setTitle (getBundleString ("VIEW.TITLE") + " " + systemName);

        DSFXAdapter<PIkOvDnrPayLog> dsfx = DSFXAdapter.bind (dsIK_OV_DNR_PAY_LOG, IK_OV_DNR_PAY_LOG, null, true); 

        dsfx.setEnableFilter (true);
        IK_OV_DNR_PAY_LOG$MARK.init (IK_OV_DNR_PAY_LOG.getDataSetAdapter ());
        IK_OV_DNR_PAY_LOG$MARK.addAggregator ("1", AggrFuncEnum.COUNT, AggregatorType.MARK, null, null);
 
        initToolBar ();

        IK_OV_DNR_PAY_LOG.setToolBar (toolBar);       
//        IK_OV_DNR_PAY_LOG.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_OV_DNR_PAY_LOG.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
//        IK_OV_DNR_PAY_LOG.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
//        IK_OV_DNR_PAY_LOG.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_OV_DNR_PAY_LOG.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
//
//    
    private void doRefresh () 
    {
        IK_OV_DNR_PAY_LOG.executeQuery ();
    }
//
//
//    
    private void initToolBar () 
    {
        toolBar.setStandartActions (
//        ActionFactory.ActionTypeEnum.CREATE,
                                    ActionFactory.ActionTypeEnum.VIEW,
//                                    ActionFactory.ActionTypeEnum.UPDATE,
//                                    ActionFactory.ActionTypeEnum.DELETE,
                                    ActionFactory.ActionTypeEnum.REFRESH);
        
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
        JInvButtonPrint altPrint = new JInvButtonPrint(this::prePrintAp);
        altPrint.setReportTypeId(AltPrintReportType.PRINT_WS_LOG_TYPE);
        toolBar.getItems().add(altPrint);
    }
//
//
//
    private void prePrintAp(ApReport apReport) {
        String p1 = dsIK_OV_DNR_PAY_LOG.getCurrentRow() == null ? "" : dsIK_OV_DNR_PAY_LOG.getCurrentRow().getID().toString();
        String p2 = dsIK_OV_DNR_PAY_LOG.getMarkerID() == null ? "" : dsIK_OV_DNR_PAY_LOG.getMarkerID().toString();
        apReport.setParam(p1, p2);
    }


    private void doOperation ( JInvFXFormController.FormModeEnum mode ) 
    {
        PIkOvDnrPayLog p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkOvDnrPayLog ();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_OV_DNR_PAY_LOG.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkOvDnrPayLog> (getTaskContext (), getViewContext (), EditIkOvDnrPayLogController.class)
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
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkOvDnrPayLog> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_OV_DNR_PAY_LOG.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_OV_DNR_PAY_LOG.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_OV_DNR_PAY_LOG.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_OV_DNR_PAY_LOG.requestFocus ();
    }        
//
//
//    
}


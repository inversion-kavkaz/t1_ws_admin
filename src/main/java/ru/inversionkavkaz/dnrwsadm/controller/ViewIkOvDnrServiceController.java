package ru.inversionkavkaz.dnrwsadm.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.renderer.JInvTableCell;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrPayLog;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrService;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrShedule;
import ru.inversionkavkaz.dnrwsadm.ovplat.controller.ViewOvPlatController;
import ru.inversionkavkaz.dnrwsadm.ovplat.entity.POvPlat;
import ru.inversionkavkaz.dnrwsadm.protocol.controller.ViewIkOvDnrServiceProtocolController;
import ru.inversionkavkaz.dnrwsadm.protocol.entity.PIkOvDnrServiceProtocol;
import ru.inversionkavkaz.dnrwsadm.utils.AltPrintReportType;
import ru.inversionkavkaz.dnrwsadm.utils.ButtonUtils;
import ru.inversionkavkaz.dnrwsadm.utils.DbUtils;

import java.util.function.BiConsumer;

/**
 *
 * @author  Valeriy Bugaev
 * @since   Fri Feb 07 13:05:55 MSK 2020
 */
public class ViewIkOvDnrServiceController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkOvDnrService> IK_OV_DNR_SERVICE;
    @FXML JInvTableColumn CNAME;
    @FXML private JInvToolBar toolBar;
    @FXML private DSInfoBar IK_OV_DNR_SERVICE$MARK;

    private JInvButtonPrint altPrint = null;

    public static final String STYLE_NO_ACTIVE = "-fx-background-color: #ff182c;\n" +
            "    -fx-background-insets: 0 1 1 0;\n" +
            "    -fx-text-fill: #ffffff;\n";


    private final XXIDataSet<PIkOvDnrService> dsIK_OV_DNR_SERVICE = new XXIDataSet<> ();    
//
//
//    
    private void initDataSet () throws Exception 
    {
        dsIK_OV_DNR_SERVICE.setTaskContext (getTaskContext ());
        dsIK_OV_DNR_SERVICE.setRowClass (PIkOvDnrService.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkOvDnrService> dsfx = DSFXAdapter.bind (dsIK_OV_DNR_SERVICE, IK_OV_DNR_SERVICE, null, true); 

        dsfx.setEnableFilter (true);
        IK_OV_DNR_SERVICE$MARK.init (IK_OV_DNR_SERVICE.getDataSetAdapter ());
        IK_OV_DNR_SERVICE$MARK.addAggregator ("1", AggrFuncEnum.COUNT, AggregatorType.MARK, null, null);

        initCellRenderer();
        initToolBar ();

        IK_OV_DNR_SERVICE.setToolBar (toolBar);       
        IK_OV_DNR_SERVICE.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_OV_DNR_SERVICE.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IK_OV_DNR_SERVICE.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IK_OV_DNR_SERVICE.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_OV_DNR_SERVICE.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
//
//    
    private void doRefresh () 
    {
        IK_OV_DNR_SERVICE.executeQuery ();
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
        ButtonUtils.addBtn(toolBar,"Доп. параметры сервиса", this::onShowServiceParams);
        ButtonUtils.addBtn(toolBar,"Журнал оплаты", this::onShowPayLog);
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));

        altPrint = new JInvButtonPrint(this::prePrintAp);
        altPrint.setReportTypeId(AltPrintReportType.PRINT_WS_TYPE);
        toolBar.getItems().add(altPrint);
    }

    private void onShowServiceParams(Event event) {
        if (dsIK_OV_DNR_SERVICE.getCurrentRow() == null) return;
        final String cSystemName  = dsIK_OV_DNR_SERVICE.getCurrentRow().getCNAME();

        ParamMap mapProp = new ParamMap();
        mapProp.put(ViewIkOvDnrPayLogController.INIT_PROP, cSystemName);
        new FXFormLauncher<PIkOvDnrPayLog>(getTaskContext(), getViewContext(), ViewIkOvDnrServiceParamController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .initProperties(mapProp)
                .callback(null)
                .modal(true)
                .show();
    }

    private void onShowPayLog(Event event) {
        if (dsIK_OV_DNR_SERVICE.getCurrentRow() == null) return;
        final String cSystemName  = dsIK_OV_DNR_SERVICE.getCurrentRow().getCNAME();

        ParamMap mapProp = new ParamMap();
        mapProp.put(ViewIkOvDnrPayLogController.INIT_PROP, cSystemName);
        new FXFormLauncher<PIkOvDnrPayLog>(getTaskContext(), getViewContext(), ViewIkOvDnrPayLogController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .initProperties(mapProp)
                .callback(null)
                .modal(true)
                .show();
    }

    private void prePrintAp(ApReport apReport) {
        String p1 = dsIK_OV_DNR_SERVICE.getCurrentRow() == null ? "" : dsIK_OV_DNR_SERVICE.getCurrentRow().getCNAME();
        String p2 = dsIK_OV_DNR_SERVICE.hasMarkedRows()||dsIK_OV_DNR_SERVICE.getMarkerID() == null ? "" : dsIK_OV_DNR_SERVICE.getMarkerID().toString();
        apReport.setParam(p1, p2);
    }

//
//
//    
    private void doOperation ( JInvFXFormController.FormModeEnum mode ) 
    {
        PIkOvDnrService p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkOvDnrService ();
                break;
            case VM_EDIT:
            case VM_SHOW:
                p = dsIK_OV_DNR_SERVICE.getCurrentRow ();
                break;
            case VM_DEL:
                if (dsIK_OV_DNR_SERVICE.hasMarkedRows()) {
                    if (DbUtils.deleteByMarker(this, taskContext, "ik_ov_dnr_service", "cname", dsIK_OV_DNR_SERVICE,
                            "delete from %s where %s in (select urow from mrk_u where idmarker = ?)")) {
                        try {
                            dsIK_OV_DNR_SERVICE.clearMark();
                        } catch (DataSetException e) {
                            e.printStackTrace();
                        }
                        doRefresh();
                    }
                } else {
                    p = dsIK_OV_DNR_SERVICE.getCurrentRow();
                }
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkOvDnrService> (getTaskContext (), getViewContext (), EditIkOvDnrServiceController.class)
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
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkOvDnrService> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_OV_DNR_SERVICE.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_OV_DNR_SERVICE.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_OV_DNR_SERVICE.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_OV_DNR_SERVICE.requestFocus ();
    }

    private void initCellRenderer() {
        CNAME.setCellRenderer(new BiConsumer<JInvTableCell<PIkOvDnrService, String>, String>() {
            @Override
            public void accept(JInvTableCell<PIkOvDnrService, String> cell, String aEnabled) {
                PIkOvDnrService service = (PIkOvDnrService) cell.getTableRow().getItem();
                if (service != null) {
                    if (!service.getIENABLED()) {
                        cell.setStyle(STYLE_NO_ACTIVE);
                    }
                }
            }
        });
    }

    public void onExit(ActionEvent actionEvent) {
        this.close();
    }

    public void onShowProtocols(ActionEvent actionEvent) {
//        ParamMap pm = new ParamMap();
        //pm.put(ViewIkTaxexAccController.INIT_PARAM_NIFILE, niFile);
        new FXFormLauncher<PIkOvDnrServiceProtocol>(getTaskContext(), getViewContext(), ViewIkOvDnrServiceProtocolController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
//                .initProperties(pm)
//                .callback(this::doFormTaxexAccResult)
                .modal(true)
                .show();
    }

    public void onShowOvPlat(ActionEvent actionEvent) {
        new FXFormLauncher<POvPlat>(getTaskContext(), getViewContext(), ViewOvPlatController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .modal(true)
                .show();
    }

    public void onShowSendQueue(ActionEvent actionEvent) {
        new FXFormLauncher<POvPlat>(getTaskContext(), getViewContext(), ViewIkOvDnrSendController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .modal(true)
                .show();
    }

    public void onPrint(ActionEvent actionEvent) {
        if(altPrint!=null) altPrint.fire();
    }

    public void onShowSheduleTable(ActionEvent actionEvent) {
        new FXFormLauncher<PIkOvDnrShedule>(getTaskContext(), getViewContext(), ViewIkOvDnrSheduleController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .modal(true)
                .show();
    }
}


package ru.inversionkavkaz.dnrwsadm.entity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;

/**
 * @author porche
 * @since Wed Feb 12 14:46:25 MSK 2020
 */
public class ViewIkOvDnrServiceParamController extends JInvFXBrowserController {
    public static final String INIT_PROP = "INIT_PROP";
    private String systemName = "";

    @FXML
    private JInvTable<PIkOvDnrServiceParam> IK_OV_DNR_SERVICE_PARAM;
    @FXML
    private JInvToolBar toolBar;


    private final XXIDataSet<PIkOvDnrServiceParam> dsIK_OV_DNR_SERVICE_PARAM = new XXIDataSet<>();

//
//
//    
    private void initDataSet() throws Exception {
        dsIK_OV_DNR_SERVICE_PARAM.setTaskContext(getTaskContext());
        dsIK_OV_DNR_SERVICE_PARAM.setRowClass(PIkOvDnrServiceParam.class);
        dsIK_OV_DNR_SERVICE_PARAM.setWherePredicat(PIkOvDnrServiceParam.Columns.SERVICE_ID + "='" + systemName + "'");
    }

//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        systemName = (String) getInitProperties().get(INIT_PROP);
        initDataSet();
        setTitle(getBundleString("VIEW.TITLE") + " " + systemName);

        DSFXAdapter<PIkOvDnrServiceParam> dsfx = DSFXAdapter.bind(dsIK_OV_DNR_SERVICE_PARAM, IK_OV_DNR_SERVICE_PARAM, null, false);

        dsfx.setEnableFilter(true);

        initToolBar();

        IK_OV_DNR_SERVICE_PARAM.setToolBar(toolBar);
        IK_OV_DNR_SERVICE_PARAM.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IK_OV_DNR_SERVICE_PARAM.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IK_OV_DNR_SERVICE_PARAM.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IK_OV_DNR_SERVICE_PARAM.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IK_OV_DNR_SERVICE_PARAM.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        doRefresh();
    }

    //
//
//    
    private void doRefresh() {
        IK_OV_DNR_SERVICE_PARAM.executeQuery();
    }

    //
//
//    
    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE,
                ActionFactory.ActionTypeEnum.REFRESH);

//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }

//
//
//    
    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkOvDnrServiceParam p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkOvDnrServiceParam();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_OV_DNR_SERVICE_PARAM.getCurrentRow();
                break;
        }

        if (p != null) {
            ParamMap mapProp = new ParamMap();
            mapProp.put(EditIkOvDnrServiceParamController.INIT_PROP, systemName);

            new FXFormLauncher<PIkOvDnrServiceParam>(getTaskContext(), getViewContext(), EditIkOvDnrServiceParamController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(mapProp)
                    .callback(this::doFormResult)
                    .modal(true)
                    .show();
        }
    }

//
// 
//
    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkOvDnrServiceParam> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIK_OV_DNR_SERVICE_PARAM.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIK_OV_DNR_SERVICE_PARAM.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIK_OV_DNR_SERVICE_PARAM.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        IK_OV_DNR_SERVICE_PARAM.requestFocus();
    }
//
//
//    
}


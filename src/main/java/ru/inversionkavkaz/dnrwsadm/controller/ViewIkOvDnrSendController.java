package ru.inversionkavkaz.dnrwsadm.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.control.BomController;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.bicomp.util.ControllerUtil;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.db.expr.SQLExpressionException;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;
import ru.inversion.fxpdoc.kostrep.*;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversion.icons.enums.FontAwesome;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSend;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSendA;
import ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSendActions;
import ru.inversionkavkaz.dnrwsadm.utils.AltPrintReportType;
import ru.inversionkavkaz.dnrwsadm.utils.ButtonUtils;

import static ru.inversionkavkaz.dnrwsadm.utils.DnrWsAdmConstant.DNR_WS_ADM_DEF;

/**
 * @author Valeriy Bugaev
 * @since Mon Dec 07 15:19:10 MSK 2020
 */
public class ViewIkOvDnrSendController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PIkOvDnrSend> IK_OV_DNR_SEND;
    @FXML
    private JInvToolBar toolBar;
    @FXML
    private StackPane bomPane;
    @FXML
    private DSInfoBar IK_OV_DNR_SEND$MARK;

    JInvButtonPrint altPrint = null;
    private static final String BOM_PROCESS_ID = "IK_OV_DNR_SEND";
    private final XXIDataSet<PIkOvDnrSend> dsIK_OV_DNR_SEND = new XXIDataSet<>();

    private void initDataSet() throws Exception {
        dsIK_OV_DNR_SEND.setTaskContext(getTaskContext());
        dsIK_OV_DNR_SEND.setRowClass(PIkOvDnrSend.class);
    }

//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PIkOvDnrSend> dsfx = DSFXAdapter.bind(dsIK_OV_DNR_SEND, IK_OV_DNR_SEND, null, true);

        dsfx.setEnableFilter(true);

        IK_OV_DNR_SEND$MARK.init(IK_OV_DNR_SEND.getDataSetAdapter());
        IK_OV_DNR_SEND$MARK.addAggregator("1", AggrFuncEnum.COUNT, AggregatorType.MARK, null, null);


        initToolBar();

        IK_OV_DNR_SEND.setToolBar(toolBar);
        IK_OV_DNR_SEND.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        IK_OV_DNR_SEND.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
        IK_OV_DNR_SEND.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        IK_OV_DNR_SEND.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        IK_OV_DNR_SEND.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        ControllerUtil.load(BomController.class, bomPane)
                .init(dsfx.getDataSet(), ViewIkOvDnrSendController.BOM_PROCESS_ID, true);

        dsfx.showFilterDialog(getViewContext());
        //doRefresh();
    }

    private void doRefresh() {
        IK_OV_DNR_SEND.executeQuery();
    }

    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.REFRESH);
        ButtonUtils.addBtn(toolBar, "Поставить в очередь", FontAwesome.fa_play, this::restartQueue);
        ButtonUtils.addBtn(toolBar, "Остановить отправку", FontAwesome.fa_stop, this::disableQueue);
        ButtonUtils.addBtn(toolBar, "Действия пользователя", FontAwesome.fa_info, this::showSendActions);
        ButtonUtils.addBtn(toolBar, "История постановки в очередь задания", FontAwesome.fa_list, this::showSendAReestr);

        altPrint = new JInvButtonPrint(this::prePrintAp);
        altPrint.setReportTypeId(AltPrintReportType.PRINT_WS_SEND_TYPE);
        toolBar.getItems().add(altPrint);
    }

    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PIkOvDnrSend p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkOvDnrSend();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_OV_DNR_SEND.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<PIkOvDnrSend>(getTaskContext(), getViewContext(), EditIkOvDnrSendController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .modal(true)
                    .show();
    }

    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkOvDnrSend> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    dsIK_OV_DNR_SEND.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:
                    dsIK_OV_DNR_SEND.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsIK_OV_DNR_SEND.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }
        IK_OV_DNR_SEND.requestFocus();
    }

    private void restartQueue(Event event) {
        if (dsIK_OV_DNR_SEND.hasMarkedRows() || dsIK_OV_DNR_SEND.getCurrentRow() != null) {
            if (Alerts.yesNo(this, "Постановка документов в очередь", "Вы действительно хотите отправить документы в очередь?")) {
                ParamMap pm = new ParamMap();
                pm.put("caption", "Постановка документов в очередь.");
                pm.put("pIID", dsIK_OV_DNR_SEND.getCurrentRow() != null ? dsIK_OV_DNR_SEND.getCurrentRow().getI_ID() : null);
                pm.put("pMarkerId", dsIK_OV_DNR_SEND.hasMarkedRows() ? dsIK_OV_DNR_SEND.getMarkerID() : null);
                pm.execParallel(this, getClass().getResource(DNR_WS_ADM_DEF), "restartQueue", this::onFinishExec);
            }
        } else {
            Alerts.info(this, "Пожалуйста, пометьте документ(ы).");
        }
    }

    private void disableQueue(Event event) {
        if (dsIK_OV_DNR_SEND.hasMarkedRows() || dsIK_OV_DNR_SEND.getCurrentRow() != null) {
            if (Alerts.yesNo(this, "Отмена обработки документов в очереди", "Вы действительно хотите остановить обработку документов в очереди?")) {
                ParamMap pm = new ParamMap();
                pm.put("caption", "Отмена обработки документов в очереди.");
                pm.put("pIID", dsIK_OV_DNR_SEND.getCurrentRow() != null ? dsIK_OV_DNR_SEND.getCurrentRow().getI_ID() : null);
                pm.put("pMarkerId", dsIK_OV_DNR_SEND.hasMarkedRows() ? dsIK_OV_DNR_SEND.getMarkerID() : null);
                pm.execParallel(this, getClass().getResource(DNR_WS_ADM_DEF), "disableQueue", this::onFinishExec);
            }
        } else {
            Alerts.info(this, "Пожалуйста, пометьте документ(ы).");
        }
    }

    private void onFinishExec(ParamMap pm)  {
        String message = String.format(pm.get("caption") + " Обработано %d, с ошибками %d.", pm.get("pProcessed"), pm.get("pErrorCount"));
        Alerts.info(this, message);
        try {
            dsIK_OV_DNR_SEND.executeQuery();
            dsIK_OV_DNR_SEND.refreshCurrentDependentData();
        } catch (DataSetException e) {
            e.printStackTrace();
        }
    }

    private void showSendActions(Event event) {
        if (dsIK_OV_DNR_SEND.getCurrentRow() == null) return;
        ParamMap mapProp = new ParamMap();
        mapProp.put(ViewIkOvDnrSendActionsController.InitProp.SEND_ID, dsIK_OV_DNR_SEND.getCurrentRow().getI_ID());
        new FXFormLauncher<PIkOvDnrSendActions>(getTaskContext(), getViewContext(), ViewIkOvDnrSendActionsController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .initProperties(mapProp)
                .callback(null)
                .modal(true)
                .show();
    }

    private void showSendAReestr(Event event) {
        if (dsIK_OV_DNR_SEND.getCurrentRow() == null) return;
        ParamMap mapProp = new ParamMap();
        mapProp.put(ViewIkOvDnrSendAController.InitProp.SEND_ID, dsIK_OV_DNR_SEND.getCurrentRow().getI_ID());
        new FXFormLauncher<PIkOvDnrSendA>(getTaskContext(), getViewContext(), ViewIkOvDnrSendAController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .initProperties(mapProp)
                .callback(null)
                .modal(true)
                .show();
    }

    private void prePrintAp(ApReport apReport) {
        String p1 = dsIK_OV_DNR_SEND.getCurrentRow() == null ? "" : dsIK_OV_DNR_SEND.getCurrentRow().getI_ID().toString();
        String p2 = dsIK_OV_DNR_SEND.hasMarkedRows()||dsIK_OV_DNR_SEND.getMarkerID()==null ? "" : dsIK_OV_DNR_SEND.getMarkerID().toString();
        apReport.setParam(p1, p2);
    }

    @Override
    protected void closeResources() throws SQLExpressionException {
        if (dsIK_OV_DNR_SEND.getMarkerID() != null)
            KostRepUtil.ClearMrk(getTaskContext(), dsIK_OV_DNR_SEND.getMarkerID());
    }
}


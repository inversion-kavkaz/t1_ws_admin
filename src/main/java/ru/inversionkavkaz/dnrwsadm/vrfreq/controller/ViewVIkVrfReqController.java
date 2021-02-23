package ru.inversionkavkaz.dnrwsadm.vrfreq.controller;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.control.fxtask.BiCompTask;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.dataset.DataSetException;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversion.icons.enums.FontAwesome;
import ru.inversion.tc.TaskContext;
import ru.inversionkavkaz.dnrwsadm.entity.PVerifyRequest;
import ru.inversionkavkaz.dnrwsadm.utils.AltPrintReportType;
import ru.inversionkavkaz.dnrwsadm.utils.ButtonUtils;
import ru.inversionkavkaz.dnrwsadm.utils.DbUtils;
import ru.inversionkavkaz.dnrwsadm.vrfreq.entity.PVIkVrfReq;

import java.awt.*;
import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author porche
 * @since Mon Feb 15 16:50:41 MSK 2021
 */
public class ViewVIkVrfReqController extends JInvFXBrowserController {
    @FXML
    private JInvTable<PVIkVrfReq> V_IK_VRF_REQ;
    @FXML
    private JInvToolBar toolBar;
    @FXML
    JInvTableColumn SERVICENAME;
    @FXML
    private DSInfoBar V_IK_VRF_REQ$MARK;

    private JInvButtonPrint altPrint = null;

    private final XXIDataSet<PVIkVrfReq> dsV_IK_VRF_REQ = new XXIDataSet<>();
    private static final String SQL_SAVE_REPORT_TO_FILE = "select FILENAME, xmlserialize(DOCUMENT REPORTDATA AS BLOB ENCODING 'WINDOWS-1251') from xxi.ik_vrf_req where id = ? and REPORTDATA is not null";
    Task showReportTask = null;

//
//
//    
    private void initDataSet() throws Exception {
        dsV_IK_VRF_REQ.setTaskContext(getTaskContext());
        dsV_IK_VRF_REQ.setRowClass(PVIkVrfReq.class);
    }

//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception {
        setTitle(getBundleString("VIEW.TITLE"));

        initDataSet();
        DSFXAdapter<PVIkVrfReq> dsfx = DSFXAdapter.bind(dsV_IK_VRF_REQ, V_IK_VRF_REQ, null, true);

        dsfx.setEnableFilter(true);
        V_IK_VRF_REQ$MARK.init(V_IK_VRF_REQ.getDataSetAdapter());
        V_IK_VRF_REQ$MARK.addAggregator("1", AggrFuncEnum.COUNT, AggregatorType.MARK, null, null);


        initToolBar();

        V_IK_VRF_REQ.setToolBar(toolBar);
        V_IK_VRF_REQ.setAction(ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation(FormModeEnum.VM_INS));
        V_IK_VRF_REQ.setAction(ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation(FormModeEnum.VM_SHOW));
//        V_IK_VRF_REQ.setAction(ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation(FormModeEnum.VM_EDIT));
        V_IK_VRF_REQ.setAction(ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation(FormModeEnum.VM_DEL));
        V_IK_VRF_REQ.setAction(ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh());

        doRefresh();
    }

    //
//
//    
    private void doRefresh() {
        V_IK_VRF_REQ.executeQuery();
    }

//
//
//    
    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.VIEW,
//                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE,
                ActionFactory.ActionTypeEnum.REFRESH);

        ButtonUtils.addBtn(toolBar,"Просмотреть отчет", FontAwesome.fa_file_excel_o, this::onShowReport);
        altPrint = new JInvButtonPrint(this::prePrintAp);
        altPrint.setReportTypeId(AltPrintReportType.PRINT_WS_VRF);
        toolBar.getItems().add(altPrint);
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }


//
//
//    
    private void doOperation(JInvFXFormController.FormModeEnum mode) {
        PVIkVrfReq p = null;

        switch (mode) {
            case VM_INS:
                p = new PVIkVrfReq();
                p.setID(DbUtils.getSeqID(getTaskContext(), PVerifyRequest.class));
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsV_IK_VRF_REQ.getCurrentRow();
                break;
        }

        if (p != null)
            new FXFormLauncher<PVIkVrfReq>(getTaskContext(), getViewContext(), EditVIkVrfReqController.class)
                    .dataObject(p)
                    .dialogMode(mode)
                    .initProperties(getInitProperties())
                    .callback(this::doFormResult)
                    .modal(true)
                    .show();
    }

//
// 
//
    private void doFormResult(JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PVIkVrfReq> dctl) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            switch (dctl.getFormMode()) {
                case VM_INS:
                    try {
                        dsV_IK_VRF_REQ.insertRow(dctl.getDataObject(), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                        dsV_IK_VRF_REQ.refreshCurrentRowFromDB();
                    } catch (DataSetException e) {
                        e.printStackTrace();
                    }
                    break;
                case VM_EDIT:
                    dsV_IK_VRF_REQ.updateCurrentRow(dctl.getDataObject());
                    break;
                case VM_DEL:
                    dsV_IK_VRF_REQ.removeCurrentRow();
                    break;
                default:
                    break;
            }
        }

        V_IK_VRF_REQ.requestFocus();
    }

    private void onShowReport(Event event) {
        if (dsV_IK_VRF_REQ.getCurrentRow() == null || !dsV_IK_VRF_REQ.getCurrentRow().getREPORTDATA_EXISTS().equals("Y")) {
            Alerts.error(this,"Данные отсутствуют.");
            return;
        }
        if(Alerts.yesNo(this, "Просмотр отчета", "Открыть отчет сверки в Excel?"))
            saveXmlToFile(dsV_IK_VRF_REQ.getCurrentRow().getID(), dsV_IK_VRF_REQ.getCurrentRow().getFILENAME());
    }

    private void saveXmlToFile(Long id, String fileName) {
        TaskContext taskContext = getTaskContext();
        showReportTask = new BiCompTask() {
            @Override
            protected Object call() throws SQLException, IOException {
                PreparedStatement stmt=null;
                stmt = taskContext.getConnection().prepareStatement(SQL_SAVE_REPORT_TO_FILE);
                stmt.setLong(1, id);
                ResultSet result = null;
                result = stmt.executeQuery();

                if (result.next()) {
                    String reportFilePath = System.getProperty("java.io.tmpdir") + "\\"+ fileName;
                    File outputFile = new File(reportFilePath);
                    Blob blob = result.getBlob(2);
                    saveFile(blob, outputFile);
                    // Desktop.getDesktop().open("excel "+File(fileName));
                    Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\vrfreport.bat " + reportFilePath);
                }
                result.close();
                stmt.close();
                return this;
            }
        };
        showReportTask.setOnFailed (this::onFailed);
        showReportTask.setOnSucceeded (this::onSucceded);

        new Thread (showReportTask, "Show report in progress").start ();
        setState (StateEnum.WAIT);
    }

    private void onSucceded(Event event) {
        setState (StateEnum.ACTIVE);
    }

    private void onFailed(Event event) {
        setState (StateEnum.ACTIVE);
        String errorMessage = " ";
        if(showReportTask!=null&&showReportTask.getException()!=null){
            showReportTask.getException().printStackTrace();
            errorMessage = showReportTask.getException().getMessage();
        }
        Alerts.error(this, "Просмотр отчета", "Ошибка открытия файла " + errorMessage);
    }

    private void saveFile(Blob blob, File file) throws SQLException, IOException {
        InputStream in = blob.getBinaryStream();
        OutputStream out = new FileOutputStream(file);
        byte[] buff = new byte[4096];
        int len = 0;

        while ((len = in.read(buff)) != -1) {
            out.write(buff, 0, len);
        }

        blob.free();
        in.close();
        out.close();
    }

    private void prePrintAp(ApReport apReport) {
        String p1 = dsV_IK_VRF_REQ.getCurrentRow() == null ? "" : dsV_IK_VRF_REQ.getCurrentRow().getID().toString();
        String p2 = dsV_IK_VRF_REQ.hasMarkedRows()||dsV_IK_VRF_REQ.getMarkerID() == null ? "" : dsV_IK_VRF_REQ.getMarkerID().toString();
        apReport.setParam(p1, p2);
    }

//
//
//    
}

